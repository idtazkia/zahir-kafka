package id.ac.tazkia.zahir.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.tazkia.zahir.dao.BankDao;
import id.ac.tazkia.zahir.dao.InvoiceConfigurationDao;
import id.ac.tazkia.zahir.dao.InvoiceDao;
import id.ac.tazkia.zahir.dao.InvoicePaymentDao;
import id.ac.tazkia.zahir.dto.*;
import id.ac.tazkia.zahir.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service @Transactional
public class KafkaListenerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListenerService.class);

    @Autowired private ObjectMapper objectMapper;
    @Autowired private BankDao bankDao;
    @Autowired private InvoiceDao invoiceDao;
    @Autowired private InvoicePaymentDao invoicePaymentDao;
    @Autowired private InvoiceConfigurationDao invoiceConfigurationDao;
    @Autowired private ZahirService zahirService;

    @KafkaListener(topics = "${kafka.topic.tagihan.response}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleTagihanResponse(String message) {

        try {
            LOGGER.debug("Terima tagihan response : {}", message);
            TagihanResponse tagihanResponse = objectMapper.readValue(message, TagihanResponse.class);
            InvoiceConfiguration config = invoiceConfigurationDao.findByInvoiceType(tagihanResponse.getJenisTagihan());

            if (config == null) {
                LOGGER.error("Invoice Type {} not yet configured", tagihanResponse.getJenisTagihan());
                return;
            }

            Product p = new Product();
            p.setId(config.getProduct());

            Department dept = new Department();
            dept.setId(config.getDepartment());

            Customer customer = new Customer();

            if(config.getCustomer() != null) {
                customer.setId(config.getCustomer());
            } else {
                customer = zahirService.findCustomerByCode(tagihanResponse.getDebitur());
            }

            if (customer == null || customer.getId() == null) {
                LOGGER.error("Invoice Type {} has no customer configuration", tagihanResponse.getJenisTagihan());
                return;
            }

            SalesInvoiceRequest request = new SalesInvoiceRequest();
            request.setIsPosted(true);
            request.setInvoiceDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
            request.setCustomer(customer.getId());
            request.setDepartment(dept.getId());
            request.setLineItems(
                    Arrays.asList(
                            new SalesInvoiceRequestLineItem[]{
                                    new SalesInvoiceRequestLineItem(
                                            p.getId(),
                                            1,
                                            new BigDecimal("300000.00"))}));

            LOGGER.debug("Sales invoice request : {}", objectMapper.writeValueAsString(request));

            SalesInvoice salesInvoice = zahirService.createSalesInvoice(request);

            Invoice inv = new Invoice();
            inv.setId(salesInvoice.getId());
            inv.setAmount(salesInvoice.getTotalAmount());
            inv.setCustomer(salesInvoice.getCustomer().getId());
            inv.setInvoiceNumber(tagihanResponse.getNomorTagihan());
            inv.setSalesInvoiceNumber(salesInvoice.getInvoiceNumber());

            invoiceDao.save(inv);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @KafkaListener(topics = "${kafka.topic.tagihan.payment}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleTagihanPayment(String message) {
        try {
            LOGGER.debug("Terima pembayaran tagihan : {}", message);
            PembayaranTagihan pembayaranTagihan = objectMapper.readValue(message, PembayaranTagihan.class);

            Invoice invoice = invoiceDao.findByInvoiceNumber(pembayaranTagihan.getNomorTagihan());
            if (invoice == null) {
                LOGGER.error("No tagihan {} tidak ada di database", pembayaranTagihan.getNomorTagihan());
                return;
            }

            Bank bank = bankDao.findById(pembayaranTagihan.getBank()).get();
            if (bank == null) {
                LOGGER.error("Bank {} tidak ada di database", pembayaranTagihan.getBank());
                return;
            }

            Account bankAccount = new Account();
            bankAccount.setId(bank.getAccountCode());

            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.setCustomer(invoice.getCustomer());
            paymentRequest.setTransactionDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
            paymentRequest.getLineItems().add(
                    new PaymentRequestLineItem(invoice.getId(), invoice.getAmount()));
            paymentRequest.setCash(new PaymentRequestCash(bankAccount.getId()));

            LOGGER.debug("Payment Request : {}", objectMapper.writeValueAsString(paymentRequest));
            Payment payment = zahirService.createPayment(paymentRequest);
            LOGGER.debug("Payment Response : {}", objectMapper.writeValueAsString(payment));

            invoice.setInvoiceStatus(InvoiceStatus.PAID);
            invoiceDao.save(invoice);

            InvoicePayment invoicePayment = new InvoicePayment();
            invoicePayment.setId(payment.getId());
            invoicePayment.setInvoice(invoice);
            invoicePayment.setReferenceNumber(payment.getReferenceNumber());
            invoicePayment.setBank(bank);
            invoicePayment.setAmount(pembayaranTagihan.getNilaiPembayaran());
            invoicePaymentDao.save(invoicePayment);

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }
}

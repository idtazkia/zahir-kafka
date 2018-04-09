package id.ac.tazkia.zahir.service;

import id.ac.tazkia.zahir.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Service
public class ZahirService {

    private static final String URL_API_PRODUCT = "/api/product";
    private static final String URL_API_ACCOUNT = "/api/account";
    private static final String URL_API_CUSTOMER = "/api/contact";
    private static final String URL_API_DEPARTMENT = "/api/departments";
    private static final String URL_API_PROJECT = "/api/projects";
    private static final String URL_API_INVOICE = "/api/sales_invoice";
    private static final String URL_API_PAYMENT = "/api/receivable_payments";

    @Value("${zahir.api.url}") private String zahirApiUrl;
    @Value("${zahir.api.key}") private String zahirApiKey;

    private WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.builder()
                .baseUrl(zahirApiUrl)
                .defaultHeader("api_key", zahirApiKey)
                .build();
    }

    public Product findProductByCode(String code) {
        return webClient
                .get().uri(URL_API_PRODUCT+"?code={code}", code)
                .retrieve().bodyToFlux(Product.class).blockFirst();
    }

    public Account findAccountByCode(String code) {
        return webClient
                .get().uri(URL_API_ACCOUNT+"?code={code}", code)
                .retrieve().bodyToFlux(Account.class).blockFirst();
    }

    public Customer findCustomerByCode(String code) {
        return webClient
                .get().uri(URL_API_CUSTOMER+"?code={code}", code)
                .retrieve().bodyToFlux(Customer.class).blockFirst();
    }

    public Department findDepartmentByCode(String code) {
        return webClient
                .get().uri(URL_API_DEPARTMENT+"?code={code}", code)
                .retrieve().bodyToFlux(Department.class).blockFirst();
    }

    public Project findProjectByCode(String code) {
        return webClient
                .get().uri(URL_API_PROJECT+"?code={code}", code)
                .retrieve().bodyToFlux(Project.class).blockFirst();
    }

    public SalesInvoice createSalesInvoice(SalesInvoiceRequest invoice) {
        SalesInvoiceRequest[] requestBody = new SalesInvoiceRequest[]{invoice};
        return webClient.post().uri(URL_API_INVOICE)
                .body(Mono.just(requestBody), SalesInvoiceRequest[].class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new IllegalStateException())
                )
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new IllegalStateException())
                )
                .bodyToMono(SalesInvoice.class).block();
    }

    public SalesInvoice getSalesInvoice(String number) {
        return webClient
                .get().uri(URL_API_INVOICE+"?invoice_number={number}", number)
                .retrieve().bodyToFlux(SalesInvoice.class).blockFirst();
    }

    public Payment createPayment(PaymentRequest paymentRequest) {
        return webClient.post().uri(URL_API_PAYMENT)
                .body(Mono.just(paymentRequest), PaymentRequest.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new IllegalStateException(clientResponse.toString()))
                )
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new IllegalStateException(clientResponse.toString()))
                )
                .bodyToMono(Payment.class).block();
    }
}

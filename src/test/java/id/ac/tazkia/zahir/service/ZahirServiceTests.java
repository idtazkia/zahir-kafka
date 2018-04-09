package id.ac.tazkia.zahir.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.tazkia.zahir.dto.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZahirServiceTests {
    @Autowired private ZahirService zahirService;
    @Autowired private ObjectMapper objectMapper;

    @Test
    public void testProductByCode() {
        String code = "1";

        Product pmb = zahirService.findProductByCode(code);
        Assert.assertNotNull(pmb);
        Assert.assertEquals("Pendaftaran Mahasiswa Baru 2018", pmb.getName());
    }

    @Test
    public void testCustomerByCode() {
        String code = "16103011";

        Customer customer = zahirService.findCustomerByCode(code);
        Assert.assertNotNull(customer);
        Assert.assertEquals("Ahmad Misluha", customer.getName());
    }

    @Test
    public void testDepartmentByCode() {
        String code = "1";

        Department department = zahirService.findDepartmentByCode(code);
        Assert.assertNotNull(department);
        Assert.assertEquals("Yayasan", department.getName());
    }

    @Test
    public void testProjectByCode() {
        String code = "1";

        Project project = zahirService.findProjectByCode(code);
        Assert.assertNotNull(project);
        Assert.assertEquals("Program Studi S1 Ekonomi Syariah", project.getName());
    }

    @Test
    public void testCreateInvoiceSppTetap() throws Exception {
        Product spp = zahirService.findProductByCode("11");
        Department yayasan = zahirService.findDepartmentByCode("1");
        Project prodi = zahirService.findProjectByCode("1");
        Customer mhs = zahirService.findCustomerByCode("16103011");

        SalesInvoiceRequest request = new SalesInvoiceRequest();
        request.setIsPosted(true);
        request.setInvoiceDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        request.setCustomer(mhs.getId());
        request.setDepartment(yayasan.getId());
        request.setProject(prodi.getId());
        request.setLineItems(
                Arrays.asList(
                        new SalesInvoiceRequestLineItem[]{
                                new SalesInvoiceRequestLineItem(
                                        spp.getId(),
                                        1,
                                        new BigDecimal("1200000.00"))}));

        System.out.println(objectMapper.writeValueAsString(request));

        SalesInvoice hasil = zahirService.createSalesInvoice(request);
        Assert.assertNotNull(hasil);
        System.out.println(objectMapper.writeValueAsString(hasil));
    }

    @Test
    public void testCreateInvoicePmb() throws Exception {
        Product pmb = zahirService.findProductByCode("1");
        Department yayasan = zahirService.findDepartmentByCode("1");
        Customer calon = zahirService.findCustomerByCode("CUST-9999");

        SalesInvoiceRequest request = new SalesInvoiceRequest();
        request.setIsPosted(true);
        request.setInvoiceDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        request.setCustomer(calon.getId());
        request.setDepartment(yayasan.getId());
        request.setLineItems(
                Arrays.asList(
                        new SalesInvoiceRequestLineItem[]{
                                new SalesInvoiceRequestLineItem(
                                        pmb.getId(),
                                        1,
                                        new BigDecimal("300000.00"))}));

        System.out.println(objectMapper.writeValueAsString(request));

        SalesInvoice hasil = zahirService.createSalesInvoice(request);
        Assert.assertNotNull(hasil);
        System.out.println(objectMapper.writeValueAsString(hasil));
    }
}

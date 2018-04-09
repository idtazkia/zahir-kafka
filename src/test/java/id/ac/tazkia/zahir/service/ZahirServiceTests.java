package id.ac.tazkia.zahir.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.tazkia.zahir.dto.Customer;
import id.ac.tazkia.zahir.dto.Department;
import id.ac.tazkia.zahir.dto.Product;
import id.ac.tazkia.zahir.dto.Project;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}

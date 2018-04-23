package id.ac.tazkia.zahir.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Files;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaListenerServiceTests {

    @Value("classpath:/json/tagihan-response.json") private Resource tagihanResponseFile;
    @Value("classpath:/json/tagihan-payment.json") private Resource tagihanPaymentFile;

    @Autowired private KafkaListenerService kafkaListenerService;

    @Test
    public void testHandleTagihanResponse() throws Exception {
        String tagihanResponseContent = new String(Files.readAllBytes(tagihanResponseFile.getFile().toPath()));
        Assert.assertNotNull(tagihanResponseContent);
        kafkaListenerService.handleTagihanResponse(tagihanResponseContent);
    }

    @Test
    public void testHandleTagihanPayment() throws Exception {
        String tagihanPaymentContent = new String(Files.readAllBytes(tagihanPaymentFile.getFile().toPath()));
        Assert.assertNotNull(tagihanPaymentContent);
        kafkaListenerService.handleTagihanPayment(tagihanPaymentContent);
    }
}

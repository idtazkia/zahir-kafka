package id.ac.tazkia.zahir.service;

import id.ac.tazkia.zahir.dto.Customer;
import id.ac.tazkia.zahir.dto.Department;
import id.ac.tazkia.zahir.dto.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

@Service
public class ZahirService {

    private static final String URL_API_PRODUCT = "/api/product";
    private static final String URL_API_CUSTOMER = "/api/contact";
    private static final String URL_API_DEPARTMENT = "/api/departments";

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
}

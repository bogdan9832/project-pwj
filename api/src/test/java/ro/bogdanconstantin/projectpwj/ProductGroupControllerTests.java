package ro.bogdanconstantin.projectpwj;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ro.bogdanconstantin.projectpwj.controllers.CustomerController;
import ro.bogdanconstantin.projectpwj.domain.Customer;
import ro.bogdanconstantin.projectpwj.domain.Order;
import ro.bogdanconstantin.projectpwj.domain.Product;
import ro.bogdanconstantin.projectpwj.domain.ProductGroup;
import ro.bogdanconstantin.projectpwj.repository.*;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
@EnableTransactionManagement
@ComponentScan
public class ProductGroupControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CustomersRepository customersRepository;
    @MockBean
    private OptionsRepository optionsRepository;
    @MockBean
    private OrdersRepository ordersRepository;
    @MockBean
    private OrderItemsRepository orderItemsRepository;
    @MockBean
    private OrderItemOptionsRepository orderItemOptionsRepository;
    @MockBean
    private ProductsRepository productsRepository;
    @MockBean
    private ProductGroupsRepository productGroupsRepository;

    @Test
    public void testgetAll()  throws Exception {
        mockMvc.perform(get("/groups"))
                .andExpect(status().isOk()).andExpect(jsonPath("$").isArray());

    }


    @Test
    public void testInsert()  throws Exception {
        var productGroup = new ProductGroup();
        productGroup.setId(1L);

        mockMvc.perform(post("/groups").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(productGroup)))
                .andExpect(status().isOk());

    }
    @Test
    public void testUpdate()  throws Exception {
        var productGroup = new ProductGroup();
        productGroup.setId(1L);

        mockMvc.perform(patch("/groups").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(productGroup)))
                .andExpect(status().isOk());

    }
    @Test
    public void testDelete()  throws Exception  {
        mockMvc.perform(delete("/groups/1"))
                .andExpect(status().isOk());

    }
}
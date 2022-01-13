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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ro.bogdanconstantin.projectpwj.controllers.CustomerController;
import ro.bogdanconstantin.projectpwj.domain.Customer;
import ro.bogdanconstantin.projectpwj.domain.Order;
import ro.bogdanconstantin.projectpwj.repository.*;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = CustomerController.class)
@EnableTransactionManagement
@ComponentScan
public class OrderControllerTests {
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
            mockMvc.perform(get("/orders"))
                    .andExpect(status().isOk()).andExpect(jsonPath("$").isArray());

    }

    @Test
    public void testgetByCustomer()  throws Exception {
            mockMvc.perform(get("/orders/0/d1864532-cd55-4e81-a3ce-8d060b0fb15c"))
                    .andExpect(status().isOk()).andExpect(jsonPath("$").isArray());

    }
    @Test
    public void testgetByDateRange()  throws Exception {
            mockMvc.perform(get("/orders/dates/2022-01-01/2022-01-15"))
                    .andExpect(status().isOk()).andExpect(jsonPath("$").isArray());

    }

    @Test
    public void testInsert()  throws Exception {
            var order = new Order("0",new Date(),new Customer("a","b",1L));

            mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(order)))
                    .andExpect(status().isOk());

    }
    @Test
    public void testUpdate()  throws Exception {
            var order = new Order("0",new Date(),new Customer("a","b",1L));


            mockMvc.perform(patch("/orders").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(order)))
                    .andExpect(status().isOk());

    }
    @Test
    public void testDelete()  throws Exception  {
            mockMvc.perform(delete("/orders/1"))
                    .andExpect(status().isOk());

    }
}
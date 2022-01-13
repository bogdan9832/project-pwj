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
import org.springframework.web.bind.annotation.*;
import ro.bogdanconstantin.projectpwj.controllers.CustomerController;
import ro.bogdanconstantin.projectpwj.domain.Customer;
import ro.bogdanconstantin.projectpwj.domain.Option;
import ro.bogdanconstantin.projectpwj.repository.*;


import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = CustomerController.class)
@EnableTransactionManagement
@ComponentScan
public class CustomerControllerTests {
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
    public void testGetAll()  throws  Exception{
            mockMvc.perform(get("/customers"))
                    .andExpect(status().isOk());

    }
    @Test
    public void testgetById() throws Exception{

            mockMvc.perform(get("/customers/1"))
                    .andExpect(status().isOk());


    }
    @Test
    public void testInsert() throws Exception {
            var customer = new Customer("","",1L);

            mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(customer)))
                    .andExpect(status().isOk());

    }
    @Test
    public void testUpdate() throws  Exception{
        var customer = new Customer("","",1L);

            mockMvc.perform(patch("/customers").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(customer)))
                    .andExpect(status().isOk());

    }
    @Test
    public void testDelete() throws Exception {

            mockMvc.perform(delete("/customers/1"))
                    .andExpect(status().isOk());


    }
}
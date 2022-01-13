package ro.bogdanconstantin.projectpwj;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
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
import ro.bogdanconstantin.projectpwj.domain.OrderItem;
import ro.bogdanconstantin.projectpwj.repository.*;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
@EnableTransactionManagement
@ComponentScan
public class OrderItemControllerTests {
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
    public void testgetByOrder() throws Exception {
        var order = mock(Order.class);
        when(ordersRepository.findById(any(Long.class))).thenReturn(Optional.of(order));
        when(order.getItems()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/items/1"))
                .andExpect(status().isOk());

    }

    @Test
    public void testInsert() throws Exception {
        var order = mock(Order.class);
        when(ordersRepository.findById(any(Long.class))).thenReturn(Optional.of(order));
        when(order.getItems()).thenReturn(new ArrayList<>());

        mockMvc.perform(post("/items/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new OrderItem())))
                .andExpect(status().isOk());

    }

    @Test
    public void testUpdate() throws Exception {
        var order = mock(Order.class);
        when(ordersRepository.findById(1L)).thenReturn(Optional.of(order));
        when(order.getItems()).thenReturn(new ArrayList<>());

        mockMvc.perform(patch("/items").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new OrderItem())))
                .andExpect(status().isOk());

    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/items/1"))
                .andExpect(status().isOk());

    }
}

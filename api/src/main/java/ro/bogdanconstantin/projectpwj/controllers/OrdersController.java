package ro.bogdanconstantin.projectpwj.controllers;

import org.springframework.web.bind.annotation.*;
import ro.bogdanconstantin.projectpwj.domain.Order;
import ro.bogdanconstantin.projectpwj.repository.OrdersRepository;
import ro.bogdanconstantin.projectpwj.services.OrdersService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrdersController {

  private final OrdersService service;

    public OrdersController(OrdersService service) {
        this.service = service;
    }

    @GetMapping("/orders")
    Iterable<Order> getAll(){
        return service.getAll();
    }
    @GetMapping("/orders/{status}/{customer}")
    Iterable<Order> getByCustomer(@PathVariable String customer, @PathVariable int status){
        return service.getByCustomer(customer,status);
    }
    @GetMapping("/orders/dates/{startDate}/{endDate}")
    Iterable<Order> getAllInDateRange(@PathVariable String startDate, @PathVariable String endDate) throws ParseException {
        return service.getAllInDateRange(startDate,endDate);
    }
    @PostMapping("/orders")
    Order insert(@RequestBody Order order){
        return service.insert(order);
    }
    @PatchMapping("/orders")
    Order  update(@RequestBody Order order){
        return service.update(order);
    }
    @DeleteMapping("/orders/{id}")
    void delete(@PathVariable Long id){
          service.delete(id);
    }

}

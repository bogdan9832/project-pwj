package ro.bogdanconstantin.projectpwj.controllers;

import org.springframework.web.bind.annotation.*;
import ro.bogdanconstantin.projectpwj.domain.OrderItem;
import ro.bogdanconstantin.projectpwj.domain.Order;
import ro.bogdanconstantin.projectpwj.services.OrderItemService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderItemController {
    private final OrderItemService service;

    public OrderItemController(OrderItemService service) {
        this.service = service;
    }

    @GetMapping("/items/{order}")
    List<OrderItem> getByOrder(@PathVariable Long order){
        return service.getByOrder(order);
    }
    @PostMapping("/items/{order}")
    OrderItem insert(@RequestBody OrderItem item,@PathVariable Long order){
        return service.insert(item,order);
    }
    @PatchMapping("/items")
    OrderItem update(@RequestBody OrderItem item){

        return service.update(item);
    }
    @DeleteMapping("/items/{id}")
    void delete(@PathVariable Long id){
        service.delete(id);
    }

}

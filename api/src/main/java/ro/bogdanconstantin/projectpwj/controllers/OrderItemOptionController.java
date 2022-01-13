package ro.bogdanconstantin.projectpwj.controllers;

import org.springframework.web.bind.annotation.*;
import ro.bogdanconstantin.projectpwj.domain.OrderItem;
import ro.bogdanconstantin.projectpwj.domain.OrderItemOption;
import ro.bogdanconstantin.projectpwj.services.OrderItemOptionService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderItemOptionController {
    final private OrderItemOptionService service;

    public OrderItemOptionController(OrderItemOptionService service) {
        this.service = service;
    }

    @GetMapping("/item-options/{orderItem}")
    List<OrderItemOption> getByItem(@PathVariable Long orderItem){
        return service.getByItem(orderItem);
    }
    @PostMapping("/item-options/{orderItem}")
    OrderItemOption insert(@RequestBody OrderItemOption option,@PathVariable Long orderItem){
       return service.insert(option,orderItem);
    }
    @PatchMapping("/item-options")
    OrderItemOption update(@RequestBody OrderItemOption option){
        return service.update(option);
    }
    @DeleteMapping("/item-options/{id}")
    void delete(@PathVariable Long id){
        service.delete(id);
    }

}

package ro.bogdanconstantin.projectpwj.services;

import org.springframework.stereotype.Service;
import ro.bogdanconstantin.projectpwj.domain.OrderItem;
import ro.bogdanconstantin.projectpwj.domain.OrderItemOption;
import ro.bogdanconstantin.projectpwj.repository.OrderItemOptionsRepository;
import ro.bogdanconstantin.projectpwj.repository.OrderItemsRepository;

import java.util.List;

@Service
public class OrderItemOptionService {

    private final OrderItemOptionsRepository repo;
    private final OrderItemsRepository orderItemsRepository;


    public OrderItemOptionService(OrderItemOptionsRepository repo, OrderItemsRepository orderItemsRepository) {
        this.repo = repo;
        this.orderItemsRepository = orderItemsRepository;
    }

    public List<OrderItemOption> getByItem(Long orderItem){
        return orderItemsRepository.findById(orderItem).orElse(new OrderItem()).getOptions();
    }
    public OrderItemOption insert(OrderItemOption option,Long orderItem){
        var persistedItem = orderItemsRepository.findById(orderItem).orElse(new OrderItem());
        option = repo.save(option);
        persistedItem.getOptions().add(option);
        orderItemsRepository.save(persistedItem);
        return option;
    }
    public OrderItemOption update( OrderItemOption option){
        return repo.save(option);
    }

    public void delete( Long id){
        repo.deleteById(id);
    }

}

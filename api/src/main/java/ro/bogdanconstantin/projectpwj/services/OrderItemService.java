package ro.bogdanconstantin.projectpwj.services;

import org.springframework.stereotype.Service;
import ro.bogdanconstantin.projectpwj.domain.OrderItem;
import ro.bogdanconstantin.projectpwj.domain.Order;
import ro.bogdanconstantin.projectpwj.repository.OrdersRepository;
import ro.bogdanconstantin.projectpwj.repository.OrderItemsRepository;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemsRepository repo;
    private final OrdersRepository ordersRepository;


    public OrderItemService(OrderItemsRepository repo, OrdersRepository ordersRepository) {
        this.repo = repo;
        this.ordersRepository = ordersRepository;
    }


    public List<OrderItem> getByOrder(Long order){
        return ordersRepository.findById(order).get().getItems();
    }
    public OrderItem insert( OrderItem item, Long order){
        var persistedGroup = ordersRepository.findById(order).get();
        item = repo.save(item);
        persistedGroup.getItems().add(item);
        ordersRepository.save(persistedGroup);
        return item;
    }
    public OrderItem update( OrderItem item){
        return repo.save(item);
    }

    public void delete( Long id){
        repo.deleteById(id);
    }

}

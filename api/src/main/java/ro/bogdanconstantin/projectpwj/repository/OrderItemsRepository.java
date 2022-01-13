package ro.bogdanconstantin.projectpwj.repository;

import org.springframework.data.repository.CrudRepository;
import ro.bogdanconstantin.projectpwj.domain.Customer;
import ro.bogdanconstantin.projectpwj.domain.OrderItem;

public interface OrderItemsRepository extends CrudRepository<OrderItem, Long> {

}

package ro.bogdanconstantin.projectpwj.repository;

import org.springframework.data.repository.CrudRepository;
import ro.bogdanconstantin.projectpwj.domain.Customer;
import ro.bogdanconstantin.projectpwj.domain.OrderItem;
import ro.bogdanconstantin.projectpwj.domain.OrderItemOption;

public interface OrderItemOptionsRepository extends CrudRepository<OrderItemOption, Long> {

}

package ro.bogdanconstantin.projectpwj.repository;

import org.springframework.data.repository.CrudRepository;
import ro.bogdanconstantin.projectpwj.domain.Customer;
import ro.bogdanconstantin.projectpwj.domain.Order;
import ro.bogdanconstantin.projectpwj.domain.Product;
import java.util.Date;

import java.time.Instant;

public interface OrdersRepository extends CrudRepository<Order, Long> {


    Iterable<Order> findOrdersByTimestampBetween(Date start, Date end);

    Iterable<Order> findOrdersByCustomerUuidAndStatus(String customer_guid, int status);
}

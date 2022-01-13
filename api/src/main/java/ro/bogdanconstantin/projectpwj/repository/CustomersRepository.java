package ro.bogdanconstantin.projectpwj.repository;

import org.springframework.data.repository.CrudRepository;
import ro.bogdanconstantin.projectpwj.domain.Customer;

public interface CustomersRepository extends CrudRepository<Customer, Long> {

}

package ro.bogdanconstantin.projectpwj.repository;

import org.springframework.data.repository.CrudRepository;
import ro.bogdanconstantin.projectpwj.domain.Customer;
import ro.bogdanconstantin.projectpwj.domain.Order;
import ro.bogdanconstantin.projectpwj.domain.ProductGroup;

import java.util.List;

public interface ProductGroupsRepository extends CrudRepository<ProductGroup, Long> {



}

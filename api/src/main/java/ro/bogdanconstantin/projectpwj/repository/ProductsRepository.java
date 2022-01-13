package ro.bogdanconstantin.projectpwj.repository;

import org.springframework.data.repository.CrudRepository;
import ro.bogdanconstantin.projectpwj.domain.Customer;
import ro.bogdanconstantin.projectpwj.domain.Product;
import ro.bogdanconstantin.projectpwj.domain.ProductGroup;

public interface ProductsRepository extends CrudRepository<Product, Long> {




}

package ro.bogdanconstantin.projectpwj.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ro.bogdanconstantin.projectpwj.domain.Customer;
import ro.bogdanconstantin.projectpwj.domain.Product;
import ro.bogdanconstantin.projectpwj.domain.ProductGroup;
import ro.bogdanconstantin.projectpwj.domain.dtos.ProductDto;
import ro.bogdanconstantin.projectpwj.repository.ProductGroupsRepository;
import ro.bogdanconstantin.projectpwj.repository.ProductsRepository;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService {

    private final ProductsRepository repo;
    private final ProductGroupsRepository groupsRepository;


    public ProductService(ProductsRepository repo, ProductGroupsRepository groupsRepository) {
        this.repo = repo;
        this.groupsRepository = groupsRepository;
    }

    public List<Product> getByGroup( Long group){
        return groupsRepository.findById(group).orElse(new ProductGroup()).getProducts();
    }
    public Product insert( ProductDto product,  Long group){
        var persistedGroup = groupsRepository.findById(group).orElse(new ProductGroup());
        var fullProduct = repo.save(product.toFullClass());
        persistedGroup.getProducts().add(fullProduct);
        groupsRepository.save(persistedGroup);
        return fullProduct;
    }
    public Product update( ProductDto product) throws Exception {
        return repo.save(product.toFullClass());

    }
    public void delete( Long id){
        repo.deleteById(id);
    }

}

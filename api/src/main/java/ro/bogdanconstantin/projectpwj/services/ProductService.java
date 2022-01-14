package ro.bogdanconstantin.projectpwj.services;

import org.springframework.stereotype.Service;
import ro.bogdanconstantin.projectpwj.domain.Product;
import ro.bogdanconstantin.projectpwj.domain.ProductGroup;
import ro.bogdanconstantin.projectpwj.domain.dtos.ProductDto;
import ro.bogdanconstantin.projectpwj.repository.ProductGroupsRepository;
import ro.bogdanconstantin.projectpwj.repository.ProductsRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductsRepository productsRepository;
    private final ProductGroupsRepository productGroupsRepository;


    public ProductService(ProductsRepository productsRepository, ProductGroupsRepository groupsRepository) {
        this.productsRepository = productsRepository;
        this.productGroupsRepository = groupsRepository;
    }

    public List<Product> getByGroup( Long group){
        return productGroupsRepository.findById(group).orElse(new ProductGroup()).getProducts();
    }
    public Product insert( ProductDto product,  Long group){
        var persistedGroup = productGroupsRepository.findById(group).orElse(new ProductGroup());
        var fullProduct = productsRepository.save(product.toFullClass());
        persistedGroup.getProducts().add(fullProduct);
        productGroupsRepository.save(persistedGroup);
        return fullProduct;
    }
    public Product update( Product product) throws Exception {


        var g = productsRepository.findById(product.getId());
        if(g.isEmpty()){
            throw new Exception("Product " + product.getId() + "  not in db");
        }
        var persisted = g.get();
        persisted.setName(product.getName());
        persisted.setDescription(product.getDescription());
        persisted.setImage(product.getImage());
        persisted.setPrice(product.getPrice());
        return productsRepository.save(persisted);



    }
    public void delete( Long id){
        productsRepository.deleteById(id);
    }

}

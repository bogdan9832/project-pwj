package ro.bogdanconstantin.projectpwj.controllers;

import org.springframework.web.bind.annotation.*;
import ro.bogdanconstantin.projectpwj.domain.Customer;
import ro.bogdanconstantin.projectpwj.domain.Product;
import ro.bogdanconstantin.projectpwj.domain.ProductGroup;
import ro.bogdanconstantin.projectpwj.domain.dtos.ProductDto;
import ro.bogdanconstantin.projectpwj.repository.ProductGroupsRepository;
import ro.bogdanconstantin.projectpwj.repository.ProductsRepository;
import ro.bogdanconstantin.projectpwj.services.ProductService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {

  private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products/{group}")
    List<Product> getByGroup(@PathVariable Long group){
        return  service.getByGroup(group);
    }
    @PostMapping("/products/{group}")
    Product insert(@RequestBody ProductDto product, @PathVariable Long group){
        return  service.insert(product,group);
    }
    @PatchMapping("/products")
    Product update(@RequestBody ProductDto product) throws Exception {
        return  service.update(product);

    }
    @DeleteMapping("/products/{id}")
    void delete(@PathVariable Long id){
        service.delete(id);
    }

}

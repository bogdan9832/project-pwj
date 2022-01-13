package ro.bogdanconstantin.projectpwj.controllers;

import org.springframework.web.bind.annotation.*;
import ro.bogdanconstantin.projectpwj.domain.ProductGroup;
import ro.bogdanconstantin.projectpwj.domain.dtos.ProductGroupDto;
import ro.bogdanconstantin.projectpwj.repository.ProductGroupsRepository;
import ro.bogdanconstantin.projectpwj.services.ProductGroupService;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductGroupController {

    private final ProductGroupService service;

    public ProductGroupController(ProductGroupService service) {
        this.service = service;
    }

    @GetMapping("/groups")
    List<ProductGroupDto> getAll(){
      return service.getAll();
    }
    @PostMapping("/groups")
    ProductGroup insert(@RequestBody ProductGroupDto group){
        return service.insert(group);
    }
    @PatchMapping("/groups")
    ProductGroup  update(@RequestBody ProductGroup group){
       return service.update(group);
    }
    @DeleteMapping("/groups/{id}")
    void delete(@PathVariable Long id){
         service.delete(id);

    }

}

package ro.bogdanconstantin.projectpwj.services;

import org.springframework.stereotype.Service;
import ro.bogdanconstantin.projectpwj.domain.ProductGroup;
import ro.bogdanconstantin.projectpwj.domain.dtos.ProductGroupDto;
import ro.bogdanconstantin.projectpwj.repository.ProductGroupsRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductGroupService {

    private final ProductGroupsRepository productGroupsRepository;


    public ProductGroupService( ProductGroupsRepository groupsRepository) {

        this.productGroupsRepository = groupsRepository;
    }

    public List<ProductGroupDto> getAll(){
        return StreamSupport.stream(productGroupsRepository.findAll().spliterator(),false)
                .map(ProductGroup::toDto).collect(Collectors.toList());
    }
    public ProductGroup insert( ProductGroupDto group){
        return productGroupsRepository.save(group.toFullClass());
    }
    public  ProductGroup  update( ProductGroup group) throws Exception{
        var g = productGroupsRepository.findById(group.getId());
        if(g.isEmpty()){
            throw new Exception("Group not in db");
        }
        var persisted = g.get();
        persisted.setName(group.getName());
        persisted.setImage(group.getImage());
        return productGroupsRepository.save(persisted);
    }
    public void delete( Long id){
         productGroupsRepository.deleteById(id);

    }

}

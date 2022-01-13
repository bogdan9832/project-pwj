package ro.bogdanconstantin.projectpwj.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ro.bogdanconstantin.projectpwj.domain.ProductGroup;
import ro.bogdanconstantin.projectpwj.domain.dtos.ProductGroupDto;
import ro.bogdanconstantin.projectpwj.repository.ProductGroupsRepository;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductGroupService {

    private final ProductGroupsRepository groupsRepository;


    public ProductGroupService( ProductGroupsRepository groupsRepository) {

        this.groupsRepository = groupsRepository;
    }

    public List<ProductGroupDto> getAll(){
        return StreamSupport.stream(groupsRepository.findAll().spliterator(),false)
                .map(ProductGroup::toDto).collect(Collectors.toList());
    }
    public ProductGroup insert( ProductGroupDto group){
        return groupsRepository.save(group.toFullClass());
    }
    public  ProductGroup  update( ProductGroup group){
        return groupsRepository.save(group);
    }
    public void delete( Long id){
         groupsRepository.deleteById(id);

    }

}

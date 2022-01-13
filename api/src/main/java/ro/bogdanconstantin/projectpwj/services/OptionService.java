package ro.bogdanconstantin.projectpwj.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ro.bogdanconstantin.projectpwj.domain.Option;
import ro.bogdanconstantin.projectpwj.repository.OptionsRepository;
import ro.bogdanconstantin.projectpwj.repository.ProductsRepository;

@Service
public class OptionService {
    private final OptionsRepository optionsRepository;
    private final ProductsRepository productRepository;


    public OptionService(OptionsRepository optionsRepository, ProductsRepository productRepository) {
        this.optionsRepository = optionsRepository;
        this.productRepository = productRepository;
    }


    public Option insert(Option option, long product){
        var persistedProduct = productRepository.findById(product).get();
        option = optionsRepository.save(option);
        persistedProduct.getOptions().add(option);
        productRepository.save(persistedProduct);
        return option;
    }
    public Option update( Option option){
        return optionsRepository.save(option);
    }

    public void delete(Long id){
        optionsRepository.deleteById(id);

    }

}

package ro.bogdanconstantin.projectpwj.controllers;

import org.springframework.web.bind.annotation.*;
import ro.bogdanconstantin.projectpwj.domain.Option;
import ro.bogdanconstantin.projectpwj.domain.ProductGroup;
import ro.bogdanconstantin.projectpwj.repository.OptionsRepository;
import ro.bogdanconstantin.projectpwj.repository.ProductsRepository;
import ro.bogdanconstantin.projectpwj.services.OptionService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OptionController {

    private final OptionService optionService;


    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @PostMapping("/options/{product}")
    Option insert(@RequestBody Option option,@PathVariable long product){
        return optionService.insert(option,product);
    }
    @PatchMapping("/options")
    Option update(@RequestBody Option option){
        return optionService.update(option);
    }
    @DeleteMapping("/options/{id}")
    void delete(@PathVariable Long id){
        optionService.delete(id);

    }

}

package ro.bogdanconstantin.projectpwj.controllers;

import org.springframework.web.bind.annotation.*;
import ro.bogdanconstantin.projectpwj.domain.Customer;
import ro.bogdanconstantin.projectpwj.repository.CustomersRepository;
import ro.bogdanconstantin.projectpwj.services.CustomerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/customers")
    Iterable<Customer> getAll() {
        return service.getAll();
    }

    @GetMapping("/customers/{id}")
    Customer getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/customers")
    Customer insert(@RequestBody Customer customer) {
        return service.insert(customer);
    }

    @PatchMapping("/customers")
    Customer update(@RequestBody Customer customer) {

        return service.update(customer);
    }

    @DeleteMapping("/customers/{id}")
    void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

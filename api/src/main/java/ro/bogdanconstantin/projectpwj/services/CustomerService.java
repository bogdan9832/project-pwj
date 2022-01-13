package ro.bogdanconstantin.projectpwj.services;

import org.springframework.stereotype.Service;
import ro.bogdanconstantin.projectpwj.domain.Customer;
import ro.bogdanconstantin.projectpwj.repository.CustomersRepository;

@Service
public class CustomerService {

    private final CustomersRepository repo;


    public CustomerService(CustomersRepository repo) {
        this.repo = repo;
    }
    public Iterable<Customer> getAll(){
        return repo.findAll();
    }
    public  Customer getById( Long id){
        return repo.findById(id).orElse(null);
    }

    public  Customer insert( Customer customer){
        return repo.save(customer);
    }
    public   Customer update( Customer customer){
        return repo.save(customer);
    }

    public  void delete( Long id){
        repo.deleteById(id);
    }

}

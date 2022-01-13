package ro.bogdanconstantin.projectpwj.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ro.bogdanconstantin.projectpwj.domain.Order;
import ro.bogdanconstantin.projectpwj.repository.OrdersRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;


    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;

    }

    public Iterable<Order> getAll(){
        return ordersRepository.findAll();
    }
    public Iterable<Order> getByCustomer( String customer,  int status){
        return ordersRepository.findOrdersByCustomerUuidAndStatus(customer,status);
    }
    public Iterable<Order> getAllInDateRange( String startDate,  String endDate) throws ParseException {
        var df = new SimpleDateFormat("yyyy-MM-dd");
        var start  = df.parse(startDate);
        var end=  df.parse(endDate);
        end.setTime(end.getTime() + 24 * 60 * 60 * 1000);
        return ordersRepository.findOrdersByTimestampBetween(start,end);
    }
    public Order insert( Order order){
        return ordersRepository.save(order);
    }

    public Order  update( Order order){
        return ordersRepository.save(order);
    }
    public void delete( Long id){
         ordersRepository.deleteById(id);

    }

}

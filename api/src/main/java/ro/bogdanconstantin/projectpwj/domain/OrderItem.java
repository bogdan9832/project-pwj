package ro.bogdanconstantin.projectpwj.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name="order_item")
public class OrderItem  extends BaseEntity {
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @NotEmpty
    @OneToMany
    @JoinColumn(name = "order_item_id")
    private List<OrderItemOption> options;
    @Min(0)
    private double price;
    @Min(0)
    private double quantity;
    public OrderItem(Product product, double price, double quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", product=" + product +
                ", Options=" + options +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public OrderItem() {


    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<OrderItemOption> getOptions() {
        return options;
    }

    public void setOptions(List<OrderItemOption> options) {
        this.options = options;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}

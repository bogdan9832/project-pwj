package ro.bogdanconstantin.projectpwj.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity

@Table(name="order_item_option")
public class OrderItemOption extends BaseEntity {
    @NotEmpty
    @ManyToOne
    @JoinColumn(name="option_id")
    private Option option;
    private double priceModifier;
    @Min(0)
    private double quantity;

    public OrderItemOption(Option option, double priceModifier, double quantity) {
        this.option = option;
        this.priceModifier = priceModifier;
        this.quantity = quantity;
    }

    public OrderItemOption() {

    }

    @Override
    public String toString() {
        return "OrderItemOptions{" +
                "id=" + id +
                ", option=" + option +
                ", priceModifier=" + priceModifier +
                ", quantity=" + quantity +
                '}';
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public double getPriceModifier() {
        return priceModifier;
    }

    public void setPriceModifier(double priceModifier) {
        this.priceModifier = priceModifier;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}

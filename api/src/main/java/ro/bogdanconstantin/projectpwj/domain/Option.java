package ro.bogdanconstantin.projectpwj.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="[option]")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Option extends BaseEntity{
    @NotEmpty
    private String name;

    private double priceModifier;

    public Option(Long id, String name, double priceModifier) {
        this.id = id;
        this.name = name;
        this.priceModifier = priceModifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceModifier() {
        return priceModifier;
    }

    public void setPriceModifier(double priceModifier) {
        this.priceModifier = priceModifier;
    }

    protected Option() {

    }


}

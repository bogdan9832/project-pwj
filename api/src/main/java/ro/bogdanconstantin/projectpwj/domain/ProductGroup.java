package ro.bogdanconstantin.projectpwj.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ro.bogdanconstantin.projectpwj.domain.dtos.ProductGroupDto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_group")
@OnDelete(action = OnDeleteAction.CASCADE)
public class ProductGroup extends BaseEntity {
    @NotEmpty
    private String name;
    @NotEmpty
    private String image;

    @OneToMany
    @JoinColumn(name = "product_group_id")
    List<Product> products = new ArrayList<>();

    public ProductGroup(String name, String image) {
        this.name = name;
        this.image = image;
    }
    public ProductGroup(long id ,String name, String image) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public ProductGroup() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public ProductGroupDto toDto() {
        return new ProductGroupDto(id,name,image);
    }
}

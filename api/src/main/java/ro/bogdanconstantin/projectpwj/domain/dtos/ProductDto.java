package ro.bogdanconstantin.projectpwj.domain.dtos;

import ro.bogdanconstantin.projectpwj.domain.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public record ProductDto(Long id, @NotEmpty String name, @NotEmpty String description,@NotEmpty String image,
                         @Min(0) double price) {

    public Product toFullClass() {
        return new Product(id, name, description, image, price);
    }

}

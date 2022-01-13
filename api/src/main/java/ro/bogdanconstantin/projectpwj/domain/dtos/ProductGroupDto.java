package ro.bogdanconstantin.projectpwj.domain.dtos;

import ro.bogdanconstantin.projectpwj.domain.ProductGroup;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public record ProductGroupDto(

        @Min(-1) Long id, @NotEmpty String name, @NotEmpty String image) {
    public ProductGroup toFullClass() {
        return new ProductGroup(id, name, image);
    }

}

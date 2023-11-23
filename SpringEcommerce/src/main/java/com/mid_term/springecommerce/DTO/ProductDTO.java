package com.mid_term.springecommerce.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mid_term.springecommerce.Models.Category;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {
    //private Long id;

    private String name;

    private String imageUrl;
    @JsonProperty("Image")
    private String Image;
    //private String description;

    private String salePrice;
    @JsonProperty("View")
    private String View;

    //private String currentQuantity;

    //private Category category;

    //private boolean is_activated;

    //private boolean is_deleted;
}

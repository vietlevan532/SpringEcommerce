package com.mid_term.springecommerce.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = { "name", "imageUrl" }))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;
 
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private int salePrice;

    private int currentQuantity;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    private boolean is_activated;

    private boolean is_deleted;
}

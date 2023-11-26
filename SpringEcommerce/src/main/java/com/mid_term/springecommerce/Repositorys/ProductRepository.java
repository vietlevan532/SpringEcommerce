package com.mid_term.springecommerce.Repositorys;

import com.mid_term.springecommerce.Models.Category;
import com.mid_term.springecommerce.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category = :category and p.is_deleted = false")
    List<Product> getProductsByCategory(@Param("category")Category category);

    @Query("SELECT p FROM Product p WHERE p.id = :id and p.is_deleted = false")
    Product getDetailProduct(@Param("id") Long id);
}

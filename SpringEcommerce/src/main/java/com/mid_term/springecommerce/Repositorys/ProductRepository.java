package com.mid_term.springecommerce.Repositorys;

import com.mid_term.springecommerce.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

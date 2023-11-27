package com.mid_term.springecommerce.Repositorys;

import com.mid_term.springecommerce.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}

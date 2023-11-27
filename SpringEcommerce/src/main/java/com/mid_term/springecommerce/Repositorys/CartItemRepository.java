package com.mid_term.springecommerce.Repositorys;

import com.mid_term.springecommerce.Models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}

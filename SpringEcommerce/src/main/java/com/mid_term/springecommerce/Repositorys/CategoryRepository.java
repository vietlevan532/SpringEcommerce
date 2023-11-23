package com.mid_term.springecommerce.Repositorys;

import com.mid_term.springecommerce.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

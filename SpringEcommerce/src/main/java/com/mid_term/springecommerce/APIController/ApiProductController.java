package com.mid_term.springecommerce.APIController;

import com.mid_term.springecommerce.Models.Product;
import com.mid_term.springecommerce.Repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products/")
public class ApiProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("get-all-products")
    public Object getAllProducts() {
        List<Product> products = productRepository.findAll();

        return Response.createSuccessResponseModel(products.size(), products);
    }
}

package com.mid_term.springecommerce.APIController;

import com.mid_term.springecommerce.Models.Category;
import com.mid_term.springecommerce.Models.Product;
import com.mid_term.springecommerce.Repositorys.CategoryRepository;
import com.mid_term.springecommerce.Repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products/")
public class ApiProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("get-all-products")
    public Object getAllProducts() {
        List<Product> products = productRepository.findAll();

        return Response.createSuccessResponseModel(products.size(), products);
    }

    //san pham theo category
    @GetMapping("get-products-by-category")
    public Object getProductsByCategory(@RequestParam String category, @RequestParam(defaultValue = "1") int pageIndex) {
        int pageSize = 12;
        System.out.println(category);
        Category c = categoryRepository.findByName(category);
        List<Product> products = productRepository.getProductsByCategory(c);

        int from = 0;
        if(pageIndex !=1) from = (pageIndex - 1) * pageSize;

        List<Product> result = products.stream().skip(from).limit(pageSize)
                .toList();

        return Response.createSuccessResponseModel(products.size(), result);
    }
}

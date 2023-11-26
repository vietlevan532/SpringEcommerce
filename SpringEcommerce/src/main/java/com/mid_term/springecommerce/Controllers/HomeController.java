package com.mid_term.springecommerce.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/", "/home"})
    public String renderIndex()
    {
        return "index";
    }

    @GetMapping("/contact")
    public String renderContact() {
        return "contact";
    }

    @GetMapping("/shop")
    public String renderShop() {
        return "shop";
    }
}

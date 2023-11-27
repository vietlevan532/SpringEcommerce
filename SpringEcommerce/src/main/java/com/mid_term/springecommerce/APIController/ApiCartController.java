package com.mid_term.springecommerce.APIController;

import com.mid_term.springecommerce.Models.Cart;
import com.mid_term.springecommerce.Models.CartItem;
import com.mid_term.springecommerce.Models.Product;
import com.mid_term.springecommerce.Models.User;
import com.mid_term.springecommerce.Repositorys.CartItemRepository;
import com.mid_term.springecommerce.Repositorys.CartRepository;
import com.mid_term.springecommerce.Repositorys.ProductRepository;
import com.mid_term.springecommerce.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("api/cart")
public class ApiCartController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam Integer quantity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getAuthorities().toString().equals("ROLE_ANONYMOUS")) {
            Product product = productRepository.getDetailProduct(productId);
            User user = userRepository.getUserByName(authentication.getName());
            addItemToCart(product, quantity, user);
        }
        return "redirect:/cart/";
    }

    private void addItemToCart(Product product, int quantity, User user) {
        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
        }
        List<CartItem> cartItems = cart.getCartItems();
        CartItem cartItem = findCartItem(cartItems, product.getId());
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setTotalPrice(quantity * product.getSalePrice());
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);
            cartItems.add(cartItem);
            cartItemRepository.save(cartItem);
        } else {
            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setTotalPrice(quantity * product.getSalePrice());
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                cartItems.add(cartItem);
                cartItemRepository.save(cartItem);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setTotalPrice(cartItem.getTotalPrice() + ( quantity * product.getSalePrice()));
                cartItemRepository.save(cartItem);
            }
        }
        cart.setCartItems(cartItems);

        int totalItems = totalItems(cart.getCartItems());
        int totalPrice = totalPrice(cart.getCartItems());

        cart.setCartItems(cartItems);
        cart.setTotalItems(totalItems);
        cart.setTotalPrice(totalPrice);

        cartRepository.save(cart);
    }

    private CartItem findCartItem(List<CartItem> cartItems, Long productId) {
        if (cartItems == null) {
            return null;
        }
        CartItem cartItem = null;

        for (CartItem item : cartItems) {
            if (Objects.equals(item.getProduct().getId(), productId)) {
                cartItem = item;
            }
        }
        return cartItem;
    }

    private int totalItems(List<CartItem> cartItems){
        int totalItems = 0;
        for(CartItem item : cartItems){
            totalItems += item.getQuantity();
        }
        return totalItems;
    }

    private int totalPrice(List<CartItem> cartItems){
        int totalPrice = 0;

        for(CartItem item : cartItems){
            totalPrice += item.getTotalPrice();
        }

        return totalPrice;
    }
}

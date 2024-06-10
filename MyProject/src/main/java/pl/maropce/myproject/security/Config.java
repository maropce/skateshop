package pl.maropce.myproject.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.maropce.myproject.appUser.AppUser;
import pl.maropce.myproject.appUser.AppUserService;
import pl.maropce.myproject.appUser.Role;
import pl.maropce.myproject.product.Product;
import pl.maropce.myproject.product.ProductService;

import java.util.Set;

@Configuration
public class Config {
    private final AppUserService appUserService;

    private final ProductService productService;
    private final BCryptPasswordEncoder passwordEncoder;

    public Config(AppUserService appUserService, ProductService productService, BCryptPasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.productService = productService;
        this.passwordEncoder = passwordEncoder;

        AppUser appUser = AppUser.builder()
                .username("maropce")
                .email("maropce@gmail.com")
                .password("maropce123")
                .roles(Set.of(Role.USER, Role.ADMIN))
                .build();


        AppUser appUser2 = AppUser.builder()
                .username("spajto")
                .email("spajto@gmail.com")
                .password("spajto123")
                .roles(Set.of(Role.USER))
                .build();




        appUserService.addUser(appUser);
        appUserService.addUser(appUser2);

        Product p1 = Product.builder()
                .name("Vans old skool")
                .description("Classic vans shoes. Very durable and comfort")
                .price(399)
                .build();

        Product p2 = Product.builder()
                .name("Vans hoodie black")
                .description("Classic vans hoodie.")
                .price(399)
                .build();

        productService.addProduct(p1);
        productService.addProduct(p2);

    }
}

package pl.maropce.myproject.shoppingCart;

import org.springframework.stereotype.Service;
import pl.maropce.myproject.appUser.AppUser;
import pl.maropce.myproject.appUser.AppUserService;
import pl.maropce.myproject.cartItem.CartItem;
import pl.maropce.myproject.cartItem.CartItemService;
import pl.maropce.myproject.product.Product;
import pl.maropce.myproject.product.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository repository;
    private final CartItemService cartItemService;
    private final ProductService productService;
    private final AppUserService appUserService;

    public ShoppingCartService(ShoppingCartRepository repository, CartItemService cartItemService, ProductService productService, AppUserService appUserService) {
        this.repository = repository;
        this.cartItemService = cartItemService;
        this.productService = productService;
        this.appUserService = appUserService;
    }

    public Set<CartItem> getAllItems(Long shoppingCartId) {
        Optional<AppUser> optionalAppUser = appUserService.findById(shoppingCartId);
        //Optional<ShoppingCart> optionalShoppingCart = repository.findById(shoppingCartId);

        if (optionalAppUser.isPresent()) {
            return optionalAppUser.map(appUser -> appUser.getShoppingCart().getCartItems()).get();
        }

        return null;
    }

    public ShoppingCart addProduct(Long id, Long productId) {
        Optional<AppUser> optionalAppUser = appUserService.findById(id);
        Optional<Product> optionalProduct = productService.getProduct(productId);

        if (optionalAppUser.isEmpty() || optionalProduct.isEmpty()) {
            return null;
        }

        AppUser appUser = optionalAppUser.get();
        ShoppingCart shoppingCart = appUser.getShoppingCart();
        Product product = optionalProduct.get();

        CartItem cartItem = CartItem.builder()
                .product(product)
                .shoppingCart(shoppingCart)
                .quantity(1)
                .build();

        cartItemService.addCartItem(cartItem);
        shoppingCart.addCartItem(cartItem);

        return appUserService.addUser(appUser).getShoppingCart();


    }
}

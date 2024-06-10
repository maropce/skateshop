package pl.maropce.myproject.shoppingCart;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maropce.myproject.cartItem.CartItem;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/api/v1/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartService service;

    public ShoppingCartController(ShoppingCartService service) {
        this.service = service;
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<Set<CartItem>> getAllItemsByShoppingCartId(@PathVariable Long id) {
        Set<CartItem> allItems = service.getAllItems(id);
        return allItems == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(allItems);
    }

    @PostMapping("{userId}/items/{productId}")
    public ResponseEntity<ShoppingCart> addProduct(@PathVariable Long userId, @PathVariable Long productId) {
        ShoppingCart shoppingCart = service.addProduct(userId, productId);

        //TODO: Zamienic ResponseEntity.ok() na created();
        return shoppingCart == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(shoppingCart);
    }
}

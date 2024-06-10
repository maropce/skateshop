package pl.maropce.myproject.cartItem;

import org.springframework.stereotype.Service;
import pl.maropce.myproject.shoppingCart.ShoppingCart;

import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepository repository;

    public CartItemService(CartItemRepository repository) {
        this.repository = repository;
    }

    public List<CartItem> getAllItemsByShoppingCart(ShoppingCart shoppingCart) {
        return repository.getCartItemsByShoppingCart(shoppingCart);
    }

    public CartItem addCartItem(CartItem cartItem) {
        return repository.save(cartItem);
    }
}

package pl.maropce.myproject.cartItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maropce.myproject.shoppingCart.ShoppingCart;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> getCartItemsByShoppingCart(ShoppingCart shoppingCart);

}

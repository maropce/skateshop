package pl.maropce.myproject.shoppingCart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maropce.myproject.cartItem.CartItem;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {


}

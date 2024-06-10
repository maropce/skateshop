package pl.maropce.myproject.shoppingCart;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.maropce.myproject.appUser.AppUser;
import pl.maropce.myproject.cartItem.CartItem;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CartItem> cartItems = new HashSet<>();

    public ShoppingCart(AppUser appUser) {
        this.appUser = appUser;
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }
}

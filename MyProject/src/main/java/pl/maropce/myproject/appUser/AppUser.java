package pl.maropce.myproject.appUser;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.maropce.myproject.shoppingCart.ShoppingCart;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ShoppingCart shoppingCart;

    private String username;
    private String email;
    private String password;
    private Set<Role> roles;
    //private Role role;


    public AppUser() {
        this.shoppingCart = new ShoppingCart(this);
    }

    @Builder
    private AppUser(String username, String email, String password, Set<Role> roles) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return roles.stream().map(role ->
                        new SimpleGrantedAuthority(role.fullName))
                .collect(Collectors.toList());

//        return Stream.of(role).map( role1 ->
//                new SimpleGrantedAuthority(role1.fullName))
//                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

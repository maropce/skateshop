package pl.maropce.myproject.appUser;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService {

    private final AppUserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public AppUser addUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRoles(Set.of(Role.USER));
        return repository.save(appUser);
    }

    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    public Optional<AppUser> findById(Long id) {
        return repository.findById(id);
    }
}

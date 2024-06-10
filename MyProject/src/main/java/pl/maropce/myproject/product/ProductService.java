package pl.maropce.myproject.product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    public Optional<Product> getProduct(Long id) {
        return repository.findById(id);
    }



    public Optional<Product> updateProduct(Long id, Product newProduct) {
        Optional<Product> productOptional = repository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            updateProduct(product, newProduct);
            repository.save(product);

            return Optional.of(product);
        } else {
            return Optional.empty();
        }
    }

    private Product updateProduct(Product product, Product newProduct) {
        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setPrice(newProduct.getPrice());

        return product;
    }
}

package com.amazon.api;

import com.amazon.api.model.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    public UserDTO getUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return new UserDTO(user.getId(), user.getEmail(), user.getRole(), user.getTokens());
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsById(String id) {
        return productRepository.findAllById(Integer.valueOf(id));
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(String storeId, String productId) {
        productRepository.deleteByIdAndStoreId(Integer.valueOf(productId), Integer.valueOf(storeId));
    }

    public Store getStore(String id) {
        return storeRepository.findById(Integer.valueOf(id)).orElseThrow();
    }

    public void saveStore(StoreDTO store) {
        Store newStore = Store.builder()
                .name(store.getName())
                .admin(store.getAdmin())
                .build();
        storeRepository.save(newStore);
    }
}

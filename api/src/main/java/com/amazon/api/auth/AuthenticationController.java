package com.amazon.api.auth;

import com.amazon.api.ProductRepository;
import com.amazon.api.Service;
import com.amazon.api.model.Product;
import com.amazon.api.model.Store;
import com.amazon.api.model.StoreDTO;
import com.amazon.api.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final Service service;
    @PostMapping("/user/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/user/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticate
    ){
        return ResponseEntity.ok(authenticationService.authenticate(authenticate));

    }
    @GetMapping("/user")
    public ResponseEntity<UserDTO> getUser (
            @RequestBody String email
    ){
        return ResponseEntity.ok(service.getUser(email));
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(service.getProducts());
    }

    @GetMapping("/store/{id}/product")
    public ResponseEntity<List<Product>> getProductsByStore (
            @RequestParam String id
    ) {
        return ResponseEntity.ok(service.getProductsById(id));
    }

    @PostMapping("/store/{id}/product")
    public ResponseEntity<Product> addProduct (
            @RequestParam String id,
            @RequestBody Product product
    ) {
        service.saveProduct(product);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(id)
                        .toUri())
                        .build();
    }

    @DeleteMapping("store/{storeId}/product/{productId}")
    public ResponseEntity deleteProduct (
            @RequestParam String storeId,
            @RequestParam String productId
    ) {
        service.deleteProduct(storeId, productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/store/{id}")
    public ResponseEntity getStore (
            @RequestParam String id
    ) {
        return ResponseEntity.ok(service.getStore(id));
    }

    @PostMapping("/store/register")
    public ResponseEntity addStore (
            @RequestBody StoreDTO store

    ) {
        System.out.println("KOM INN I POSTMAPPINGA");
        service.saveStore(store);
        return ResponseEntity.created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/" + store.getName())
                                .buildAndExpand(store.getName())
                                .toUri())
                                .build();

    }
}

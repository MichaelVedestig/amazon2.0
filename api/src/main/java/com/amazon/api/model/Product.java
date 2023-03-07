package com.amazon.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonBackReference
    public Store store;
    private double price;
    private int quantity;
    private String category;

}

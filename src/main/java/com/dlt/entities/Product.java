package com.dlt.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
    
    @Id
    @Column(name = "product_id")
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    private boolean status = true;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;
}

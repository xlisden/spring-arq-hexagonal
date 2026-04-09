package com.davinchicoder.spring_data_jpa_cero_a_experto.review.infrastructure;

import com.davinchicoder.spring_data_jpa_cero_a_experto.product.infrastructure.database.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "reviews")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private Instant product_created_at;

}

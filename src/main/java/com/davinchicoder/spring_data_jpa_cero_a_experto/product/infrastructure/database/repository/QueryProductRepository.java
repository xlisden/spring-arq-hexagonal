package com.davinchicoder.spring_data_jpa_cero_a_experto.product.infrastructure.database.repository;

import com.davinchicoder.spring_data_jpa_cero_a_experto.product.infrastructure.database.entity.ProductEntity;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QueryProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    Optional<ProductEntity> findByNameContaining(String name);

    List<ProductEntity> findAllByPriceBetween(Double priceAfter, Double priceBefore);

    @Query(value = """
            SELECT p
            FROM ProductEntity p
            WHERE p.name LIKE CONCAT('%', :name, '%')
            OR p.description LIKE CONCAT('%', :description, '%')
            OR p.price BETWEEN :priceAfter AND :priceBefore
            """
    )
    List<ProductEntity> findProductsDetails(String name, String description, Double priceAfter, Double priceBefore);

    @QueryHints({
            @QueryHint(name = "hibernate.lock.timeout", value = "0")
    })
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<ProductEntity> findTop50ByName(String name);

    boolean existsByName(String name);

    long countByPrice(Double price);

    Page<ProductEntity> findAll(Specification<ProductEntity> specification, Pageable pageable);

    @EntityGraph(attributePaths = {"productDetail", "reviews", "categories"})
    Optional<ProductEntity> findById(Long id);
}

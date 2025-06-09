package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE p.id = :id and p.status = 1")
    Optional<Product> findByStatus(@Param("id") Long id);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.productDetails WHERE p.id = :id")
    Optional<Product> findByIdWithProductDetails(@Param("id") Long id);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.productImages WHERE p.id = :id")
    Optional<Product> findByIdWithProductImages(@Param("id") Long id);

    @Query("SELECT p FROM Product p " +
            "LEFT JOIN FETCH p.productCategories pc " +
            "LEFT JOIN FETCH pc.category " +
            "WHERE p.id = :id")
    Optional<Product> findByIdWithCategories(@Param("id") Long id);

    @Query("SELECT p FROM Product p where p.status = 1 order by p.createdDate desc ")
    Page<Product> findAllWithJPQL(Pageable pageable);

}

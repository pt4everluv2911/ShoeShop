package com.example.shoeshop.Repository;

import com.example.shoeshop.Model.ProductDetailImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailImageRepository extends JpaRepository<ProductDetailImage, Integer>   {

    @Query("SELECT DISTINCT(p.mainImg) FROM ProductDetailImage p WHERE p.shopProductDetail.id = :id")
    String getMainImg(@Param("id") Long id);

    @Query("SELECT p.addedImg FROM ProductDetailImage p WHERE p.shopProductDetail.id = :id")
    List<String> getAddedImg(@Param("id") Long id);
}

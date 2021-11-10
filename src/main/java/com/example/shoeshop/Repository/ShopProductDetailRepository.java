package com.example.shoeshop.Repository;

import com.example.shoeshop.Model.ShopProductDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopProductDetailRepository extends PagingAndSortingRepository<ShopProductDetail, Long> {

    @Query("select s from ShopProductDetail s where s.type = :type")
    List<ShopProductDetail> getRelatedProduct(@Param("type") String type);
}

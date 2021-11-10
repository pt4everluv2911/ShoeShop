package com.example.shoeshop.Repository;

import com.example.shoeshop.Model.ShopProductDetail;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageShopProductRepository extends PagingAndSortingRepository<ShopProductDetail, Integer> {

    @Query("SELECT s FROM ShopProductDetail s WHERE s.sale > 0 ")
    List<ShopProductDetail> getShopProductDetailSaleList();

}

package com.example.shoeshop.Service;

import com.example.shoeshop.Model.ShopProductDetail;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ShopProductDetailService {

    Page<ShopProductDetail> listAll(Integer pageToken);

    List<ShopProductDetail> getPageProduct(Integer pageToken, Integer pageSize, String sortBy);

    List<ShopProductDetail> sortByDesc(Integer pageToken, String sortBy);

    List<ShopProductDetail> sortBySale(Integer pageToken);

    ShopProductDetail prepareShopProductDataForSave(String h2, Integer price,
                                                    String brand, String descript,
                                                    String color, String specification,
                                                    String size, Integer quantity,
                                                    Integer sale, String type);

    ShopProductDetail save(ShopProductDetail shopProductDetail);

    Optional<ShopProductDetail> findById(Long id);

    List<String> getSingleSize(String size);
}

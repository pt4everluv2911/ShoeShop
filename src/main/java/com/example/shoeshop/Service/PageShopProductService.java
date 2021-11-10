package com.example.shoeshop.Service;

import com.example.shoeshop.Model.ShopProductDetail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PageShopProductService {

    Page<ShopProductDetail> getPageBase(Integer pageToken, Integer pageSize, String sortBy);
    int getTotalPage(Integer pageToken, Integer pageSize, String sortBy);
    List<ShopProductDetail> getShopProductDetailList(Integer pageToken, Integer pageSize, String sortBy);
    List<ShopProductDetail> sortByDescList(Integer pageToken, Integer pageSize, String sortBy);
    List<ShopProductDetail> sortBySaleList(Integer pageToken);
    List<ShopProductDetail> getSortedShopProductDetailList(Integer pageToken, String SelectSort);
    List<String> getMainImgList(List<ShopProductDetail> shopProductDetailList);
    List<ShopProductDetail> getRelatedProduct(String type);

}

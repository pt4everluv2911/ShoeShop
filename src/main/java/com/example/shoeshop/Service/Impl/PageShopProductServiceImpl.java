package com.example.shoeshop.Service.Impl;

import com.example.shoeshop.Model.ShopProductDetail;
import com.example.shoeshop.Repository.PageShopProductRepository;
import com.example.shoeshop.Repository.ProductDetailImageRepository;
import com.example.shoeshop.Repository.ShopProductDetailRepository;
import com.example.shoeshop.Service.PageShopProductService;
import com.example.shoeshop.Util.ListPage;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PageShopProductServiceImpl implements PageShopProductService {
    private final PageShopProductRepository pageRepo;
    private final ProductDetailImageRepository productDetailImageRepo;
    private final ShopProductDetailRepository shopProductDetailRepo;

    private static final Integer pageSize = 9;

    @Override
    public Page<ShopProductDetail> getPageBase(Integer pageToken, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageToken - 1, pageSize, Sort.by(sortBy));
        return pageRepo.findAll(pageable);
    }

    @Override
    public int getTotalPage(Integer pageToken, Integer pageSize, String sortBy) {
        Page<ShopProductDetail> temp = getPageBase(pageToken, pageSize, sortBy);

        int totalPage = temp.getTotalPages();
        return totalPage;
    }

    // FIXME: write a function to shorten the if else for checking whether the Page has content or not
    @Override
    public List<ShopProductDetail> getShopProductDetailList(Integer pageToken, Integer pageSize, String sortBy) {
        Page<ShopProductDetail> shopProductDetailList = getPageBase(pageToken, pageSize, sortBy);

        if (shopProductDetailList.hasContent())
            return shopProductDetailList.getContent();
        else
            return new ArrayList<>();
    }

    @Override
    public List<ShopProductDetail> sortByDescList(Integer pageToken, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageToken - 1, pageSize, Sort.by(sortBy).descending());
        Page<ShopProductDetail> shopProductDetailDescList = pageRepo.findAll(pageable);

        if (shopProductDetailDescList.hasContent())
            return shopProductDetailDescList.getContent();
        else
            return new ArrayList<>();
    }

    @Override
    public List<ShopProductDetail> sortBySaleList(Integer pageToken) {
        List<ShopProductDetail> ShopProductDetailSaleList = pageRepo.getShopProductDetailSaleList();

        ListPage listPage = new ListPage();

        return listPage.getListPage(ShopProductDetailSaleList, pageToken);
    }

    @Override
    public List<ShopProductDetail> getSortedShopProductDetailList(Integer pageToken, String SelectSort) {
        List<ShopProductDetail> sortedShopProductDetailList = new ArrayList<>();

        switch (SelectSort) {
            // xap xeo gia tu cao den thap
            case "priceDesc":
                sortedShopProductDetailList = sortByDescList(pageToken, pageSize, "price");
                break;
            // xap xep gia tu thap den cao
            case "price":
                sortedShopProductDetailList = getShopProductDetailList(pageToken, pageSize, "price");
                break;
            // xap xep hang dang sale theo thu tu thu thap den cao
            case "sale":
                sortedShopProductDetailList = sortBySaleList(pageToken);
                break;
            // xap xep hang moi
            default:
                sortedShopProductDetailList = getShopProductDetailList(pageToken, pageSize, "id");
        }

        return sortedShopProductDetailList;
    }

    @Override
    public List<String> getMainImgList(List<ShopProductDetail> shopProductDetailList) {
        List<String> mainImgList = new ArrayList<>();

        for (ShopProductDetail temp : shopProductDetailList) {
            String temp1 = new String();
            temp1 = productDetailImageRepo.getMainImg(temp.getId());
            mainImgList.add(temp1);
        }
        return mainImgList;
    }

    @Override
    public List<ShopProductDetail> getRelatedProduct(String type){
        return shopProductDetailRepo.getRelatedProduct(type);
    }
}

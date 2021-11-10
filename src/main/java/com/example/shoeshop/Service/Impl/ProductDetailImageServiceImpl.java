package com.example.shoeshop.Service.Impl;

import com.example.shoeshop.Repository.ProductDetailImageRepository;
import com.example.shoeshop.Service.ProductDetailImageService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductDetailImageServiceImpl implements ProductDetailImageService {
    private final ProductDetailImageRepository productDetailImageRepo;

    public String getMainImg(@Param("id") Long id){
        String mainImg = productDetailImageRepo.getMainImg(id);
        return  mainImg;
    }

    public List<String> getAddedImg(@Param("id") Long id){
        List<String> addedImgList = productDetailImageRepo.getAddedImg(id);
        return addedImgList;
    }

}

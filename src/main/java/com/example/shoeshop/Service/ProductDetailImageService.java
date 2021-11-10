package com.example.shoeshop.Service;

import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDetailImageService {

    String getMainImg(@Param("id") Long id);
    List<String> getAddedImg(@Param("id") Long id);

}

package com.example.shoeshop.Service;


import com.example.shoeshop.Model.ProductDetailImage;
import com.example.shoeshop.Model.ShopProductDetail;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

public interface FileUploadService {
    void saveFileToHardware(String brand, MultipartFile multipartFile) throws IOException;
    void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException;
    String setUpImgUrl(String brand,  MultipartFile multipartFile);
    String setUpUploadDir(String brand);
    Set<ProductDetailImage>
    SetProductDetailImageforSaving(Set<ProductDetailImage> productDetailImageSet, MultipartFile file, String brand, String urlMainImage, ShopProductDetail shopProductDetail);
}

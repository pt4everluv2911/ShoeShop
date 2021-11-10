package com.example.shoeshop.Service.Impl;

import com.example.shoeshop.Model.ProductDetailImage;
import com.example.shoeshop.Model.ShopProductDetail;
import com.example.shoeshop.Service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public void saveFileToHardware(String brand, MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String upLoadDir = setUpUploadDir(brand);
        saveFile(upLoadDir, fileName, multipartFile);

    }

    @Override
    public void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    @Override
    public String setUpImgUrl(String brand, MultipartFile multipartFile){

        String ImgUrl;
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        if (brand.equals("Addidas") || brand.equals("Nike") || brand.equals("ReeBook"))
            ImgUrl = "./img/"+ brand +"/" + fileName;
        else
            ImgUrl = "./img/" + fileName;

        return ImgUrl;
    }

    @Override
    public String setUpUploadDir(String brand){
        String upLoadDir = "E:\\phuc\\WEB\\int\\ShoeShop\\src\\main\\resources\\static\\img\\" + brand;

        return upLoadDir;
    }

    @Override
    public Set<ProductDetailImage> SetProductDetailImageforSaving(Set<ProductDetailImage> productDetailImageSet, MultipartFile file, String brand, String urlMainImage, ShopProductDetail shopProductDetail){
        //prepare set of ProductDetailImage
        ProductDetailImage productDetailImage = new ProductDetailImage();

        //prepare data
        String urlAddedImage = setUpImgUrl(brand,file);
        // set data to productDetailImage
        productDetailImage.setAddedImg(urlAddedImage);
        productDetailImage.setMainImg(urlMainImage);
        productDetailImage.setShopProductDetail(shopProductDetail);

        productDetailImageSet.add(productDetailImage);
        return productDetailImageSet;
    }
}

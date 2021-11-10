package com.example.shoeshop.Controller;

import com.example.shoeshop.Model.HomeSlide;
import com.example.shoeshop.Model.ProductDetailImage;
import com.example.shoeshop.Model.ShopProductDetail;
import com.example.shoeshop.Service.FileUploadService;
import com.example.shoeshop.Service.HomeSlideService;
import com.example.shoeshop.Service.ShopProductDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;



@Controller
@AllArgsConstructor
public class AdminController {

    private final HomeSlideService homeSlideService;
    private final ShopProductDetailService shopService;
    private final FileUploadService fileUploadService;


    @GetMapping("/adjust-home-slide")
    public String showAdjustHomeSlide() {
        return "/admin/adjustHomeSlide";
    }

    @PostMapping(params = "submit", value = "/adjust-home-slide")
    public String getSlideInfo(@RequestParam("inputH1") String inputH1,
                               @RequestParam("inputH2") String inputH2,
                               @RequestParam("inputParagrath") String inputParagrath,
                               @RequestParam(value = "file") MultipartFile multipartFile,
                               @RequestParam("pageSelection") Long pageSelection,
                               @RequestParam("brand") String brand,
                               ModelMap modelMap) throws IOException {

        fileUploadService.saveFileToHardware(brand, multipartFile);

        String ImgUrl = fileUploadService.setUpImgUrl(brand, multipartFile);
        HomeSlide homeSlide = homeSlideService.prepareHomeSlideForSave(pageSelection, ImgUrl, inputH1, inputH2, inputParagrath);
        homeSlideService.update(homeSlide, pageSelection);

        modelMap.addAttribute("message", "success");

        return "./admin/adjustHomeSlide";
    }

    @GetMapping("/adjust-shop-product")
    public String showAdjustShopProductInfo() {
        return "/admin/adjustShopProductDetailInfo";
    }


        /* Todo: viết thêm user case
            1. cho nhập file đuôi gì như jpg, png ... vv
            2. cho nhập file với kích thước bao nhiêu ..
        */
    @PostMapping("/adjust-shop-product")
    public String getShopProductInfo(@RequestParam("h2") String h2,
                                     @RequestParam("brand") String brand,
                                     @RequestParam("descript") String descript,
                                     @RequestParam("color") String color,
                                     @RequestParam("specification") String specification,
                                     @RequestParam("size") String size,
                                     @RequestParam("sale") Integer sale,
                                     @RequestParam("price") Integer price,
                                     @RequestParam("quantity") Integer quantity,
                                     @RequestParam("file") MultipartFile[] file, // chứa những file added img
                                     @RequestParam("fileMain") MultipartFile fileMain,// chứa file của main img
                                     @RequestParam("type") String type,
                                     ModelMap modelMap) throws IOException {
        // save shop product detail in table shopproductdetail
        ShopProductDetail shopProductDetail = shopService.prepareShopProductDataForSave(h2, price, brand, descript, color, specification, size, quantity, sale, type);
        shopService.save(shopProductDetail);

        //save main img xuong hardware
        String urlMainImage = fileUploadService.setUpImgUrl(brand, fileMain);
        fileUploadService.saveFileToHardware(brand, fileMain);

        // prepare for saving set of product detail
        Set<ProductDetailImage> productDetailImageSet = new HashSet<>();

        // save added img
        for (MultipartFile f : file) {
            fileUploadService.saveFileToHardware(brand, f);
            productDetailImageSet = fileUploadService.SetProductDetailImageforSaving(productDetailImageSet, f,brand,urlMainImage,shopProductDetail);
        }
        // giống như cart set setitems để lưu
        shopProductDetail.setProductDetailImageSet(productDetailImageSet);
        shopService.save(shopProductDetail);

        modelMap.addAttribute("message", "success");
        return "/admin/adjustShopProductDetailInfo";
    }
}

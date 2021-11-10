package com.example.shoeshop.Controller;

import com.example.shoeshop.Model.CartDto;
import com.example.shoeshop.Model.HomeSlide;
import com.example.shoeshop.Model.ShopProductDetail;
import com.example.shoeshop.Service.HomeSlideService;
import com.example.shoeshop.Service.PageShopProductService;
import com.example.shoeshop.Service.ProductDetailImageService;
import com.example.shoeshop.Service.ShopProductDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController {

    private final HomeSlideService homeSlideService;
    private final PageShopProductService pageService;
    private final ProductDetailImageService productDetailImageService;
    private final ShopProductDetailService shopProductDetailService;

    @GetMapping("/")
    public String showHome(ModelMap modelMap){
        List<HomeSlide> homeSlides = homeSlideService.getAllHomeSlide();
        modelMap.addAttribute("homeSlides", homeSlides);
        return "user/index";
    }

    @GetMapping("/shop.html")
    public String showShop(@RequestParam(value = "pageToken", defaultValue = "1") Integer pageToken,
                           @RequestParam(value = "SelectSort", required = false, defaultValue = "id") String SelectSort,
                           ModelMap modelMap) {

        int totalPages = pageService.getTotalPage(pageToken, 9, "id");
        String saveSort = SelectSort;
        List<ShopProductDetail> sortedShopProductDetailList = pageService.getSortedShopProductDetailList(pageToken, SelectSort);
        List<String> mainImgList = pageService.getMainImgList(sortedShopProductDetailList);

        modelMap.addAttribute("totalPages", totalPages);
        modelMap.addAttribute("saveSort", saveSort);
        modelMap.addAttribute("sortedShopProductDetailList", sortedShopProductDetailList);
        modelMap.addAttribute("mainImgList", mainImgList);

        return "user/shop";
    }

    @GetMapping("/shop-single.html")
    public String showShopSingle(@RequestParam(value = "id") Long id,
                                 ModelMap modelMap){

        // prepare img, product info to be shown in single shop
        String mainImg = productDetailImageService.getMainImg(id);
        List<String> addedImgList = productDetailImageService.getAddedImg(id);// for slide
        Optional<ShopProductDetail> shopProductDetail = shopProductDetailService.findById(id);//  product detail
        List<String> singleSizeList = shopProductDetailService.getSingleSize(shopProductDetail.get().getSize());// each size of a product

        //relative product
        List<ShopProductDetail> relatedShopProductDetailList = pageService.getRelatedProduct(shopProductDetail.get().getType());
        List<String> mainRelatedImgList = pageService.getMainImgList(relatedShopProductDetailList);

        //save info for cart
        CartDto cartDto = new CartDto();
        cartDto.setMainImg(mainImg);
        cartDto.setH2(shopProductDetail.get().getH2());
        cartDto.setDescription(shopProductDetail.get().getDescript());
        cartDto.setPrice(shopProductDetail.get().getPrice());
        modelMap.addAttribute("cartDto", cartDto);

        modelMap.addAttribute("mainImg", mainImg);
        modelMap.addAttribute("addedImgList", addedImgList);
        modelMap.addAttribute("shopProductDetail", shopProductDetail);
        modelMap.addAttribute("singleSizeList", singleSizeList);
        modelMap.addAttribute("relatedShopProductDetailList", relatedShopProductDetailList);
        modelMap.addAttribute("mainRelatedImgList", mainRelatedImgList);
        return "/user/shop-single";
    }

    @PostMapping("/shop-single.html")
    public String getCartInfo(@ModelAttribute("cartDto") CartDto cartDto) {
        System.out.println(cartDto);

        return "/user/cart";
    }


    @GetMapping("/cart")
    public String showCart(ModelMap modelMap, CartDto cartDto){

        return "/user/cart";
    }

}

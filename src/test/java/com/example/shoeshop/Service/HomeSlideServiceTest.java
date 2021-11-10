package com.example.shoeshop.Service;

import com.example.shoeshop.Model.HomeSlide;
import com.example.shoeshop.Repository.HomeSlideRepository;
import com.example.shoeshop.Service.Impl.HomeSlideServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HomeSlideServiceTest {

    HomeSlideService  homeSlideService;

    @Mock
    HomeSlideRepository homeSlideRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        homeSlideService = new HomeSlideServiceImpl(homeSlideRepository);
    }

    @Test
    void getAllHomeSlides() throws Exception{
        HomeSlide homeSlideTest = new HomeSlide();
        List<HomeSlide> homeSlidesData = new ArrayList<>();
        homeSlidesData.add(homeSlideTest);

        when(homeSlideService.getAllHomeSlide()).thenReturn(homeSlidesData);

        List<HomeSlide> homeSlides = homeSlideService.getAllHomeSlide();

        assertEquals(homeSlides.size(),1);
        verify(homeSlideRepository, times(1)).findAll();
    }

    @Test
    void update() {
    }

    @Test
    void prepareHomeSlideForSave() {
    }

    @Test
    void setUPImgUrl() {
//        String brand = "Nike";
//        String file = "abc.jpg";
//        String ImgUrl = "./img/"+ "Nike" +"/" + "abc.jpg";
//
//        when(homeSlideService.setUPImgUrl(brand, file)).thenReturn(ImgUrl);
//
//        String ImgUrlReal = homeSlideService.setUPImgUrl(brand,file);
//
//        assertEquals(ImgUrl, ImgUrlReal);

    }

    @Test
    void setUpUploadDir() {
    }
}
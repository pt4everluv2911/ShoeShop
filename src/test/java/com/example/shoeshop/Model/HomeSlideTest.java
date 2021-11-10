package com.example.shoeshop.Model;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeSlideTest {

    HomeSlide homeSlide;

    @Test
    void getImgUrl() {
        HomeSlide homeSlide = new HomeSlide();
        String imgUrl = "/asd.jpg";
        homeSlide.setImgUrl("/asd.jpg");
        assertEquals(imgUrl, homeSlide.getImgUrl());
    }

    @Test
    void getH1() {
    }
}
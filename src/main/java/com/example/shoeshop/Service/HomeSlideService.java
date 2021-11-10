package com.example.shoeshop.Service;

import com.example.shoeshop.Model.HomeSlide;

import java.util.List;

public interface HomeSlideService {

    List<HomeSlide> getAllHomeSlide();
    void update(HomeSlide homeSlide, Long id);
    HomeSlide prepareHomeSlideForSave(Long pageSelection, String imgUrl, String h1, String h2, String paragrath);

}

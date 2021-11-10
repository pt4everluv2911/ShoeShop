package com.example.shoeshop.Service.Impl;

import com.example.shoeshop.Model.HomeSlide;
import com.example.shoeshop.Repository.HomeSlideRepository;
import com.example.shoeshop.Service.HomeSlideService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class HomeSlideServiceImpl implements HomeSlideService {
    private final HomeSlideRepository homeSlideRepository;

    @Override
    public List<HomeSlide> getAllHomeSlide(){return homeSlideRepository.findAll();}

    @Override
    public void update(HomeSlide homeSlide, Long id){
        HomeSlide updateData = homeSlideRepository.findById(id).orElse(homeSlideRepository.save(homeSlide));// updateData là data đã lưu trong database

        updateData.setId(homeSlide.getId());
        updateData.setImgUrl(homeSlide.getImgUrl());
        updateData.setH1(homeSlide.getH1());
        updateData.setH2(homeSlide.getH2());
        updateData.setParagrath(homeSlide.getParagrath());
    }

    @Override
    public HomeSlide prepareHomeSlideForSave(Long pageSelection, String imgUrl, String h1, String h2, String paragrath){

        HomeSlide homeSlide = new HomeSlide();

        homeSlide.setId(pageSelection);
        homeSlide.setImgUrl(imgUrl);
        homeSlide.setH1(h1);
        homeSlide.setH2(h2);
        homeSlide.setParagrath(paragrath);

        return homeSlide;
    }

}

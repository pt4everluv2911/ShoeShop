package com.example.shoeshop.Repository;

import com.example.shoeshop.Model.HomeSlide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HomeSlideRepository  extends JpaRepository<HomeSlide, Long> {

}

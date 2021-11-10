package com.example.shoeshop.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CartDto")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    @Id
    private String mainImg;

    private String h2;
    private String description;
    private Integer price;
    private String size;
    private Long quantity;

}

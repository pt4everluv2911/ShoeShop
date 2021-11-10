package com.example.shoeshop.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ProductDetailImage")
public class ProductDetailImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Id;

    private String mainImg;
    private String addedImg;

    @ManyToOne
    @JoinColumn(name = "ShopProductDetailId", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    private ShopProductDetail shopProductDetail;

}

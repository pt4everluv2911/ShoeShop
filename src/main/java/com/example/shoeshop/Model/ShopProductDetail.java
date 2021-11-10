package com.example.shoeshop.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ShopProductDetail")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShopProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String h2;
    private Integer price;
    private String brand;
    private String descript;
    private String color;
    private String specification;
    private String size;
    private Integer quantity;
    private Integer sale;
    private String type;

    @OneToMany(mappedBy = "shopProductDetail", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ProductDetailImage> productDetailImageSet;

    public Set<ProductDetailImage> getProductDetailImageSet() {
        return productDetailImageSet;
    }

    public void setProductDetailImageSet(Set<ProductDetailImage> productDetailImageSet) {
        this.productDetailImageSet = productDetailImageSet;
    }
}

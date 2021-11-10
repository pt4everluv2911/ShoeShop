package com.example.shoeshop.Service.Impl;

import com.example.shoeshop.Model.ShopProductDetail;
import com.example.shoeshop.Repository.ShopProductDetailRepository;
import com.example.shoeshop.Service.ShopProductDetailService;
import com.example.shoeshop.Util.ListPage;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShopProductDetailServiceImpl implements ShopProductDetailService {
    private final ShopProductDetailRepository shopProductDetailRepo;

    @PersistenceContext
    private EntityManager en;

    // module used to get pageable for getting the totalItems, totalPages
    @Override
    public Page<ShopProductDetail> listAll(Integer pageToken){
        Pageable pageable = PageRequest.of((pageToken-1), 9);
        return shopProductDetailRepo.findAll(pageable);
    }

    // get the list of product sort by sortBy attribute for showing list product data for Shop view
    @Override
    public List<ShopProductDetail> getPageProduct(Integer pageToken, Integer pageSize, String sortBy){
        Pageable pageable = PageRequest.of(pageToken, pageSize, Sort.by(sortBy));
        Page<ShopProductDetail>  listItemsOfPage = shopProductDetailRepo.findAll(pageable);
        if (listItemsOfPage.hasContent())
            return listItemsOfPage.getContent();
        else
            return new ArrayList<>();
    }

    // the same function with getPageProduct sort with descendent
    @Override
    public List<ShopProductDetail> sortByDesc( Integer pageToken, String sortBy){
        Pageable pageable = PageRequest.of(pageToken, 9, Sort.by(sortBy).descending());
        Page<ShopProductDetail>  listItemsOfPage = shopProductDetailRepo.findAll(pageable);
        if (listItemsOfPage.hasContent())
            return listItemsOfPage.getContent();
        else
            return new ArrayList<>();
    }

    // module used to get the list of product which sale > 0. sort by descendant
    @Override
    public List<ShopProductDetail> sortBySale(Integer pageToken){
        String sql = "select s from ShopProductDetail s where s.sale > 0 order by s.sale desc ";
        Query query = en.createQuery(sql);
        try {
            List<ShopProductDetail> list = query.getResultList();
            ListPage listPage = new ListPage();
            return  listPage.getListPage(list, pageToken);

        }catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public
    ShopProductDetail prepareShopProductDataForSave(String h2, Integer price,
                                                    String brand, String descript,
                                                    String color, String specification,
                                                    String size, Integer quantity,
                                                    Integer sale, String type){
        ShopProductDetail shopProductDetail = new ShopProductDetail();

        shopProductDetail.setH2(h2);
        shopProductDetail.setPrice(price);
        shopProductDetail.setBrand(brand);
        shopProductDetail.setDescript(descript);
        shopProductDetail.setColor(color);
        shopProductDetail.setSpecification(specification);
        shopProductDetail.setSize(size);
        shopProductDetail.setQuantity(quantity);
        shopProductDetail.setSale(sale);
        shopProductDetail.setType(type);

        return shopProductDetail;
    }

    @Override
    public ShopProductDetail save(ShopProductDetail shopProductDetail){return shopProductDetailRepo.save(shopProductDetail);}

    @Override
    public Optional<ShopProductDetail> findById(Long id){
        Optional<ShopProductDetail>  shopProductDetail = shopProductDetailRepo.findById(id);
        return  shopProductDetail;
    }

    @Override
    public List<String> getSingleSize(String size){
        List<String> singleSizeList = new ArrayList<>();
        String[] temp = size.split(",");

        for(String temp1: temp){
            singleSizeList.add(temp1);
        }
        return  singleSizeList;
    }
}

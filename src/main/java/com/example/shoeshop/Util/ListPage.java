package com.example.shoeshop.Util;

import com.example.shoeshop.Model.ShopProductDetail;

import java.util.ArrayList;
import java.util.List;
// this class used to contain list of sorted page
public class ListPage {

    // get a page with 9 items
    public List<ShopProductDetail> getListPage(List<ShopProductDetail> list, Integer p) {
        int sizeOfList = list.size();
        System.out.println("this is size of list :" +sizeOfList);
        int start = (p - 1) * 9;
        System.out.println("this is start :" +start);
        int end = (p * 9) - 1;
        System.out.println("this is end :" +end);
        List<ShopProductDetail> res = new ArrayList<>();

        if (sizeOfList > end) {
            for (int i = start; i <= end; i++) {
                ShopProductDetail a = list.get(i);
                res.add(a);
                System.out.println("this is 1 :");
            }
        } else {
            for (int i = start; i < sizeOfList; i++) {
                ShopProductDetail a = list.get(i);
                res.add(a);
                System.out.println("this is 2 :");
            }
        }
        return res;
    }

    public Integer getTotalPages(List<ShopProductDetail> list){
        return (list.size()/9) + 1;
    }

}

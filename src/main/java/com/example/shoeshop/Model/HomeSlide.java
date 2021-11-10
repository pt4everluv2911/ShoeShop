package com.example.shoeshop.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="homeslide")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HomeSlide {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String imgUrl;
    private String h1;
    private String h2;
    private String paragrath;
}

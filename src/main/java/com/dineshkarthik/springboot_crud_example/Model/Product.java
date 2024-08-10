package com.dineshkarthik.springboot_crud_example.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product_Table")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private int quantity;
    private double price;
}

// things to work on
// 1) first make name & price mandatory fields and make quantity default as 1
// 2) prepare to tackle addProducts issue what if 2 products work and 1 product fail, but still api should work with the 2 products.
// 3) try to minimize HttpMessageNotReadableException

package com.dineshkarthik.springboot_crud_example.Repository;

import com.dineshkarthik.springboot_crud_example.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findByName(String name);
}

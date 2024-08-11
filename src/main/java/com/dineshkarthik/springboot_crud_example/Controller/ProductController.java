package com.dineshkarthik.springboot_crud_example.Controller;

import com.dineshkarthik.springboot_crud_example.Model.Product;
import com.dineshkarthik.springboot_crud_example.Service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crudOperations")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService service;

    @GetMapping("/welcome")
    public String Welcome(){
        return "Welcome this endpoint is not secure";
    }

    @GetMapping("/secured")
    public String secured(){
        return "Hello, Secured";
    }

    @PostMapping("/addProduct")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Product addProduct(@RequestBody Product product){
        logger.info("Add Product endpoint called with data: {}", product);
        return service.saveProduct(product);
    }


    @PostMapping("/addProducts")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> addProducts(@RequestBody List<Product> products){
        return service.saveProducts(products);
    }

    @GetMapping("/products")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Product> findAllProducts(){
        return service.getProducts();
    }

    @GetMapping("/productById/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product findProductById(@PathVariable int id){
        return service.getProductById(id);
    }

    @GetMapping("/productByName")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product findProductByName(@RequestParam String name){
        return service.getProductByName(name);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Product updateProduct(@RequestBody Product product){
        return service.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteProduct(@PathVariable int id){
        return service.deleteProduct(id);
    }


}

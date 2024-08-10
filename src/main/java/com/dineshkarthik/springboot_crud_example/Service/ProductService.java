package com.dineshkarthik.springboot_crud_example.Service;

import com.dineshkarthik.springboot_crud_example.Model.Product;
import com.dineshkarthik.springboot_crud_example.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product){
        return repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products){
        return repository.saveAll(products);
    }

    public List<Product> getProducts(){
        return repository.findAll();
    }

    public Product getProductById(int id){
        return repository.findById(id).orElse(null);
    }

    public Product getProductByName(String name){
        return repository.findByName(name);
    }

    public String deleteProduct(int id){
        Product existingProduct = repository.findById(id).orElse(null);
        if(existingProduct != null){
            repository.deleteById(id);
            return "Product removed !! "+id;
        }
        return "Product is already deleted";
    }



    public Product updateProduct(Product product) {

        if(product == null){
            throw new HttpMessageNotReadableException("Given Input is Null");
        }
        if(product.getId() == null){
           throw new IllegalArgumentException("Id is Null");
        }

        Product existingProduct = repository.findById(product.getId()).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setPrice(product.getPrice());
            return repository.save(existingProduct);
        } else {
            throw new EntityNotFoundException("Product not found with id: " + product.getId());
        }
    }

}

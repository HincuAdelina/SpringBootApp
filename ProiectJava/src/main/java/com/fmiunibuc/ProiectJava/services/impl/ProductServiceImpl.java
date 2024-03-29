package com.fmiunibuc.ProiectJava.services.impl;

import com.fmiunibuc.ProiectJava.entities.Product;
import com.fmiunibuc.ProiectJava.entities.Restaurant;
import com.fmiunibuc.ProiectJava.exception.ProductNotFoundException;
import com.fmiunibuc.ProiectJava.exception.RestaurantNotFoundException;
import com.fmiunibuc.ProiectJava.repositories.ProductRepository;
import com.fmiunibuc.ProiectJava.repositories.RestaurantRepository;
import com.fmiunibuc.ProiectJava.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Product getProductById(int id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        } else {
            throw new ProductNotFoundException();
        }
    }
    @Override
    public List<Product> getProductsByRestaurantId(int restaurantId){
        return productRepository.findAllByRestaurantId(restaurantId);
    }
    @Override
    public Product addProduct(Product product, int restaurantId){
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(restaurant.isPresent()){
            product.setRestaurant(restaurant.get());
            return productRepository.save(product);
        } else {
            throw new RestaurantNotFoundException(restaurantId);
        }
    };

    @Override
    public void updateProduct(int id, Product product){
        Optional<Product> productUpdated = productRepository.findById(id);
        if(productUpdated.isPresent()){
            Product productUpdatedValue = productUpdated.get();
            productUpdatedValue.setName(product.getName());
            productUpdatedValue.setDescription(product.getDescription());
            productUpdatedValue.setPrice(product.getPrice());
            productUpdatedValue.setCategory(product.getCategory());
            productRepository.save(productUpdatedValue);
        } else {
            throw new ProductNotFoundException();
        }
    }

    @Override
    public void removeProduct(int id){
        productRepository.deleteById(id);
    };
}

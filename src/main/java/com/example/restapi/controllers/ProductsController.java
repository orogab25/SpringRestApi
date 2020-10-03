package com.example.restapi.controllers;

import com.example.restapi.models.JwtToken;
import com.example.restapi.models.Product;
import com.example.restapi.models.User;
import com.example.restapi.repositories.ProductsRepository;
import com.example.restapi.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ProductsController {

    private final ProductsRepository productsRepository;

    private final UsersRepository usersRepository;

    @Autowired
    private JwtToken jwtToken;

    public ProductsController(ProductsRepository productsRepository, UsersRepository usersRepository) {
        this.productsRepository = productsRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/products")
    public Iterable<Product> getProducts(){

        return productsRepository.findAll();
    }

    @GetMapping("/products/{productId}")
    public Product getProduct(@PathVariable long productId){

        return productsRepository.findById(productId).get();
    }

    @PostMapping("/products")
    public void putProduct(@RequestBody Product product, HttpServletRequest req){

        String token = req.getHeader("Authorization").replace("Bearer ","");
        User currentUser = usersRepository.findByUsername(jwtToken.getUsernameFromToken(token));
        product.setOwnerId(currentUser.getId());
        productsRepository.save(product);
    }

    @PutMapping("/products/{productId}")
    public void putProduct(@PathVariable long productId, @RequestBody Product product, HttpServletRequest req){

        String token = req.getHeader("Authorization").replace("Bearer ","");
        User currentUser = usersRepository.findByUsername(jwtToken.getUsernameFromToken(token));
        Product productInDB = productsRepository.findById(productId).get();

        if(productInDB.getOwnerId()==currentUser.getId()){
            productInDB.setName(product.getName());
            productInDB.setCategory(product.getCategory());
            productsRepository.save(productInDB);
        }else{
            throw new AuthorizationServiceException("The current user don't have permission to modify!");
        }
    }

    @PostMapping("/products/{productId}")
    public void postRate(@PathVariable Long productId, @RequestParam Double rate){
        Product productInDB = productsRepository.findById(productId).get();
        productInDB.addRate(rate);
        List<Double> rates = productInDB.getRates();
        double sumRate=0;

        for(Double rateNumber : rates){
            sumRate+=rateNumber;
        }
        productInDB.setAvgRate(sumRate/rates.size());
    }
}

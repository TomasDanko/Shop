package com.learn2code_v2.Shop_V2.controller;

import com.learn2code_v2.Shop_V2.db.service.api.ProductService;
import com.learn2code_v2.Shop_V2.domain.Product;
import com.learn2code_v2.Shop_V2.request.UpdateProductRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") int id){
        Product product = productService.get(id);
        if(product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody Product product){
        Integer id = productService.add(product);
        if(id != null){
            return  new ResponseEntity<>(id, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity allProducts() {
        List<Product> productList = productService.getProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable("id") int id, @RequestBody UpdateProductRequest request){
        if(productService.get(id) != null) {
            productService.updateProduct(id, request);
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Product with id: " + id + "does not exist");
        }
        }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") int id){
        if(productService.get(id) != null){
            productService.delete(id);
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Product with id: " + id + " does not exist");
        }
    }

    }



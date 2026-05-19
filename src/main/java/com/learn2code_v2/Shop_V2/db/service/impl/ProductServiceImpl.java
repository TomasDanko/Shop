package com.learn2code_v2.Shop_V2.db.service.impl;

import com.learn2code_v2.Shop_V2.db.repository.ProductRepository;
import com.learn2code_v2.Shop_V2.db.service.api.ProductService;
import com.learn2code_v2.Shop_V2.domain.Product;
import com.learn2code_v2.Shop_V2.request.UpdateProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public Product get(int id) {
        return productRepository.get(id);
    }

    @Override
    public Integer add(Product product) {
        return productRepository.add(product);
    }

    @Override
    public void updateProduct(int id, UpdateProductRequest request) {
        productRepository.update(id, request);

    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);

    }
}

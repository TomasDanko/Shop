package com.learn2code_v2.Shop_V2.db.service.api;

import com.learn2code_v2.Shop_V2.domain.Product;
import com.learn2code_v2.Shop_V2.request.UpdateProductRequest;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    @Nullable
    Product get(int id);

    @Nullable
    Integer add(Product product);

    void updateProduct(int id, UpdateProductRequest request);

    void delete(int id);
}

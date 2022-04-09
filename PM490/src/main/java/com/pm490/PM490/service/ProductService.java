// Author Angie
package com.pm490.PM490.service;

import com.pm490.PM490.dto.ProductRequest;
import com.pm490.PM490.dto.ProductSearchDto;
import com.pm490.PM490.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<Product> searchProduct(String searchPro);
    List<Product> searchProductAdvanced(ProductSearchDto productSearchDto);
    Product save(ProductRequest product);
    Product update(long id, ProductRequest product);
    boolean delete(long id);
}

// Author Angie
package com.pm490.PM490.service.implementation;

import com.pm490.PM490.dto.ProductRequest;
import com.pm490.PM490.dto.ProductSearchDto;
import com.pm490.PM490.model.Category;
import com.pm490.PM490.model.Product;
import com.pm490.PM490.model.ProductStatus;
import com.pm490.PM490.model.User;
import com.pm490.PM490.repository.CategoryRepository;
import com.pm490.PM490.repository.ProductRepository;
import com.pm490.PM490.repository.UserRepository;
import com.pm490.PM490.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public UserRepository vendorRepository;

    @Autowired
    public CategoryRepository categoryRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllStatus(ProductStatus status) {
        return productRepository.findAllStatus(status);
    }

    @Override
    public List<Product> searchProduct(String searchPro) {
        return productRepository.searchProduct(searchPro);
    }

    @Override
    public List<Product> searchProductAdvanced(ProductSearchDto productSearchDto) {
        return productRepository.searchProductAdvanced(productSearchDto.getName(), productSearchDto.getColor(), productSearchDto.getIdVendor(), productSearchDto.getIdCategory());
    }

    @Override
    public Product save(ProductRequest newProduct) {
        User vendor = vendorRepository.findById(newProduct.getIdVendor())
                .orElseThrow(() -> new ResourceNotFoundException("Vendor doesn't exist with id :" + newProduct.getIdVendor()));
        System.out.println("#### NAME "+vendor.getFullName());
        Category category = categoryRepository.findById(newProduct.getIdCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category doesn't exist with id :" + newProduct.getIdCategory()));
        System.out.println("### CAT "+category.getName());
        Product product = new Product(newProduct.getName(),
                newProduct.getColor(),
                vendor,
                ProductStatus.NOTAPPROVED,
                newProduct.getQuantity(),
                category,
                newProduct.getPrice());
        return productRepository.save(product);
    }

    @Override
    public Product update(long id, ProductRequest editedProduct) {
        Optional<Product> optionalProduct = Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product doesn't exist with id :" + id)));
        Product product = optionalProduct.get();
        if (optionalProduct.isPresent()) {
            product.setName(editedProduct.getName());
            product.setColor(editedProduct.getColor());
            User newVendor = vendorRepository.findById(editedProduct.getIdVendor())
                    .orElseThrow(() -> new ResourceNotFoundException("Vendor doesn't exist with id :" + editedProduct.getIdVendor()));
            product.setVendor(newVendor);
            product.setStatus(editedProduct.getStatus());
            product.setQuantity(editedProduct.getQuantity());
            Category newCategory = categoryRepository.findById(editedProduct.getIdCategory())
                    .orElseThrow(() -> new ResourceNotFoundException("Category doesn't exist with id :" + editedProduct.getIdCategory()));
            product.setCategory(newCategory);
            product.setPrice(editedProduct.getPrice());

            product = productRepository.save(product);
        }
        return product;
    }

    @Override
    public boolean delete(long id) {
        Optional<Product> optionalProduct = Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product doesn't exist with id :" + id)));
        Product product = optionalProduct.get();
        if (optionalProduct.isPresent()) {
            product.setStatus(ProductStatus.DELETED);
            product = productRepository.save(product);
            return !productRepository.existsById(id);
        } else {
            return false;
        }
    }
}

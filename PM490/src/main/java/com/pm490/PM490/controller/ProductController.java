package com.pm490.PM490.controller;

import com.pm490.PM490.dto.ProductRequest;
import com.pm490.PM490.dto.ProductSearchDto;
import com.pm490.PM490.model.Product;
import com.pm490.PM490.service.ProductService;
import com.pm490.PM490.util.ListMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/product")
@PreAuthorize("hasAuthority('VENDOR')")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    public ModelMapper mapper;

    @Autowired
    public ListMapper listMapper;

    @GetMapping
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/SearchName")
    public List<Product> searchProduct(@RequestParam String searchPro) {
        return productService.searchProduct(searchPro);
    }

    @GetMapping("/advancesearch")
    public List<Product> searchProductAdvanced(@RequestParam ProductSearchDto productAdv){
        List<Product> products = productService.searchProductAdvanced(productAdv);
        return listMapper.mapList(products, ProductSearchDto.class);
    }
    @PostMapping("/saveproduct")
    public Product save(@RequestBody ProductRequest product) {
        System.out.println(" #######CAT "+product.getIdCategory());
        return productService.save(product);
    }

    @PatchMapping
    public Product update(@PathVariable long id, @RequestBody ProductRequest product){
        return productService.update(id, product);
    }

}
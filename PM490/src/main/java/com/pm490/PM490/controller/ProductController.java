// Author Angie
package com.pm490.PM490.controller;

import com.pm490.PM490.dto.ProductRequest;
import com.pm490.PM490.dto.ProductSearchDto;
import com.pm490.PM490.model.Product;
import com.pm490.PM490.model.ProductStatus;
import com.pm490.PM490.model.Role;
import com.pm490.PM490.model.User;
import com.pm490.PM490.repository.UserRepository;
import com.pm490.PM490.service.CurrentUserService;
import com.pm490.PM490.service.ProductService;
import com.pm490.PM490.util.ListMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/product")

public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CurrentUserService currentUserService;

    @Autowired
    public ModelMapper mapper;

    @Autowired
    public ListMapper listMapper;


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/")
    public List<Product> findAllApproved(){
        return productService.findAllStatus(ProductStatus.APPROVED);
    }

    @PreAuthorize("hasAuthority('VENDOR') or hasAuthority('ADMIN')")
    @GetMapping("/mycreatedproducts")
    public List<Product> findAllByVendor(){
        User user = currentUserService.findLoggedUser();
        return productService.findAllByVendor(user);
    }

    @PostMapping("/advancesearch")
    public List<Product> searchProductAdvanced(@RequestBody ProductSearchDto productAdv){
        System.out.println("#########"+productAdv);
        if(productAdv.getStatus() != ProductStatus.APPROVED) {
            try {
                if (currentUserService.findLoggedUser() != null){
                    if(currentUserService.findLoggedUser().getRole() == Role.ADMIN){
                        System.out.println("#########" + currentUserService.findLoggedUser().getRole());
                        return productService.searchProductAdvanced(productAdv);
                    }else {
                        productAdv.setStatus(ProductStatus.APPROVED);
                    }
                } else {
                    productAdv.setStatus(ProductStatus.APPROVED);
                }

            } catch (Exception e) {
                productAdv.setStatus(ProductStatus.APPROVED);
            }
        }
            return productService.searchProductAdvanced(productAdv);
    }

    @PreAuthorize("hasAuthority('VENDOR') or hasAuthority('ADMIN')")
    @PostMapping("/saveproduct")
    public Product save(@RequestBody ProductRequest product) {
        User user = currentUserService.findLoggedUser();
        return productService.save(product, user);
    }
    @PreAuthorize("hasAuthority('VENDOR') or hasAuthority('ADMIN')")
    @PatchMapping("/updateproduct/{id}")
    public Product update(@PathVariable long id, @RequestBody ProductRequest product){
        User user = currentUserService.findLoggedUser();
        return productService.update(id, product, user);
    }
    @PreAuthorize("hasAuthority('VENDOR') or hasAuthority('ADMIN')")
    @PatchMapping("/deleteproduct/{id}")
    public Boolean delete(@PathVariable long id){
        return productService.delete(id);
    }

}

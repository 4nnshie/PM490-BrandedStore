// Author Angie
package com.pm490.PM490.controller;

import com.pm490.PM490.dto.ProductRequest;
import com.pm490.PM490.dto.ProductSearchDto;
import com.pm490.PM490.model.Product;
import com.pm490.PM490.model.ProductStatus;
import com.pm490.PM490.model.Role;
import com.pm490.PM490.model.User;
import com.pm490.PM490.repository.ProductRepository;
import com.pm490.PM490.repository.UserRepository;
import com.pm490.PM490.service.CurrentUserService;
import com.pm490.PM490.service.ProductService;
import com.pm490.PM490.util.ListMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/product")

public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

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
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/")
    public List<Product> findAllApproved() {
        return productService.findAllStatus(ProductStatus.APPROVED);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found - %d !" + id));
    }

    @PreAuthorize("hasAuthority('VENDOR') or hasAuthority('ADMIN')")
    @GetMapping("/mycreatedproducts")
    public List<Product> findAllByVendor() {
        User user = currentUserService.findLoggedUser();
        return productService.findAllByVendor(user);
    }

    @PostMapping("/advancesearch")
    public List<Product> searchProductAdvanced(@RequestBody ProductSearchDto productAdv) {
        System.out.println("#########" + productAdv);
        if (productAdv.getName() != "") {
            if (productAdv.getStatus() != ProductStatus.APPROVED) {
                try {
                    if (currentUserService.findLoggedUser() != null) {
                        if (currentUserService.findLoggedUser().getRole() == Role.ADMIN) {
                            System.out.println("#########" + currentUserService.findLoggedUser().getRole());
                            return productService.searchProductAdvanced(productAdv);
                        } else {
                            productAdv.setStatus(ProductStatus.APPROVED);
                        }
                    } else {
                        productAdv.setStatus(ProductStatus.APPROVED);
                    }
                } catch (Exception e) {
                    productAdv.setStatus(ProductStatus.APPROVED);
                }
            } else {
                return productService.searchProductAdvanced(productAdv);
            }
        }
        return (List<Product>) productRepository.findAllByVendor_IdOrCategory_Id(productAdv.getIdVendor(), productAdv.getIdCategory())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nothing :("));
    }

    @PreAuthorize("hasAuthority('VENDOR') or hasAuthority('ADMIN')")
    @PostMapping("/saveproduct")
    public ResponseEntity<?> save(@RequestBody ProductRequest product) {
        try {
            User user = currentUserService.findLoggedUser();
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product, user));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(exception.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('VENDOR') or hasAuthority('ADMIN')")
    @PatchMapping("/updateproduct/{id}")
    public Product update(@PathVariable long id, @RequestBody ProductRequest product) {
        User user = currentUserService.findLoggedUser();
        return productService.update(id, product, user);
    }

    @PreAuthorize("hasAuthority('VENDOR') or hasAuthority('ADMIN')")
    @PatchMapping("/deleteproduct/{id}")
    public Boolean delete(@PathVariable long id) {
        return productService.delete(id);
    }

    @GetMapping("/getcolors/")
    public List<String> getColors() {
        return productRepository.getColors();
    }
}

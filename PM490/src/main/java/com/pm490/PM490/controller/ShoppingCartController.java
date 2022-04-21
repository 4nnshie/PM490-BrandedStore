package com.pm490.PM490.controller;

import com.pm490.PM490.dto.ItemListRequest;
import com.pm490.PM490.model.ItemList;
import com.pm490.PM490.model.Product;
import com.pm490.PM490.model.PurchaseStatus;
import com.pm490.PM490.model.User;
import com.pm490.PM490.repository.ItemListRepository;
import com.pm490.PM490.repository.ProductRepository;
import com.pm490.PM490.repository.UserRepository;
import com.pm490.PM490.service.CurrentUserService;
import com.pm490.PM490.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

@RestController
@RequestMapping("/api/shoppingcart")
@PreAuthorize("hasAuthority('CUSTOMER')")

public class ShoppingCartController {

    @Autowired
    ItemListRepository itemListRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CurrentUserService currentUserService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/getItems")
    List<ItemList> getItems() {
        UserDetailsImpl userDetail = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findById(userDetail.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found - %d !"));
        return itemListRepository.findByUserAndCreated(user.getId());
    }

    @PostMapping("/add")
    List<ItemList> add(@RequestBody ItemListRequest newItem) {
        User user = currentUserService.findLoggedUser();
        Product p = productRepository.findById(newItem.getIdProduct())
                .orElseThrow(() -> new ResourceNotFoundException("product doesn't exist "));
        ItemList i = new ItemList();
        i.setUser(user);
        i.setProduct(p);
        i.setQuantity(newItem.getQuantity());
        i.setPurchaseStatus(PurchaseStatus.CREATED);
        i.setTotal(p.getPrice()*newItem.getQuantity());
        itemListRepository.save(i);
        return itemListRepository.findByUserAndCreated(user.getId());
    }

    @PostMapping("/update/{id}")
    List<ItemList> update(@RequestBody ItemList itemParam, @PathVariable Long id) {
        UserDetailsImpl userDetail = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findById(userDetail.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found - %d !"));

        return itemListRepository.findById(id)
                .map(item -> {
                    item.setQuantity(itemParam.getQuantity());
                    item.setTotal(itemParam.getQuantity() * item.getProduct().getPrice());
                    itemListRepository.save(item);
                    return itemListRepository.findByUserAndCreated(user.getId());
                })
                .orElseGet(() -> {
//                    itemParam.setId(id);
                    itemParam.setUser(user);
                    itemParam.setPurchaseStatus(PurchaseStatus.CREATED);
                    itemListRepository.save(itemParam);
                    return itemListRepository.findByUserAndCreated(user.getId());
                });
    }

    @DeleteMapping("/deleteItem/{id}")
    List<ItemList> delete(@PathVariable Long id) {

        UserDetailsImpl userDetail = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findById(userDetail.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found - %d !"));

        itemListRepository.deleteById(id);
        return itemListRepository.findByUserAndCreated(user.getId());
    }
}
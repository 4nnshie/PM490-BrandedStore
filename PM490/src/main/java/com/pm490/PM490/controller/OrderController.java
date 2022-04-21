package com.pm490.PM490.controller;

import com.pm490.PM490.dto.OrderRequest;
import com.pm490.PM490.model.User;
import com.pm490.PM490.repository.OrderRepository;
import com.pm490.PM490.service.CurrentUserService;
import com.pm490.PM490.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@PreAuthorize("hasAuthority('CUSTOMER') || hasAuthority('CLIENT')")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CurrentUserService currentUserService;

    @GetMapping()
    public ResponseEntity<?> searchProduct() {
        return ResponseEntity.ok().body(orderRepository.findAll());
    }

    @PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> newOrder(@RequestBody OrderRequest order) {

        try {
            User user = currentUserService.findLoggedUser();
            return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order, user));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(exception.getMessage());
        }
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<?> showOrder(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRepository.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable long id, @RequestBody OrderRequest order) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.update(id, order));
    }
}

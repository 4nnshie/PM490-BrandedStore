package com.pm490.PM490.service.implementation;

import com.pm490.PM490.dto.OrderRequest;
import com.pm490.PM490.dto.OrderSearchDto;
import com.pm490.PM490.model.*;
import com.pm490.PM490.repository.CustomerRepository;
import com.pm490.PM490.repository.ItemListRepository;
import com.pm490.PM490.repository.OrderRepository;
import com.pm490.PM490.service.CurrentUserService;
import com.pm490.PM490.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CurrentUserService currentUserService;
    @Autowired
    private ItemListRepository itemListRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<OrderCart> showAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderCart> searchOrder(Long searchOrder) {
        return orderRepository.findAllById(new ArrayList<>(){{add(searchOrder);}});
    }

    @Override
    public OrderCart save(OrderRequest newOrder) throws RuntimeException {
        User user = currentUserService.findLoggedUser();
        Customer customer = customerRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer doesn't exist with id :" +user.getId()));
        List<ItemList> items = itemListRepository.findByUserAndCreated(user.getId());

        // VALIDATE
        if(items.isEmpty()) {
            throw new RuntimeException("Nothing to order");
        }

        OrderCart order = new OrderCart(customer,
                newOrder.getDateOrdered(),
                newOrder.getDateShipped(),
                PurchaseStatus.ORDERED,
                items);
        OrderCart createdOrder = orderRepository.save(order);
        for(ItemList item : items) {
            item.setUser(user);
            item.setPurchaseStatus(PurchaseStatus.ORDERED);
            itemListRepository.save(item);
        }
        return createdOrder;
    }

    @Override
    public OrderCart update(long id, OrderRequest editedOrder) {
        Optional<OrderCart> optionalOrder = Optional.ofNullable(orderRepository.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Order doesn't exist with id: " + id));
        OrderCart orderCart = optionalOrder.get();
        if(optionalOrder.isPresent()){
            orderCart.setDateOrdered(editedOrder.getDateOrdered());
            orderCart.setDateShipped(editedOrder.getDateShipped());
            orderCart.setStatus(editedOrder.getStatus());

            orderCart = orderRepository.save(orderCart);
        }
        return orderCart;
    }
}

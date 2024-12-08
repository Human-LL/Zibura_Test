package com.example.mcsample.service;

import com.example.mcsample.entity.Order;
import com.example.mcsample.entity.OrderItem;
import com.example.mcsample.entity.Product;
import com.example.mcsample.entity.User;
import com.example.mcsample.repository.OrderRepository;
import com.example.mcsample.repository.ProductRepository;
import com.example.mcsample.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
public class OrderServiceImpl {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void addProductToOrder(UUID userId, UUID orderId, UUID productId, int quantity) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (userOptional.isPresent() && orderOptional.isPresent() && productOptional.isPresent()) {
            User user = userOptional.get();
            Order order = orderOptional.get();
            Product product = productOptional.get();

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(quantity);
            order.addOrderItem(orderItem);

            orderRepository.save(order);
        }
    }


    @Transactional
    public void updateProductQuantityInOrder(UUID orderId, UUID productId, int newQuantity) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            OrderItem orderItem = order.getOrderItems().stream()
                    .filter(item -> item.getProduct().getId().equals(productId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Товар не найден в заказе"));

            orderItem.setQuantity(newQuantity);
            orderRepository.save(order);
        }
    }


    @Transactional
    public void removeProductFromOrder(UUID orderId, UUID productId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.getOrderItems().removeIf(item -> item.getProduct().getId().equals(productId));
            orderRepository.save(order);
        }
    }

    @Transactional
    public void deleteOrder(UUID orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            orderRepository.deleteById(orderId);
        }
    }

    public List<OrderItem> getOrderItems(UUID orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            return orderOptional.get().getOrderItems();
        } else {
            throw new RuntimeException("Заказ не найден");
        }
    }
}
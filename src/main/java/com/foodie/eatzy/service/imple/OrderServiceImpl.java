package com.foodie.eatzy.service.imple;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.foodie.eatzy.dto.OrderDto;
import com.foodie.eatzy.dto.OrderPlaceRequest;
import com.foodie.eatzy.entity.Address;
import com.foodie.eatzy.entity.Cart;
import com.foodie.eatzy.entity.CartItem;
import com.foodie.eatzy.entity.Order;
import com.foodie.eatzy.entity.OrderItem;
import com.foodie.eatzy.entity.Restaurant;
import com.foodie.eatzy.entity.User;
import com.foodie.eatzy.entity.enums.OrderStatus;
import com.foodie.eatzy.entity.enums.PaymentStatus;
import com.foodie.eatzy.exception.ResourceNotFoundException;
import com.foodie.eatzy.repository.CartRepository;
import com.foodie.eatzy.repository.OrderRepository;
import com.foodie.eatzy.repository.RestaurantRepository;
import com.foodie.eatzy.repository.UserRepository;
import com.foodie.eatzy.service.CartService;
import com.foodie.eatzy.service.OrderService;
import com.foodie.eatzy.util.Helper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public OrderDto placeOrder(OrderPlaceRequest orderPlaceRequest) {

        User user = userRepository.findById(orderPlaceRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        Restaurant restaurant = restaurantRepository.findById(orderPlaceRequest.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        List<CartItem> cartItems = cart.getItems();
        System.out.println("size of the cart: " + cartItems.size());
        if (cartItems.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Cart is empty");
        }
        // Convert cart items to order items
        Order order = new Order();
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setId(Helper.uuid());
        Address address = mapper.map(orderPlaceRequest.getAddress(), Address.class);
        if (address.getId() == null)
            address.setId(null);

        order.setAddress(address);
        order.setStatus(OrderStatus.PLACED);
        order.setOrderedAt(LocalDateTime.now());
        order.setPaymentStatus(
                PaymentStatus.NOT_PAID);
        order.setPaymentMode(
                orderPlaceRequest.getPaymentMode());

        final AtomicInteger totalAmount = new AtomicInteger(0);
        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setFoodItem(cartItem.getFoodItem());
                    totalAmount.set(totalAmount.get()
                            + ((int) (cartItem.getFoodItem().actualPrice() * cartItem.getQuantity())));

                    return orderItem;
                })
                .collect(Collectors.toList());
        order.setTotalAmount(totalAmount.get());
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        // Clear user's cart
        cartService.clearCart(orderPlaceRequest.getUserId());
        return mapper.map(order, OrderDto.class);
    }

    @Override
    public List<OrderDto> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> mapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrderByRestaurant(String restaurantId) {
        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
        return orders.stream()
                .map(order -> mapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrderByUser(String userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(order -> mapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrderByDeliveryBoy(String deliveryBoyId) {
        List<Order> orders = orderRepository.findByDeliveryBoyId(deliveryBoyId);
        return orders.stream()
                .map(order -> mapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto tractOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return mapper.map(order, OrderDto.class);
    }

    @Override
    public OrderDto cancelOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(OrderStatus.CANCELLED);
        Order savedOrder = orderRepository.save(order);
        return mapper.map(savedOrder, OrderDto.class);
    }

    @Override
    public OrderDto updateOrderStataus(String orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
        order.setStatus(orderStatus);
        Order savedOrder = orderRepository.save(order);
        return mapper.map(savedOrder, OrderDto.class);
    }
}

package com.foodie.eatzy.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.foodie.eatzy.entity.enums.FoodType;
import com.foodie.eatzy.entity.enums.UnitType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean available;
    @Enumerated(EnumType.STRING)
    private FoodType foodType = FoodType.VEG;
    private String imageUrl;
    private LocalDateTime createdDate;
    private int discountAmount;
    @ManyToOne
    private Restaurant restaurant;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    public double actualPrice() {
        return price - discountAmount;
    }

    public double getDiscountPrecentage() {
        return (discountAmount / price) * 100;
    }

}
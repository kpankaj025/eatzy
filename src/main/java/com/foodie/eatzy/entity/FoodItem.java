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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "foodItem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal basePrice;

    private boolean isAvailable;

    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private UnitType unit;

    private BigDecimal discount;

    private BigDecimal weightKg;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public BigDecimal actualPrice() {
        return basePrice.subtract(discount);
    }

    public BigDecimal getDiscountPercentage() {
        if (basePrice == null || discount == null || basePrice.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO; // avoid division by zero
        }
        return discount
                .divide(basePrice, 2, RoundingMode.HALF_UP) // scale = 2 for %, round properly
                .multiply(BigDecimal.valueOf(100));
    }

}

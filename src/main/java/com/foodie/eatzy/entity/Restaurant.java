package com.foodie.eatzy.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "foodie_restaurant")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {

    @Id
    private String id;

    private String name;

    @Lob
    private String description;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Boolean open = true;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    private boolean isActive = true;
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodItem> foodItems = new ArrayList<>();

    private String bannerImageUrl;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }

}

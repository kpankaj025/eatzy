package com.foodie.eatzy.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.foodie.eatzy.entity.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "foodie_users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role; // ADMIN, USER, DELIVERY_BOY, RESTAURANT

    private boolean isAvailable = true; // applicable for delivery boy

    // feel free to add more fields ad required

    private LocalDate createdDate;

    private boolean enabled = true;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Restaurant> restaurants = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDate.now();
    }

    //
    // @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // @JoinTable(name = "user_role",
    // joinColumns = @JoinColumn(name = "user_id"),
    // inverseJoinColumns = @JoinColumn(name = "role_id")
    // )
    // private List<RoleEntity> roleEntities = new ArrayList<>();

    // @PrePersist
    // public void preSave() {
    // this.createdDate = LocalDate.now();
    // }
    //
    // @PostPersist
    // public void postSave() {
    // System.out.println("entity saved : " + this.getId());
    // }
    //
    // @PreUpdate
    // public void preUpdate() {
    // System.out.println("entity updated : " + this.getId());
    // }

}

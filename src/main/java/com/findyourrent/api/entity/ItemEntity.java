package com.findyourrent.api.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private float price;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    private UserEntity owner;

    @Column(name = "ownerId", insertable = false, updatable = false)
    private int ownerId;

    @Column(columnDefinition = "TEXT[]") // Adjust for your database
    private String[] photos;

    private boolean isAvailable = true;

    @OneToMany(mappedBy = "item")
    private List<RentalEntity> rentals;
}

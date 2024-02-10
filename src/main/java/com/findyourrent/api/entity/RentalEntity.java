package com.findyourrent.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "rentals")
public class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "renterId")
    private UserEntity renter;

    @Column(name = "renterId", insertable = false, updatable = false)
    private int renterId;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private ItemEntity item;

    @Column(name = "itemId", insertable = false, updatable = false)
    private int itemId;

    @Column(name = "startDate")
    private LocalDateTime startDate;

    @Column(name = "endDate")
    private LocalDateTime endDate;

    @Column(name = "status")
    private String status;
}

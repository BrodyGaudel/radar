package org.mounanga.registrationservice.queries.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Vehicle {

    @Id
    private String id;

    @Column(unique = true, nullable = false)
    private String registrationId;

    @Column(nullable = false)
    private String marque;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Double taxPower;

    @Column(nullable = false)
    private String description;

    @CreatedDate
    private LocalDateTime createdDate;

    @CreatedDate
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @ManyToOne
    private Owner owner;
}

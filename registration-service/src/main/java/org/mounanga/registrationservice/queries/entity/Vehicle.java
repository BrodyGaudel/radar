package org.mounanga.registrationservice.queries.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
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
@Builder
@ToString
public class Vehicle {
    @Id
    private String id;

    @Column(unique=true, nullable=false)
    private String registrationId;

    @Column(nullable=false)
    private String marque;

    @Column(nullable=false)
    private String model;

    @Column(nullable=false)
    private String description;

    @Column(nullable=false)
    private Double taxPower;

    @ManyToOne
    private Owner owner;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    private String createBy;

    @LastModifiedBy
    @Column(insertable=false)
    private String lastModifiedBy;
}

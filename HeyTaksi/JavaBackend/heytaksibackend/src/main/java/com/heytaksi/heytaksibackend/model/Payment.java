package com.heytaksi.heytaksibackend.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"id"})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paymentid_generator")
    @SequenceGenerator(name = "paymentid_generator", sequenceName = "paymentid_seq", initialValue = 1)
    @Column(name="paymentid", updatable = false, nullable = false)
    private Long id;

    private Integer paymentType;
    private String paymentTypeName;
    private Long customerId; // TODO: FK
    private Long driverId; // TOOD: manuel
    private Long tripId;

}

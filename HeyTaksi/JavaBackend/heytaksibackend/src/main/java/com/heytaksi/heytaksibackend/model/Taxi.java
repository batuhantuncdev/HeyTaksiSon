package com.heytaksi.heytaksibackend.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"id"})
public class Taxi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taxi_id_generator")
    @SequenceGenerator(name = "taxi_id_generator", sequenceName = "taxi_id_seq", initialValue = 1)
    @Column(name="taxi_id", updatable = false, nullable = false)
    private Long id;

    private String taxiPlate;
    private String taxiModel;
    private String taxiBrand;
    private Double currentLocationX;
    private Double currentLocationY;

    @Column
    private String createdBy;
    @Column
    private String updatedBy;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        LocalDateTime localNow = LocalDateTime.now();
        ZonedDateTime zonedUTC = localNow.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedIST = zonedUTC.withZoneSameInstant(ZoneId.of("Europe/Istanbul"));
        setCreatedAt(zonedIST.toLocalDateTime());
    }

    @PreUpdate
    void onPersist() {
        LocalDateTime localNow = LocalDateTime.now();
        ZonedDateTime zonedUTC = localNow.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedIST = zonedUTC.withZoneSameInstant(ZoneId.of("Europe/Istanbul"));
        setUpdatedAt(zonedIST.toLocalDateTime());
    }

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;
}

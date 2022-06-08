package com.heytaksi.heytaksibackend.model;

import java.time.LocalDateTime;
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

public class Driver implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driverid_generator")
    @SequenceGenerator(name = "driverid_generator", sequenceName = "driverid_seq", initialValue = 1)
    @Column(name="driverid", updatable = false, nullable = false)
    private Long id;
    private String driverName;
    private String driverSurname;
    private Long driverPhone;
    private String driverMail;
    private Long taxiId; // TODO: Manuel relationship -> One to One
    private Boolean isAvailable; // TODO: trip musaitligini kontrol edecegiz.
    private Boolean isOnline;
    private Long appUserId;

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

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
public class Trip implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tripid_generator")
    @SequenceGenerator(name = "tripid_generator", sequenceName = "tripid_seq", initialValue = 1)
    @Column(name="tripid", updatable = false, nullable = false)

    private Long id;
    private LocalDateTime tripTime;
    private Long tripCost;
    private Long tripScore;
    private Long driverId;
    private Long customerId;
    private Long taxiId;
    private Long notificationId; // TODO: think about relationships.
    // TODO: It dependes whether each trip should have more than one notification or not.

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

}

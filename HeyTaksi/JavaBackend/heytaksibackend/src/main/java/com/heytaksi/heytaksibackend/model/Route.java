package com.heytaksi.heytaksibackend.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"id"})
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "routeid_generator")
    @SequenceGenerator(name = "routeid_generator", sequenceName = "routeid_seq", initialValue = 1)
    @Column(name="routeid", updatable = false, nullable = false)
    private Long id;

    private Long appUserId;
    private String startingPoint;
    private String endingPoint;
    private String tripDistance;
    private String tripCost;

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

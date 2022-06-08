package com.heytaksi.heytaksibackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"id"})
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", initialValue = 1)
    @Column(name="user_id", updatable = false, nullable = false)

    private Long id;
    private String fullname;
    private String role;
    @Column(columnDefinition = "TEXT")
    private String token;
    private String userEmail;
    private String userPassword;
    private String userPhoneNumber;
    private LocalDateTime lastLogin;
    private LocalDateTime registeredDate;
    private Double homeAddressLocationX; // for customer role only
    private Double homeAddressLocationY; // for customer role only
    private String homeAddress; // for customer role only
    private Double currentLocationX;
    private Double currentLocationY;
    private Boolean isAvailable;

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

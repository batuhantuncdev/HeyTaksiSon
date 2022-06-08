package com.heytaksi.heytaksibackend.model;


import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"id"})
public class FavouriteRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "favouriterouteid_generator")
    @SequenceGenerator(name = "favouriterouteid_generator", sequenceName = "favouriterouteid_seq", initialValue = 1)
    @Column(name="favouriterouteid", updatable = false, nullable = false)
    private Long id;

    private Long appUserId;
    private Long routeId;
}

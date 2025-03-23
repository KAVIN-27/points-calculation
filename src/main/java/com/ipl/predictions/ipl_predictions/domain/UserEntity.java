package com.ipl.predictions.ipl_predictions.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String winner;
    private String runnerUp;
    private String orangeCap;
    private String purpleCap;
    private String emergingPlayer;
    private String mostValuablePlayer;


    @Builder.Default
    private int points =0;
}

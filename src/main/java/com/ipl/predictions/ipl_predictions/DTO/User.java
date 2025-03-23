package com.ipl.predictions.ipl_predictions.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long id;
    private String username;
    private String winner;
    private String runnerUp;
    private String orangeCap;
    private String purpleCap;
    private String emergingPlayer;
    private String MostValuablePlayer;

    private int points;

}

package com.ipl.predictions.ipl_predictions.repository;

import com.ipl.predictions.ipl_predictions.domain.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentResultRepository extends JpaRepository<TournamentEntity,Long> {

    Optional<TournamentEntity> findFirstByOrderByIdDesc();
}

package net.subey.starter.example.repository;

import net.subey.starter.example.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition,Long> {


    List<Competition> findAllByPlayers_Id(Long playerId);
    Competition findOneByName(String name);
}

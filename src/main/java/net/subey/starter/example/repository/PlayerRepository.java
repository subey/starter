package net.subey.starter.example.repository;

import net.subey.starter.example.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {

    Player findOneByEmail(String email);
}

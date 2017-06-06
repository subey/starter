package net.subey.starter.example.repository;

import net.subey.starter.example.domain.Player;
import net.subey.starter.example.domain.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration,Long> {

    Registration findOneByPlayer(Player player);
}

package net.subey.starter.example.repository;

import net.subey.starter.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAllByLastLoginBetween(LocalDateTime fromDate, LocalDateTime toDate);


    @Query("SELECT u FROM User u WHERE u.lastLogin BETWEEN :from AND :to")
    List<User> findCustom(
            @Param("from") LocalDateTime fromDate,
            @Param("to") LocalDateTime toDate);
}
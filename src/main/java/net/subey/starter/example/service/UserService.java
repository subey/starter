package net.subey.starter.example.service;

import net.subey.starter.example.domain.User;
import net.subey.starter.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager em;

    public List<User> findByDates(LocalDateTime fromDate, LocalDateTime toDate) {
        return userRepository.findAllByLastLoginBetween(fromDate,toDate);
    }

    public List<User> findManual(
            LocalDateTime fromDate,
            LocalDateTime toDate) {

        Query query = em.createNativeQuery("SELECT * FROM user");

        query.setParameter("from", Timestamp.valueOf(fromDate));
        query.setParameter("to", Timestamp.valueOf(toDate));


        List<Object[]> result = query.getResultList();
        log.info("result="+result.size());

        ArrayList<User> items = new ArrayList<User>();
        for (int i = 0; i < result.size(); i++) {
            Object[] row = result.get(i);

            User item = new User();
            item.setLastLogin(LocalDateTime.ofInstant(((Timestamp)row[0]).toInstant(), ZoneId.of("UTC")));
            item.setName((String) row[1]);
            items.add(item);
        }
        return items;

    }
}

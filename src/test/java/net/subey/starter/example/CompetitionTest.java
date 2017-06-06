package net.subey.starter.example;

import net.subey.starter.example.domain.Competition;
import net.subey.starter.example.domain.Player;
import net.subey.starter.example.domain.Registration;
import net.subey.starter.example.repository.CompetitionRepository;
import net.subey.starter.example.repository.PlayerRepository;
import net.subey.starter.example.repository.RegistrationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompetitionTest {

    private final Logger log = LoggerFactory.getLogger(CompetitionTest.class);

    @Autowired
    CompetitionRepository competitionRepository;

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Test
    @Rollback(false)
    public void formsetup(){
        Competition competitionTeam = new Competition();
        competitionTeam.setName("Footbal");
        competitionTeam.setCity("Cracow");

        Competition competitionSingle = new Competition();
        competitionSingle.setName("Running");
        competitionSingle.setCity("Cracow");

        List<Competition> competitions = new ArrayList<>();
        competitions.add(competitionSingle);
        competitions.add(competitionTeam);
        competitionRepository.save(competitions);
    }
    @Test
    @Transactional
    @Rollback(false)
    public void form(){

        Player playerA = playerRepository.findOneByEmail("jf@example.com");
        if(playerA == null){
            playerA = new Player();
            playerA.setFirstName("John");
            playerA.setLastName("Foo");
            playerA.setEmail("jf@example.com");
        }else {
            log.warn("playerA exist");
        }


        Player playerB = playerRepository.findOneByEmail("tb@example.com");
        if(playerB == null){
            playerB = new Player();
            playerB.setFirstName("Thomas");
            playerB.setLastName("Baar");
            playerB.setEmail("tb@example.com");
        }else {
            log.warn("playerB exist");
        }


        Competition competitionTeam = competitionRepository.findOneByName("Footbal");
        competitionTeam.getPlayers().add(playerA);
        competitionTeam.getPlayers().add(playerB);


        Competition competitionSingle = competitionRepository.findOneByName("Running");
        competitionSingle.getPlayers().add(playerA);

        Registration reg = registrationRepository.findOneByPlayer(playerA);

        if(reg == null){
            reg = new Registration();
            reg.setCreated(LocalDateTime.now());
            reg.setPlayer(playerA);
            playerA.setRegistration(reg);
            registrationRepository.save(reg);
        }else {
            log.warn("Exist=" + reg);
        }


        List<Competition> allA = competitionRepository.findAll();
        for(Competition c : allA){
            log.info("### " + c.getName() + " - " + c.getCity());
            for(Player p : c.getPlayers()){
                log.info("    " + p.getFirstName() + " - " + p.getLastName());
            }
        }


        List<Registration> all = registrationRepository.findAll();
        log.info(all.toString());
        for(Registration r : all){
            for(Competition c : r.getPlayer().getCompetitions()){
                log.info(c.getName() +" | " + r.getPlayer().getFirstName() + " | team="+c.getPlayers().size());
            }
        }


    }

}

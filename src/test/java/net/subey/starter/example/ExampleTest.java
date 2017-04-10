package net.subey.starter.example;

import com.bethecoder.ascii_table.ASCIITable;
import com.bethecoder.ascii_table.impl.CollectionASCIITableAware;
import com.bethecoder.ascii_table.spec.IASCIITableAware;
import net.subey.starter.example.domain.User;
import net.subey.starter.example.repository.UserRepository;
import net.subey.starter.example.service.UserService;
import net.subey.starter.scg.Generator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleTest {
    private final Logger log = LoggerFactory.getLogger(ExampleTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void run(){
        userRepository.save(new User("subey"));
        List<User> items = userRepository.findAll();
        log.info(items.toString());
        IASCIITableAware asciiTableAware =
                new CollectionASCIITableAware<User>(items,"id", "name");
        ASCIITable.getInstance().printTable(asciiTableAware);
    }

    @Test
    public void generate() throws IOException {
        User user = new User("subey");
        user.setType(2);
        Generator gen = new Generator();
        gen.run(user);
    }
}
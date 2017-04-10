package net.subey.starter.example.domain;

import net.subey.starter.scg.annotation.Dictionary;
import net.subey.starter.scg.annotation.DictionaryItem;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "user_id_seq_gen", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq_gen")
    private Long id;

    @Column(name = "user_name")
    private String name;

    private LocalDateTime lastLogin;

    @Dictionary(key = "user_types", value = {
            @DictionaryItem(key = "1", value = "normal"),
            @DictionaryItem(key = "2", value = "admin")
    })
    private Integer type;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastLogin=" + lastLogin +
                ", type=" + type +
                '}';
    }
}

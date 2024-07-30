package org.example.security_session.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true) // 중복 x
    private String username;
    private String password;
    private String role;

//    public UserEntity(String username, String password, String role) {
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }
}


package org.example.security_session.repository;

import org.example.security_session.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.BooleanLiteral;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Boolean existsByUsername(String username);
}

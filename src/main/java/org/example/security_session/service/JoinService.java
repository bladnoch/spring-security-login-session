package org.example.security_session.service;

import lombok.RequiredArgsConstructor;
import org.example.security_session.dto.JoinDTO;
import org.example.security_session.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;

    public void joinProcess(JoinDTO request) {

    }
}

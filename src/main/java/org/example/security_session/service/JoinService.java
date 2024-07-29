package org.example.security_session.service;

import lombok.RequiredArgsConstructor;
import org.example.security_session.domain.UserEntity;
import org.example.security_session.dto.JoinDTO;
import org.example.security_session.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO request) {
        //db에 동일한 유저 이름을 가진 유저가 있는지 검증
//        if (userRepository.existsByUsername(request.getUsername())) {
//            return;
//        }

        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());

        UserEntity data = new UserEntity();
        data.setUsername(request.getUsername());
        data.setPassword(encodedPassword);
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);

    }
}

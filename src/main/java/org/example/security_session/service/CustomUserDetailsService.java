//package org.example.security_session.service;
//
//import lombok.RequiredArgsConstructor;
//import org.example.security_session.domain.UserEntity;
//import org.example.security_session.dto.CustomUserDetails;
//import org.example.security_session.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        UserEntity userData = userRepository.findByUsername(username);
//        System.out.println("CustomUserDetailsService.loadUserByUsername");
//        System.out.println("userData.getUsername = " + userData.getUsername());
//        System.out.println("userData.getRole() = " + userData.getRole());
//
//        if (userData != null) {
//            System.out.println("userData exist");
//            return new CustomUserDetails(userData);
//        }
//
//        return null;
//    }
//}

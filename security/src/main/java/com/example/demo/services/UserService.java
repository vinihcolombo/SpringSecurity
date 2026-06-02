package com.example.demo.services;

import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements UserDetailsService {

    @Autowired // Autowired cria uma instância da classe chamada
    private UserRepository userRepository;

    @Override // Altera o funcionamento do método herdado
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return  userRepository.findByLogin(username);
    }

}
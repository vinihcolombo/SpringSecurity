package com.example.demo.controllers;

import com.example.demo.models.AuthenticationDTO;
import com.example.demo.models.RegisterDTO;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController // O Controller é do tipo REST
@RequestMapping(path = "auth") // Caminho utilizado pelo controller
public class AuthenticationController {

    @Autowired // Instancia a classe chamada
    private AuthenticationManager authenticationManager;

    @Autowired // Instancia a classe chamada
    private UserRepository userRepository;

    @PostMapping("/login") // Notação para o POST no CRUD (Criar)
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO data){   // RequestBody chama um objeto para ser utilizado
                                                                                // Valid é um mecanismo de validação
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
            return ResponseEntity.ok().build();
    }

    @PostMapping("/register") // Notação para o POST no CRUD (Criar)
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){   // RequestBody chama um objeto para ser utilizado
                                                                            // Valid é um mecanismo de validação
        if(this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserModel newUser = new UserModel(data.login(), encryptedPassword, data.role());
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

}

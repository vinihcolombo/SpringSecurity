package com.example.demo.models;

import com.example.demo.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity // Define a classe como uma entidade, criando uma tabela no banco
@Table(name = "PESSOA") // Define o nome da tabela no banco de dados
@Data // Gera Getter e Setter automaticamente
public class UserModel implements UserDetails {

    @Id // Designa a variável como a coluna de ID no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Meio no qual o ID será gerado
    private Long id;
    private String login;
    private String password;
    private UserRole role;

    public UserModel(String login, String password, UserRole role){
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public UserModel(){

    }

    @Override // Altera o funcionamento do método herdado
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ADMIN"),
                                                       new SimpleGrantedAuthority("USER"));
        else return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override // Altera o funcionamento do método herdado
    public String getUsername() {
        return "";
    }

    @Override // Altera o funcionamento do método herdado
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override // Altera o funcionamento do método herdado
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override // Altera o funcionamento do método herdado
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override // Altera o funcionamento do método herdado
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}

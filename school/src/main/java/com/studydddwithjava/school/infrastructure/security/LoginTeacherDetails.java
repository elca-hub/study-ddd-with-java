package com.studydddwithjava.school.infrastructure.security;

import com.studydddwithjava.school.domain.model.teacher.Teacher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

public class LoginTeacherDetails implements UserDetails {
    private final Teacher teacher;
    private final Collection<? extends GrantedAuthority> authorities;

    public LoginTeacherDetails(Teacher teacher) {
        this.teacher = teacher;
        this.authorities = teacher.getRoleList().stream().map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getPassword() {
        return teacher.getHashPw().getHash();
    }

    @Override
    public String getUsername() {
        return teacher.getName().getFullName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

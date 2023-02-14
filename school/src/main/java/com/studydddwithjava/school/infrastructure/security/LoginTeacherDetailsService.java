package com.studydddwithjava.school.infrastructure.security;

import com.studydddwithjava.school.domain.model.teacher.ITeacherRepository;
import com.studydddwithjava.school.domain.model.teacher.Teacher;
import com.studydddwithjava.school.domain.model.user.UserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginTeacherDetailsService implements UserDetailsService {
    @Autowired
    @Qualifier("mysql")
    private ITeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] nameArr = username.split(" ", 2); // フルネームで取得するので、firstnameとlastnameに分割
        Optional<Teacher> findTeacher = teacherRepository.findByUserName(new UserName(nameArr[0], nameArr[1]));

        if (findTeacher.isEmpty()) throw new UsernameNotFoundException("user not found.");

        return new LoginTeacherDetails(findTeacher.get());
    }
}

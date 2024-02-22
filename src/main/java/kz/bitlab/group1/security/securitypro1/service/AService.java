package kz.bitlab.group1.security.securitypro1.service;


import kz.bitlab.group1.security.securitypro1.model.Role;
import kz.bitlab.group1.security.securitypro1.model.Usser;
import kz.bitlab.group1.security.securitypro1.repository.RoleRepository;
import kz.bitlab.group1.security.securitypro1.repository.UsserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AService {

    private final UsserRepository usserRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final UsserService usserService;

    public Usser registerUsser(String email, String password, String rePassword, String fullName){
        Usser checkUsser=usserRepository.findByEmail(email);
        if (checkUsser==null){
            if (password.equals(rePassword)){
                List<Role> role=new ArrayList<>();
                Role userRole=roleRepository.findByRole("ROLE_USER");
                role.add(userRole);
                Usser usser=Usser
                        .builder()
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .fullName(fullName)
                        .role(role)
                        .build();
                return usserRepository.save(usser);
            }
        }
        return null;
    }

    public Usser updatePassword(String oldPassword, String newPassword, String repeatPassword){
        Usser currentUser=usserService.getCurrentUsers();
        if (currentUser!=null){
            if (newPassword.equals(repeatPassword)&&
            passwordEncoder.matches(oldPassword, currentUser.getPassword())){
                currentUser.setPassword(passwordEncoder.encode(newPassword));
                return usserRepository.save(currentUser);
            }
        }
        return null;
    }


}

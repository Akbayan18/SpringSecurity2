package kz.bitlab.group1.security.securitypro1.service;

import kz.bitlab.group1.security.securitypro1.model.Permission;
import kz.bitlab.group1.security.securitypro1.model.Userr;
import kz.bitlab.group1.security.securitypro1.repository.PermissionRepository;
import kz.bitlab.group1.security.securitypro1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final PermissionRepository permissionRepository;

    private final UserService userService;

    public Userr registerUser(String email, String password, String rePassword, String fullName){
        Userr checkUser=userRepository.findByEmail(email);
        if (checkUser==null){
            if(password.equals(rePassword)){
                List<Permission> permissions=new ArrayList<>();
                Permission userPermission=permissionRepository.findByPermission("ROLE_USER");
                permissions.add(userPermission);
                Userr userr=Userr
                        .builder()
                        .email(email)
                        .fullName(fullName)
                        .roles(permissions)
                        .password(passwordEncoder.encode(password))
                        .build();
                return userRepository.save(userr);
            }

        }
        return null;

    }

    public Userr updatePassword(String oldPassword, String newPassword, String repeatNewPassword) {
        Userr currentUser =userService.getCurrentUser();
        if (currentUser!= null) {
           if (newPassword.equals(repeatNewPassword)&&
                   passwordEncoder.matches(oldPassword, currentUser.getPassword())){
               currentUser.setPassword(passwordEncoder.encode(newPassword));
              return userRepository.save(currentUser);
           }
        }
        return null;
    }
}

package kz.bitlab.group1.security.securitypro1.service;

import kz.bitlab.group1.security.securitypro1.model.Userr;
import kz.bitlab.group1.security.securitypro1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Userr userr= userRepository.findByEmail(username);
        if (userr!=null) return userr;
        throw new UsernameNotFoundException("User Not Found");
    }
    public Userr getCurrentUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            Userr userr =(Userr) authentication.getPrincipal();
            return userr;
        }
        return null;
    }
}

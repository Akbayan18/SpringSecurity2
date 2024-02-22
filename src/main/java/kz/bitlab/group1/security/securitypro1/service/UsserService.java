package kz.bitlab.group1.security.securitypro1.service;


import kz.bitlab.group1.security.securitypro1.model.Usser;
import kz.bitlab.group1.security.securitypro1.repository.UsserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsserService implements UserDetailsService {
    private final UsserRepository usserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usser usser=usserRepository.findByEmail(username);
        if (usser!=null) return usser;
        throw new UsernameNotFoundException("User not found!");
    }

    public Usser getCurrentUsers(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            Usser usser= (Usser) authentication.getPrincipal();
            return usser;
        }
        return null;
    }

}

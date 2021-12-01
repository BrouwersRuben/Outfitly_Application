package be.kdg.outfitly.service;

import be.kdg.outfitly.domain.OutfitlyUserDetails;
import be.kdg.outfitly.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class OutfitlyUserDetailsService implements UserDetailsService {


    private final UserService userService;

    @Autowired
    public OutfitlyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);
        if (user==null){
            throw new UsernameNotFoundException("User: "+username+" not found.");
        }
        return new OutfitlyUserDetails(userService.findByEmail(username));
    }
}

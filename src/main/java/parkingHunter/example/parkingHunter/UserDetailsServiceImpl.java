package parkingHunter.example.parkingHunter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import parkingHunter.example.parkingHunter.Models.DBUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // Dependency Injection & IoC
    @Autowired
    parkingHunter.example.parkingHunter.Repos.DBUserRepository DBUserRepository;

    // Polymorphism
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DBUser dbUser = DBUserRepository.findByUsername(username);

        // Error handling ... the user is equal to null (doesn't exist in the database)
        if(dbUser == null){
            throw  new UsernameNotFoundException("The user "+ username + " does not exist");
        }
        return new ApplicationUser(dbUser);
    }
}

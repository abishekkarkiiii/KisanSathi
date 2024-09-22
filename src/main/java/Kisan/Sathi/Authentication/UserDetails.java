package Kisan.Sathi.Authentication;

import Kisan.Sathi.Entity.Doctor;
import Kisan.Sathi.Entity.Farmers;
import Kisan.Sathi.Model.AccountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.withUsername;
@Service
public class UserDetails implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    AccountModel accountModel;
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Farmers userAccount=accountModel.findUsers(username);
        if(userAccount!=null){
            org.springframework.security.core.userdetails.User.UserBuilder builder=withUsername(username);
            builder.password(userAccount.getPassword());
            builder.roles(userAccount.getRole());
            return builder.build();
        }else{
            System.out.println(username);
            Doctor doctor=accountModel.findDoctor(username);
            System.out.println(doctor);
            if(doctor!=null){
                org.springframework.security.core.userdetails.User.UserBuilder builder=withUsername(username);
                builder.password(doctor.getPassword());
                builder.roles(doctor.getRole());
                return builder.build();
            }else{
                System.out.println("sorry doctor cant found");
                return null;
            }

        }

    }
}

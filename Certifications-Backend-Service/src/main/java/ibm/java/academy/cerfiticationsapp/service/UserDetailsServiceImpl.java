package ibm.java.academy.cerfiticationsapp.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ibm.java.academy.cerfiticationsapp.implementation.UserDetailsImpl;
import ibm.java.academy.cerfiticationsapp.model.User;
import ibm.java.academy.cerfiticationsapp.repository.UserJpaRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
	UserJpaRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).
            orElseThrow(() -> new UsernameNotFoundException("User Not Found with this email: " + username));

        return UserDetailsImpl.build(user);
        
    }


}

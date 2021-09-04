package ibm.java.academy.cerfiticationsapp.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import ibm.java.academy.cerfiticationsapp.model.Role;
import ibm.java.academy.cerfiticationsapp.model.User;
import ibm.java.academy.cerfiticationsapp.repository.UserJpaRepository;
import ibm.java.academy.cerfiticationsapp.repository.RoleRepository;

@Service
public class UserService {
    
    @Autowired
    UserJpaRepository repo;

    @Autowired
    RoleRepository roleRepository;

    public User updateUser(String email, String firstname, String surname){
        User user = null;
        Optional<User> userOpt = repo.findByEmail(email);
        if(userOpt.isPresent()){
            user = userOpt.get();
            user.setName(firstname);
            user.setSurname(surname);
            return repo.save(user);
        }
        return user;
    }

    @Transactional
    public User assignRolesToUser(Long userId, List<Long> rolesIds) {
        User user = null;
        Optional<User> userOpt = repo.findById(userId);
        if(userOpt.isPresent()){
            user = userOpt.get();
            if(!CollectionUtils.isEmpty(rolesIds)) {
                Set<Role> foundRoles = roleRepository.findAllByIdIn(rolesIds);
                if(!CollectionUtils.isEmpty(foundRoles)) {
                    user.setRoles(foundRoles);
                    repo.save(user);
                }
            }
        }
        return user;
    }

}

package ibm.java.academy.cerfiticationsapp.UserInit;

import java.util.Arrays;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import ibm.java.academy.cerfiticationsapp.model.Role;
import ibm.java.academy.cerfiticationsapp.model.User;
import ibm.java.academy.cerfiticationsapp.model.Role.ERole;
import ibm.java.academy.cerfiticationsapp.repository.RoleRepository;
import ibm.java.academy.cerfiticationsapp.repository.UserJpaRepository;
import ibm.java.academy.cerfiticationsapp.service.UserService;

@Component
class UserCreator {

    @Autowired
    private UserJpaRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @PostConstruct
    public void init() {
        Role adminR = roleRepository.save(new Role(1L, ERole.ROLE_ADMIN));
        Role managerR = roleRepository.save(new Role(2L, ERole.ROLE_MANAGER));
        Role studentR = roleRepository.save(new Role(3L, ERole.ROLE_STUDENT));
        User admin = userRepository.save(new User(1L, "admin", "admin", "admin@localhost", new BCryptPasswordEncoder().encode("password")));
        User manager = userRepository.save(new User(2L, "manager", "manager", "manager@localhost", new BCryptPasswordEncoder().encode("password")));
        User student = userRepository.save(new User(3L, "student", "student", "student@localhost", new BCryptPasswordEncoder().encode("password")));
        userService.assignRolesToUser(admin.getId(), Arrays.asList(adminR.getId(), managerR.getId(), studentR.getId()));
        userService.assignRolesToUser(manager.getId(), Arrays.asList(managerR.getId(), studentR.getId()));
        userService.assignRolesToUser(student.getId(), Arrays.asList(studentR.getId()));
    }
}

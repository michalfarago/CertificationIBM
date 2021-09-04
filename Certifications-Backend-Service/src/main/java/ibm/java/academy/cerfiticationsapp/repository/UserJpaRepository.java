package ibm.java.academy.cerfiticationsapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import ibm.java.academy.cerfiticationsapp.model.User;

public interface UserJpaRepository extends JpaRepository<User, Long>{
    List<User> findAllByNameOrderBySurname(String name);
    Optional<User> findByEmail(@Param("email") String email);
    Boolean existsBySurname(String surname);
    Boolean existsByEmail(String email);
}

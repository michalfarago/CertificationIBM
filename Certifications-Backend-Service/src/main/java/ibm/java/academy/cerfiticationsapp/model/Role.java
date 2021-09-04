package ibm.java.academy.cerfiticationsapp.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    public enum ERole{
        ROLE_STUDENT, ROLE_ADMIN, ROLE_MANAGER
    }

    public Role(ERole name){
        this.name = name;
    }
    
    public Role(Long id, ERole name){
        this.id = id;
        this.name = name;
    }

    @JsonIgnoreProperties("roles")
    @ManyToMany(mappedBy = "roles")
	private Set<User> users;
}

package ibm.java.academy.cerfiticationsapp.implementation;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ibm.java.academy.cerfiticationsapp.model.User;

public class UserDetailsImpl implements UserDetails{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstname;
    private String surname;
    private String email;
    
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String firstname, String surname, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.firstname = firstname;
        this.surname = surname;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

    public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(), 
				user.getName(),
                user.getSurname(), 
				user.getEmail(),
				user.getPassword(), 
				authorities);
	}
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
		return this.id;
	}

	public String getEmail() {
		return this.email;
	}
    
    public String getName() {
		return this.firstname;
	}
    
    public String getSurname() {
		return this.surname;
	}
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

    @Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

}
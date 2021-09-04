package ibm.java.academy.cerfiticationsapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
@Table(name = "SKILL")
public class Skill {
	@Id
	@GeneratedValue
	private Long id;
		
	private String name;
		
	@Column(name = "desc")
	private String description;

	@JsonIgnoreProperties("skills")
    @ManyToMany(mappedBy = "skills")
	private List<Certification> certification;

	@Override
	public String toString() {
    return "Skill [id=" + id + ", name="
            + name + ", description=" + description + "]";
	}

	

}

package ibm.java.academy.cerfiticationsapp.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@SequenceGenerator(name="seq", sequenceName="h2seq",  initialValue=1000, allocationSize=10)
public class Certification extends Auditing<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;

    @Length(max = 50, message = "The name must be max 50 characters long")
    private String name;
    private String url;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Length(max = 3, min=3)
    private String currency;

    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "certification", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Voucher> vouchers;

    @JsonIgnoreProperties("certifications")
    @ManyToMany
    @JoinTable(name = "certification_skill", 
        joinColumns = @JoinColumn(name = "certification_id"), 
        inverseJoinColumns = @JoinColumn(name = "skill_id"), 
        uniqueConstraints = @UniqueConstraint(columnNames = {"skill_id", "certification_id"}, name = "UQ_CERT_SKILL"))
	private List<Skill> skills;

    public enum State{
        ACTIVE, PROPOSED, NEW
    }

    public Certification(Long id, String name, String url, BigDecimal price, String currency, State state) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.price = price;
        this.currency = currency;
        this.state = state;
    }

    public Certification(String name, String url, BigDecimal price, String currency, State state) {
        this.name = name;
        this.url = url;
        this.price = price;
        this.currency = currency;
        this.state = state;
    }

    
    
}

    

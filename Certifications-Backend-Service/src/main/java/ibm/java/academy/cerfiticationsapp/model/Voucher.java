package ibm.java.academy.cerfiticationsapp.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.SequenceGenerator;


@NoArgsConstructor
@Data
@Entity
@SequenceGenerator(name="seqVoucher", sequenceName="h2seqVoucher",  initialValue=1500, allocationSize=10)
public class Voucher {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqVoucher")
    private Long id;
	
	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
	private State state;
	
    @Column(length = 100, nullable = false)
	private String voucherCode;
	
	private Date validUntil;

    @JsonIgnoreProperties("vouchers")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    // @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "certification_id", nullable = false)
    @JsonBackReference
    private Certification certification;

    public enum State {
        ACTIVE, PROPOSED, NEW
    }

    @Override
    public String toString() {
        return "Voucher [certification name=" + certification.getName() + ", id=" + id + ", state=" + state + ", user name and surname=" + (user != null ? user.getName() + " " + user.getSurname(): null) 
                + ", validUntil=" + validUntil + ", voucherCode=" + voucherCode + "]";
    }



}


package ibm.java.academy.cerfiticationsapp.service;

import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import ibm.java.academy.cerfiticationsapp.model.Certification;
import ibm.java.academy.cerfiticationsapp.model.Skill;
import ibm.java.academy.cerfiticationsapp.model.Voucher;
import ibm.java.academy.cerfiticationsapp.repository.CertificationJpaRepository;
import ibm.java.academy.cerfiticationsapp.repository.SkillJpaRepository;
import lombok.extern.java.Log;

@Log
@Service
@Validated
public class CertificationService {

    @Autowired
    private CertificationJpaRepository certificationJpaRepository;

    @Autowired
    private SkillJpaRepository skillJpaRepository;

    public Certification saveCertification(@Valid Certification certification){
        return certificationJpaRepository.save(certification);
    }

    @Transactional
    public Certification updateAndSave(Long certId, String name, List<Long> skillIds) {
        Certification certification = null;
        Optional<Certification> certOpt = certificationJpaRepository.findById(certId);
        
		if(certOpt.isPresent()) {
			Certification cert = certOpt.get();
            if(StringUtils.hasText(name)) {
                cert.setName(name);
            }	
            if(!CollectionUtils.isEmpty(skillIds)) {
                List<Skill> foundSkills = skillJpaRepository.findAllByIdIn(skillIds);
                if(!CollectionUtils.isEmpty(foundSkills)) {
                    cert.setSkills(foundSkills);
                }
            }
            	
			certification = saveCertification(cert);
            
			log.info("Certification modified: " + certification.toString());
		}
        return certification;
    }

    public BigDecimal getSumPrice() {
        List<Certification> allCerts = certificationJpaRepository.findAll();
        BigDecimal sum = allCerts.stream().filter(x -> x.getPrice() != null).map(x -> x.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum;
    }

    public void deleteCertification(long id) {
        Optional<Certification> certification = certificationJpaRepository.findById(id);
        if (certification.isPresent()) {
            certificationJpaRepository.delete(certification.get());
        }
    }

    public List<Skill> getCertificationSkills(long id) {
        Optional<Certification> certification = certificationJpaRepository.findById(id);
        if (certification.isPresent()) {
            return certification.get().getSkills();
        }
        return Collections.emptyList();
    }

    public List<Voucher> getCertificationVouchers(long id) {
        Optional<Certification> certification = certificationJpaRepository.findById(id);
        if (certification.isPresent()) {
            return certification.get().getVouchers();
        }
        return Collections.emptyList();
    }
    
}

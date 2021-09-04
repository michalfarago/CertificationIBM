package ibm.java.academy.cerfiticationsapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ibm.java.academy.cerfiticationsapp.model.Certification;
import ibm.java.academy.cerfiticationsapp.model.Skill;
import ibm.java.academy.cerfiticationsapp.repository.CertificationJpaRepository;
import ibm.java.academy.cerfiticationsapp.repository.SkillJpaRepository;

@Controller
public class SkillController {
    @Autowired
    SkillJpaRepository skillJpaRepository;
    
    @Autowired
    CertificationJpaRepository certificationJpaRepository;

    @PostMapping(path = "/update-skill", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Skill updateSkill(@RequestBody Skill skill) {
   
        Optional<Skill> skillOpt = skillJpaRepository.findById(skill.getId());
        Skill oldSkill = skillOpt.get();

        oldSkill.setName(skill.getName() != null ? skill.getName() : oldSkill.getName());
        oldSkill.setDescription(skill.getDescription() != null ? skill.getDescription() : oldSkill.getDescription());

        skillJpaRepository.save(oldSkill);
        return skill;
    }

    @DeleteMapping("/delete-skill")
    @ResponseBody
    public boolean deleteUser(@RequestParam("id") Long id) {
        Skill skillToDelete = skillJpaRepository.getById(id);
        List<Certification> toSever = skillToDelete.getCertification();
        for(Certification certification : toSever){
            certification.getSkills().remove(skillToDelete);
            certificationJpaRepository.save(certification);
        }
        skillJpaRepository.deleteById(id);
        return true;
    }

}

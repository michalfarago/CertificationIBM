package ibm.java.academy.cerfiticationsapp.wrapper;

import java.util.List;

import ibm.java.academy.cerfiticationsapp.model.Certification;

public class updateCertificateWraper{
    private Certification certification;

    public Certification getCertification() {
        return certification;
    }

    public void setCertification(Certification certification) {
        this.certification = certification;
    }

    private List<Long> skills;

    public List<Long> getSkills() {
        return skills;
    }

    public void setSkills(List<Long> skills) {
        this.skills = skills;
    }

    
}

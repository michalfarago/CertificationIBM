package ibm.java.academy.cerfiticationsapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibm.java.academy.cerfiticationsapp.model.Certification;
import ibm.java.academy.cerfiticationsapp.model.User;
import ibm.java.academy.cerfiticationsapp.model.Voucher;
import ibm.java.academy.cerfiticationsapp.payload.request.VoucherRequest;
import ibm.java.academy.cerfiticationsapp.repository.CertificationJpaRepository;
import ibm.java.academy.cerfiticationsapp.repository.UserJpaRepository;
import ibm.java.academy.cerfiticationsapp.repository.VoucherJpaRepository;


@Service
public class VoucherService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private VoucherJpaRepository voucherJpaRepository;

    @Autowired
    private CertificationJpaRepository certificationJpaRepository;
          
    
    public Voucher assignVoucherToUser(Long voucherId, Long userId) {
        Voucher assigned = null;
        Optional<Voucher> voucherOptional = voucherJpaRepository.findById(voucherId);
        Optional<User> userOptional = userJpaRepository.findById(userId);
        if(voucherOptional.isPresent() && userOptional.isPresent()) {
            assigned = voucherOptional.get();
            assigned.setUser(userOptional.get());
        }
        return voucherJpaRepository.save(assigned);
    }

    public void deleteVoucher(Long id) {
        voucherJpaRepository.deleteById(id);
    }

    public List<Voucher> getVouchers(){
        return voucherJpaRepository.findAll();
    }

    public Voucher addVoucher(VoucherRequest voucher) {
        Voucher v = new Voucher();
        v.setState(voucher.getState());
        v.setVoucherCode(voucher.getVoucherCode());
        v.setValidUntil(voucher.getValidUntil());
        v.setCertification(certificationJpaRepository.findById(voucher.getCertificationId()).get());
        if(voucher.getUserId() != null) {
            Optional<User> userOptional = userJpaRepository.findById(voucher.getUserId());
            if(userOptional.isPresent()){
                v.setUser(userOptional.get());
            }
        }

        voucherJpaRepository.save(v);
        return v;
    }

    public Certification getCertificationFromVoucherById(Long id){
        return voucherJpaRepository.findById(id).map(m -> m.getCertification()).orElse(null);
    }

    public User getUserFromVoucherById(Long id){
        return voucherJpaRepository.findById(id).map(m -> m.getUser()).orElse(null);
    }
}

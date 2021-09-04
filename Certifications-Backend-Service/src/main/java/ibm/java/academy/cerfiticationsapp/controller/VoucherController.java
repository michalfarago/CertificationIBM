package ibm.java.academy.cerfiticationsapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ibm.java.academy.cerfiticationsapp.model.Certification;
import ibm.java.academy.cerfiticationsapp.model.User;
import ibm.java.academy.cerfiticationsapp.model.Voucher;
import ibm.java.academy.cerfiticationsapp.payload.request.VoucherRequest;
import ibm.java.academy.cerfiticationsapp.service.VoucherService;

@RestController()
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @PostMapping(path = "/voucherRequest", produces = "application/json")
    public Voucher addVoucher(@RequestBody VoucherRequest voucher) {
        return voucherService.addVoucher(voucher);
    }

    @GetMapping(path = "/vouchers/{voucherId}/certification")
    public Certification getCertificationFromVoucherById(@PathVariable("voucherId") Long id){
        return voucherService.getCertificationFromVoucherById(id);
    }

    @GetMapping(path = "/vouchers/{id}/user")
    public User getUserFromVoucherById(@PathVariable("id") Long id){
        return voucherService.getUserFromVoucherById(id);
    }

    @PostMapping(path = "/voucher/{voucherId}/user/{userId}", produces = "application/json")
    public Voucher assignVoucherToUser(@PathVariable("voucherId") Long voucherId, @PathVariable("userId") Long userId){
        return voucherService.assignVoucherToUser(voucherId, userId);
    }
    

}
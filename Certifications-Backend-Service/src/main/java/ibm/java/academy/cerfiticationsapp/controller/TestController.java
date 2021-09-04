package ibm.java.academy.cerfiticationsapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ibm.java.academy.cerfiticationsapp.model.User;
import ibm.java.academy.cerfiticationsapp.model.Voucher;
import ibm.java.academy.cerfiticationsapp.payload.request.UpdateRequest;
import ibm.java.academy.cerfiticationsapp.payload.request.VoucherUpdateRequest;
import ibm.java.academy.cerfiticationsapp.payload.response.MessageResponse;
import ibm.java.academy.cerfiticationsapp.repository.UserJpaRepository;
import ibm.java.academy.cerfiticationsapp.repository.VoucherJpaRepository;
import ibm.java.academy.cerfiticationsapp.service.UserService;
import ibm.java.academy.cerfiticationsapp.service.VoucherService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	UserService userService;

	@Autowired
	VoucherService voucherService;

	@Autowired
	UserJpaRepository repo;

	@Autowired
	VoucherJpaRepository repoVoucher;

    @GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('STUDENT') or hasRole('MANAGER') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MANAGER')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	@PostMapping("/users/update/")
	@PreAuthorize("hasRole('STUDENT') or hasRole('MANAGER') or hasRole('ADMIN')")
	public ResponseEntity<?> udpateUser(@Valid @RequestBody UpdateRequest updateRequest) {
		User user = userService.updateUser(updateRequest.getEmail(), updateRequest.getFirstname(), updateRequest.getSurname());
		if(user == null){
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Something is wrong!"));
		}
		return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
	}


	@PostMapping("/vouchers/update/")
	@PreAuthorize("hasRole('STUDENT') or hasRole('MANAGER') or hasRole('ADMIN')")
	public ResponseEntity<?> udpateVoucher(@Valid @RequestBody VoucherUpdateRequest updateRequest) {
		Voucher voucher = voucherService.assignVoucherToUser(updateRequest.getVoucherId(), updateRequest.getUserId());
		if(voucher == null){
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Something is wrong!"));
		}
		return ResponseEntity.ok(new MessageResponse("Voucher updated successfully!"));
	}

	@GetMapping(path = "/users/except/{id}")
    @ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
    public List<User> getCertificationFromVoucherById(@PathVariable("id") Long id){
        List<User> users = repo.findAll();
		users.remove(repo.getById(id));
		return users;
    }

	@DeleteMapping("/delete-user/{id}")
    @ResponseBody
	@PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable("id") Long id) {
        User user = repo.findById(id).get();
		for(Voucher v: user.getVoucher()){
			repoVoucher.delete(v);
		}
		repo.delete(user);
    }
}

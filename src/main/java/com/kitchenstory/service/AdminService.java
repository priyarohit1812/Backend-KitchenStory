package com.kitchenstory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitchenstory.model.Admin;
import com.kitchenstory.repository.IAdminRepository;

@Service(value = "adminService")
public class AdminService implements IAdminService {
	@Autowired
	private IAdminRepository adminRepository;
	@Autowired
	private ISecurityService securityService;
	
	@Override
	public List<Admin> fetchAdminList() {
		return this.adminRepository.findAll();
	}

	@Override
	public Admin saveAdmin(Admin admin) {
		String hashedPassword = this.securityService.generateHash(admin.getPassword());
		admin.setPassword(hashedPassword);
		return this.adminRepository.save(admin);
	}

	@Override
	public boolean deleteAdmin(Long adminId) {
		this.adminRepository.deleteById(adminId);
		return !this.adminRepository.existsById(adminId);
	}

	@Override
	public Admin getAdmin(String emailId, String password) {
		String hashedPassword = this.securityService.generateHash(password);
		Optional<Admin> optAdmin = this.adminRepository.findByEmailAndPassword(emailId, hashedPassword);
		if (optAdmin !=null && optAdmin.isPresent()) {
			return optAdmin.get();
		} else {
			return null;
		}
	}

	@Override
	public Admin getAdminById(Long adminId) {
		Optional<Admin> optAdmin = this.adminRepository.findById(adminId);
		if (optAdmin !=null && optAdmin.isPresent()) {
			return optAdmin.get();
		}else {
			return null;
		}
	}

	@Override
	public List<Admin> searchAdmin(String key) {
		return this.adminRepository.findByFirstNameContainsOrLastNameContainsOrEmailContainsOrMobileContains(key, key, key, key);
	}

}

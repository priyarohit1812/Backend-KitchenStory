package com.kitchenstory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitchenstory.model.Address;
import com.kitchenstory.model.User;
import com.kitchenstory.repository.IUserRepository;

@Service(value = "userService")
public class UserService implements IUserService {
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private ISecurityService securityService;
	
	@Autowired
	private IAddressService addressService;
	
	@Override
	public List<User> fetchUserList() {
		try {
			return this.userRepository.findAll();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public User saveUser(User user) {
		String hashedPassword = this.securityService.generateHash(user.getPassword());
		user.setPassword(hashedPassword);
		return this.userRepository.save(user);
	}

	@Override
	public boolean deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
		return !this.userRepository.existsById(userId);
		
	}

	@Override
	public User getUser(String email, String password) {
		String hashedPassword = this.securityService.generateHash(password);
		Optional<User> optUser = this.userRepository.findByEmailAndPassword(email, hashedPassword);
		if (optUser!= null && optUser.isPresent()) {
			return optUser.get();
		}

		return null;
	}

	@Override
	public User getUserById(Long userId) {
		Optional<User> optUser = this.userRepository.findById(userId);
		if (optUser !=null && optUser.isPresent()) {
			return optUser.get();
		}else {
			return null;
		}
	}

	@Override
	public boolean deleteAddresssForUser(Long userId) {
		
			boolean isDeleted = true;
			List<Address> addresses = this.addressService.fetchAddressListByUser(userId);
			
			if (addresses != null && !addresses.isEmpty()) {
				for (Address address : addresses) {
					isDeleted = isDeleted && this.addressService.deleteAddress(address.getAddressId());
				} 
			}
			
			return isDeleted;
		}

	@Override
	public User updateUser(User user) {
		this.userRepository.updateUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getMobile(), user.getUserId());
		return this.getUserById(user.getUserId());
	}

		
}

package com.kitchenstory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitchenstory.model.Address;
import com.kitchenstory.repository.IAddressRepository;

@Service(value = "addressService")
public class AddressService implements IAddressService {
	@Autowired
	private IAddressRepository addressRepository;
	
	@Override
	public List<Address> fetchAddressList() {
		return this.addressRepository.findAll();
	}

	@Override
	public Address getAddress(Long addressId) {
		Optional<Address> optAddress = this.addressRepository.findById(addressId);
		if (optAddress !=null && optAddress.isPresent()) {
			return optAddress.get();
		}else {
			return null;
		}
	}

	@Override
	public Address saveAddress(Address address) {
		return this.addressRepository.save(address);
	}

	@Override
	public boolean deleteAddress(Long addressId) {
		this.addressRepository.deleteById(addressId);
		return !this.addressRepository.existsById(addressId);
	}

	@Override
	public List<Address> fetchAddressListByUser(Long UserId) {
		return this.addressRepository.findByUserId(UserId);
	}

}

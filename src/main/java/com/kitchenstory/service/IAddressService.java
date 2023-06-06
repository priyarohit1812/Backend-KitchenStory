package com.kitchenstory.service;

import java.util.List;

import com.kitchenstory.model.Address;

public interface IAddressService {
	public List<Address> fetchAddressList();
	public List<Address> fetchAddressListByUser(Long UserId);
	public Address getAddress(Long addressId);
	public Address saveAddress(Address address);
	boolean deleteAddress(Long addressId);
}

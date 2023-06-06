package com.kitchenstory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kitchenstory.model.Address;
import com.kitchenstory.model.User;
import com.kitchenstory.model.responses.BaseResponse;
import com.kitchenstory.service.IAddressService;
import com.kitchenstory.service.IUserService;

@RestController()
@RequestMapping("/user/address")
public class AddressController {
	
	@Autowired
	private IAddressService addressService;
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<BaseResponse<?>> saveAddress(@RequestAttribute("userId") Long userId, @RequestBody Address address){
		BaseResponse<?> response = new BaseResponse<>();
		if (address==null) {
			response.setIsError(true);
			response.setMessage("Request object is null");
			return ResponseEntity.badRequest().body(response);
		}
		
		User user = this.userService.getUserById(userId);
		address.setUser(user);
		
		Address savedAddress = this.addressService.saveAddress(address);
		if (savedAddress == null) {
			response.setIsError(true);
			response.setMessage("Could not save the address");
			return ResponseEntity.internalServerError().body(response);
			
		}
		
		response.setIsError(false);
		response.setMessage("Address saved successfully!");
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/list")
	public ResponseEntity<BaseResponse<List<Address>>> getAllAddresss(@RequestAttribute("userId") Long userId) {
		BaseResponse<List<Address>> response = new BaseResponse<List<Address>>();
		try {
			List<Address> allAddresss = this.addressService.fetchAddressListByUser(userId);
			if (allAddresss == null || allAddresss.isEmpty()) {
				response.setMessage("No address found");
			}
			response.setIsError(false);
			response.setResponse(allAddresss);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setIsError(true);
			response.setMessage("Could not fetch addresss. " + e.getMessage());
			response.setResponse(null);
			return ResponseEntity.internalServerError().body(response);
		}
	}
	
	
	
	@GetMapping("/{addressId}")
	public ResponseEntity<BaseResponse<Address>> getAddress(@RequestAttribute("userId") Long userId, @PathVariable Long addressId){
		BaseResponse<Address> response = new BaseResponse<Address>();
		if (addressId<=0) {
			response.setIsError(true);
			response.setMessage("Please provide address id to fetch address");
			return ResponseEntity.badRequest().body(response);
		}
		
		Address address = this.addressService.getAddress(addressId);
		if (address==null) {
			response.setIsError(true);
			response.setMessage("Could not fetch the requested address");
			return ResponseEntity.internalServerError().body(response);
		}
		
		response.setIsError(false);
		response.setMessage("Address found!");
		response.setResponse(address);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{addressId}")
	public ResponseEntity<BaseResponse<?>> deleteAddress(@RequestAttribute("userId") Long userId, @PathVariable Long addressId ){
		BaseResponse<Address> response = new BaseResponse<Address>();
		if (addressId<=0) {
			response.setIsError(true);
			response.setMessage("Please provide address id to fetch address");
			return ResponseEntity.badRequest().body(response);
		}
		
		boolean isDeleted = this.addressService.deleteAddress(addressId);
		if (isDeleted) {
			response.setIsError(true);
			response.setMessage("Address deleted successfully!");
			return ResponseEntity.ok(response);
		}else {
			response.setIsError(true);
			response.setMessage("Could not delete the requested address");
			return ResponseEntity.internalServerError().body(response);
		}
		
	}
	

}

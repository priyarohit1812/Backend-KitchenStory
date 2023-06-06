package com.kitchenstory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kitchenstory.model.Admin;
import com.kitchenstory.model.OrderItem;
import com.kitchenstory.model.PurchaseOrder;
import com.kitchenstory.model.User;
import com.kitchenstory.model.requests.LoginRequest;
import com.kitchenstory.model.responses.BaseResponse;
import com.kitchenstory.model.responses.PurchaseOrderResponse;
import com.kitchenstory.service.IAdminService;
import com.kitchenstory.service.IOrderItemService;
import com.kitchenstory.service.IPurchaseOrderService;
import com.kitchenstory.service.ISecurityService;
import com.kitchenstory.service.IUserService;

@RestController()
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private IAdminService adminService;
	@Autowired
	private IUserService userService;	
	@Autowired
	private IOrderItemService orderItemService;
	@Autowired
	private IPurchaseOrderService purchaseOrderService;
	@Autowired
	private ISecurityService securityService;
	
	@GetMapping("/list")
	public ResponseEntity<BaseResponse<List<Admin>>> getAllAdmins(){
		BaseResponse<List<Admin>> response = new BaseResponse<List<Admin>>();
		try {
			List<Admin> allAdmins = this.adminService.fetchAdminList();
			if (allAdmins == null || allAdmins.isEmpty() ) {
				response.setIsError(true);
				response.setMessage("No admin found");
				return ResponseEntity.badRequest().body(response);
			}
			
			response.setIsError(false);
			response.setResponse(allAdmins);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setIsError(true);
			response.setMessage("Could not fetch admins." + e.getMessage());
			response.setResponse(null);
			return ResponseEntity.internalServerError().body(response);
		}			
	}
	
	@PostMapping("/save")
	public ResponseEntity<BaseResponse<Admin>> saveAdmin(@RequestBody Admin admin){
		BaseResponse<Admin> response = new BaseResponse<Admin>();
		if (admin==null) {
			response.setIsError(true);
			response.setMessage("Request object is null");
			return ResponseEntity.badRequest().body(response);
		}
		
		Admin savedAdmin = this.adminService.saveAdmin(admin);
		if (savedAdmin == null) {
			response.setIsError(true);
			response.setMessage("Could not save the requested admin");
			return ResponseEntity.internalServerError().body(response);
			
		}
		
		response.setIsError(false);
		response.setMessage("Admin saved successfully!");
		response.setResponse(savedAdmin);
		return ResponseEntity.ok(response);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<BaseResponse<String>> loginAdmin(@RequestBody LoginRequest request){
		BaseResponse<String> response = new BaseResponse<String>();
		if (request==null) {
			response.setIsError(true);
			response.setMessage("Request object is null");
			return ResponseEntity.badRequest().body(response);
		}
		
		Admin admin = this.adminService.getAdmin(request.getUsername(), request.getPassword());
		if (admin==null) {
			response.setIsError(true);
			response.setMessage("Invalid username or password");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
		
		response.setIsError(false);
		response.setMessage("Admin saved successfully!");
		response.setResponse(this.securityService.generateJWTToken(admin.getUserId()));
		return ResponseEntity.ok(response);
 	}
	
	@GetMapping("/{adminId}")
	public ResponseEntity<BaseResponse<Admin>> getAdmin(@PathVariable Long adminId){
		BaseResponse<Admin> response = new BaseResponse<Admin>();
		if (adminId<=0) {
			response.setIsError(true);
			response.setMessage("Please provide admin id to fetch admin");
			return ResponseEntity.badRequest().body(response);
		}
		
		Admin admin = this.adminService.getAdminById(adminId);
		if (admin==null) {
			response.setIsError(true);
			response.setMessage("Could not fetch the requested admin");
			return ResponseEntity.internalServerError().body(response);
		}
		
		response.setIsError(false);
		response.setMessage("Admin found!");
		response.setResponse(admin);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/delete/{adminId}")
	public ResponseEntity<BaseResponse<?>> deleteAdmin(@PathVariable Long adminId ){
		BaseResponse<Admin> response = new BaseResponse<Admin>();
		if (adminId<=0) {
			response.setIsError(true);
			response.setMessage("Please provide admin id to fetch admin");
			return ResponseEntity.badRequest().body(response);
		}
		
		boolean isDeleted = this.adminService.deleteAdmin(adminId);
		if (isDeleted) {
			response.setIsError(true);
			response.setMessage("Admin deleted successfully!");
			return ResponseEntity.ok(response);
		}else {
			response.setIsError(true);
			response.setMessage("Could not delete the requested admin");
			return ResponseEntity.internalServerError().body(response);
		}		
	}
	
	
	@GetMapping("/user/list")
	public ResponseEntity<BaseResponse<List<User>>> getAllUsers(){
		BaseResponse<List<User>> response = new BaseResponse<List<User>>();
		try {
			List<User> allUsers = this.userService.fetchUserList();
			if (allUsers == null || allUsers.isEmpty() ) {
				response.setIsError(true);
				response.setMessage("No user found");
				return ResponseEntity.badRequest().body(response);
			}
			
			response.setIsError(false);
			response.setResponse(allUsers);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setIsError(true);
			response.setMessage("Could not fetch users." + e.getMessage());
			response.setResponse(null);
			return ResponseEntity.internalServerError().body(response);
		}			
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<BaseResponse<User>> getUser(@PathVariable Long userId){
		BaseResponse<User> response = new BaseResponse<User>();
		if (userId<=0) {
			response.setIsError(true);
			response.setMessage("Please provide admin id to fetch admin");
			return ResponseEntity.badRequest().body(response);
		}
		
		User user = this.userService.getUserById(userId);
		if (user==null) {
			response.setIsError(true);
			response.setMessage("Could not fetch the requested user");
			return ResponseEntity.internalServerError().body(response);
		}
		
		response.setIsError(false);
		response.setMessage("user found!");
		response.setResponse(user);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<BaseResponse<PurchaseOrderResponse>> getPurchaseOrder(@RequestAttribute("adminId") Long adminId,
			@PathVariable Long orderId) {
		BaseResponse<PurchaseOrderResponse> response = new BaseResponse<PurchaseOrderResponse>();

		if (orderId <= 0) {
			response.setIsError(true);
			response.setMessage("Please provide order number/id");
			return ResponseEntity.badRequest().body(response);
		}

		PurchaseOrder order = this.purchaseOrderService.getPurchaseorder(orderId);
		List<OrderItem> orderItems = this.orderItemService.fetchOrderItemsByCart(order.getCart().getCartId());

		PurchaseOrderResponse orderResponse = new PurchaseOrderResponse(order, orderItems);
		response.setIsError(false);
		response.setMessage("Order fetched successfully!");
		response.setResponse(orderResponse);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/order/list")
	public ResponseEntity<BaseResponse<List<PurchaseOrder>>> getAllPurchaseOrders(@RequestAttribute("adminId") Long adminId) {
		BaseResponse<List<PurchaseOrder>> response = new BaseResponse<List<PurchaseOrder>>();

		List<PurchaseOrder> orders = this.purchaseOrderService.fetchPurchaseOrderList();

		response.setIsError(false);
		response.setMessage("Orders fetched successfully!");
		response.setResponse(orders);
		return ResponseEntity.ok(response);
	}
}

package com.kitchenstory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.kitchenstory.model.Admin;
import com.kitchenstory.service.IAdminService;

@Component
public class DataLoader implements ApplicationRunner {
	@Autowired
	IAdminService adminService;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<Admin> allAdmins = this.adminService.fetchAdminList();
		if (allAdmins == null || allAdmins.isEmpty()) {
			Admin firstAdmin = new Admin(0L,"admin@kitchenstory.com","Administrator","","9876543210","KitchenStory@2023");
			this.adminService.saveAdmin(firstAdmin);
		}

	}

}

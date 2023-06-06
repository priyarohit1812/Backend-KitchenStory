package com.kitchenstory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.kitchenstory.model.PurchaseOrder;
import com.kitchenstory.repository.IPurchaseOrderRepository;

@Service(value = "purchaseOrderService")
public class PurchaseOrderService implements IPurchaseOrderService {
	@Autowired
	private IPurchaseOrderRepository purchaseOrderRepository;
	
	@Override
	public List<PurchaseOrder> fetchPurchaseOrderList() {
		return this.purchaseOrderRepository.findAll(Sort.by(Direction.DESC, "OrderDate"));
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrderByAddressId(Long addressId) {
		return null;
	}

	@Override
	public PurchaseOrder getPurchaseorder(Long purchaseOrderId) {
		Optional<PurchaseOrder> optPurchaseOrder = this.purchaseOrderRepository.findById(purchaseOrderId);
		if (optPurchaseOrder !=null && optPurchaseOrder.isPresent()) {
			return optPurchaseOrder.get();
		}else {
			return null;
		}
	}

	@Override
	public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder) {
		return this.purchaseOrderRepository.save(purchaseOrder);
	}

	@Override
	public boolean deletePurchaseOrder(Long purchaseOrderId) {
		this.purchaseOrderRepository.deleteById(purchaseOrderId);
		return !this.purchaseOrderRepository.existsById(purchaseOrderId);
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrderByUserId(Long userId) {
		return this.purchaseOrderRepository.getOrdersByUser(userId);
	}

}

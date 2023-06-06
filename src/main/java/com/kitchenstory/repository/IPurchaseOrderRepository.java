package com.kitchenstory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kitchenstory.model.PurchaseOrder;

public interface IPurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
	@Query(nativeQuery = true, value = "SELECT po.* FROM ks_purchaseorder po JOIN ks_cart c ON c.cart_id = po.cart_id_fk JOIN ks_user u ON u.user_id = c.user_id_fk WHERE u.user_id = :userId ORDER BY po.order_date DESC")
	List<PurchaseOrder> getOrdersByUser(@Param("userId") Long userId);
}

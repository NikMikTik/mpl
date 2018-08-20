package com.flipkart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flipkart.model.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
	Merchant findByMerchantEmail(String merchantEmail);
	Merchant findByMerchantId(int merchantId);
}

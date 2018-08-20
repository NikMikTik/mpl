package com.flipkart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flipkart.model.Merchant;
import com.flipkart.model.MerchantAddress;
@Repository
public interface MerchantAddressRepository extends JpaRepository<MerchantAddress, Integer> {

}

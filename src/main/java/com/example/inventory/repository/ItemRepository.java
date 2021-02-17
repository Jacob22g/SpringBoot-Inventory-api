package com.example.inventory.repository;

import com.example.inventory.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

//    List<Item> WithdrawalQuantity(Long id, Long quantity);
//    List<Item> DepositQuantity(Long id, Long quantity);

    List<Item> findByInventoryCode(Long inventoryCode);
}

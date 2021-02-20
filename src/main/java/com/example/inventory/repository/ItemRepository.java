package com.example.inventory.repository;

import com.example.inventory.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // find all items with specific inventory code
    List<Item> findByInventoryCode(Long inventoryCode);
}

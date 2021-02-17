package com.example.inventory.controller;

import com.example.inventory.model.Item;
import com.example.inventory.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems(@RequestParam(required = false) Long inventoryCode){
        try {
            List<Item> items = new ArrayList<>();
            if (inventoryCode==null){
                itemRepository.findAll().forEach(items::add);
            } else {
                itemRepository.findByInventoryCode(inventoryCode).forEach(items::add);
            }
            if (items.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") long id) {
        try {
            Optional<Item> itemData = itemRepository.findById(id);
            if (itemData.isPresent()) {
                return new ResponseEntity<>(itemData.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        try {
            Item _item = itemRepository.save(new Item(item));
            return new ResponseEntity<>(_item, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItemAmount(@PathVariable("id") long id,
                                           @RequestBody Item item
//                                           Long amount
    ) {
        try {
            Optional<Item> itemData = itemRepository.findById(id);
            if (itemData.isPresent()) {
                Item _item = itemData.get();
                _item.setName(item.getName());
                _item.setAmount(item.getAmount());
                _item.setInventoryCode(item.getInventoryCode());
//                _item.setAmount(amount);
                return new ResponseEntity<>(itemRepository.save(_item), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/items")
    public ResponseEntity<Item> updateItemAmount(@RequestBody Item item) {
        try {
            Optional<Item> itemData = itemRepository.findById(item.getId());
            if (itemData.isPresent()) {
                Item _item = itemData.get();
                _item.setName(item.getName());
                _item.setAmount(item.getAmount());
                _item.setInventoryCode(item.getInventoryCode());
                return new ResponseEntity<>(itemRepository.save(_item), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable("id") long id) {
        try {
            Optional<Item> itemData = itemRepository.findById(id);
            if (itemData.isPresent()) {
                itemRepository.deleteById(id);
                return new ResponseEntity<>(itemData.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

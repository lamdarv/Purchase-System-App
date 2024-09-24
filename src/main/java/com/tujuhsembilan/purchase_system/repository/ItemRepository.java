package com.tujuhsembilan.purchase_system.repository;

import com.tujuhsembilan.purchase_system.model.Item;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class ItemRepository {

    // Daftar harga keyboard
    public List<Item> getKeyboards() {
        return Arrays.asList(
                new Item("Keyboard A", 45000),
                new Item("Keyboard B", 30000),
                new Item("Keyboard C", 20000)
        );
    }

    // Daftar harga mouse
    public List<Item> getMice() {
        return Arrays.asList(
                new Item("Mouse A", 12000),
                new Item("Mouse B", 20000),
                new Item("Mouse C", 35000)
        );
    }
}

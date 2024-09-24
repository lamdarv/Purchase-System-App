package com.tujuhsembilan.purchase_system.service;

import com.tujuhsembilan.purchase_system.dto.PurchaseDTO;
import com.tujuhsembilan.purchase_system.model.Item;
import com.tujuhsembilan.purchase_system.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseService {

    @Autowired
    private ItemRepository itemRepository;

    // Metode untuk mencari kombinasi barang yang paling mendekati
    public Map<String, Object> findOptimalPurchase(PurchaseDTO purchaseDTO) {
        double inputMoney = purchaseDTO.getInputMoney();

        List<Item> keyboards = itemRepository.getKeyboards();
        List<Item> mice = itemRepository.getMice();

        double closestTotal = 0;
        Item bestKeyboard = null;
        Item bestMouse = null;

        // Cari kombinasi terbaik (keyboard + mouse)
        for (Item keyboard : keyboards) {
            for (Item mouse : mice) {
                double total = keyboard.getPrice() + mouse.getPrice();
                if (total <= inputMoney && total > closestTotal) {
                    closestTotal = total;
                    bestKeyboard = keyboard;
                    bestMouse = mouse;
                }
            }
        }

        // Jika tidak ada kombinasi keyboard + mouse, cari barang individual
        if (bestKeyboard == null && bestMouse == null) {
            // Cari keyboard terbaik
            for (Item keyboard : keyboards) {
                if (keyboard.getPrice() <= inputMoney && keyboard.getPrice() > closestTotal) {
                    closestTotal = keyboard.getPrice();
                    bestKeyboard = keyboard;
                }
            }

            // Cari mouse terbaik
            for (Item mouse : mice) {
                if (mouse.getPrice() <= inputMoney && mouse.getPrice() > closestTotal) {
                    closestTotal = mouse.getPrice();
                    bestMouse = mouse;
                    bestKeyboard = null; // Reset keyboard jika mouse lebih mendekati
                }
            }
        }

        // Bangun respons
        Map<String, Object> response = new HashMap<>();
        if (bestKeyboard != null || bestMouse != null) {
            Map<String, Object> data = new HashMap<>();
            if (bestKeyboard != null) {
                data.put("keyboard", bestKeyboard);
            }
            if (bestMouse != null) {
                data.put("mouse", bestMouse);
            }
            data.put("totalSpent", closestTotal);

            response.put("data", data);
            response.put("message", "Purchase optimized for input money: " + inputMoney);
        } else {
            response.put("message", "Unable to find optimal purchase within the budget");
        }

        return response;
    }
}

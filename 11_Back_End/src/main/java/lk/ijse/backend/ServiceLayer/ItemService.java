package lk.ijse.backend.ServiceLayer;

import lk.ijse.backend.DTO.ItemDTO;
import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    void updateItem(ItemDTO itemDTO);
    void deleteItemById(String iid); // ID එකෙන් delete කරන්න
    List<ItemDTO> getAllItems();     // Table එකට දත්ත ගන්න
    ItemDTO getItemById(String id);
    void reduceStock(String itemId, int qty);
}
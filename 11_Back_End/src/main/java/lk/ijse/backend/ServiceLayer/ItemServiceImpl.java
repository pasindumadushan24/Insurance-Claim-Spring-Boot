package lk.ijse.backend.ServiceLayer;

import jakarta.transaction.Transactional;


import lk.ijse.backend.DTO.ItemDTO;
import lk.ijse.backend.Entity.Item;
import lk.ijse.backend.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemRepository.save(new Item(itemDTO.getIid(), itemDTO.getIName(), itemDTO.getQty(), itemDTO.getPrice()));
    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        if(itemRepository.existsById(itemDTO.getIid())) {
            itemRepository.save(new Item(itemDTO.getIid(), itemDTO.getIName(), itemDTO.getQty(), itemDTO.getPrice()));
        }
    }

    @Override
    public void deleteItemById(String iid) {
        itemRepository.deleteById(iid);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll().stream()
                .map(item -> new ItemDTO(item.getIid(), item.getIName(), item.getQty(), item.getPrice()))
                .toList();
    }

    @Override
    public ItemDTO getItemById(String id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        return new ItemDTO(item.getIid(), item.getIName(), item.getQty(), item.getPrice());
    }

    @Transactional
    @Override
    public void reduceStock(String itemId, int qty) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
        if (item.getQty() < qty) throw new RuntimeException("Insufficient stock");
        item.setQty(item.getQty() - qty);
        itemRepository.save(item);
    }
}
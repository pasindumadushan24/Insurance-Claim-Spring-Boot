package lk.ijse.backend.Controller;

import lk.ijse.backend.DTO.ItemDTO;
import lk.ijse.backend.ServiceLayer.ItemService;
import lk.ijse.backend.Utill.APIResponse;
import lk.ijse.backend.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/Item") // Slash එකක් මුලට එකතු කිරීම වඩාත් සුදුසුයි
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    // ===================== SAVE ITEM =====================
    // ===================== SAVE ITEM =====================
    @PostMapping
    public ResponseEntity<APIResponse<ItemDTO>> saveItem(@RequestBody ItemDTO itemDTO) {
        // 1. කලින් තිබුණු 'ItemDTO saved =' කොටස ඉවත් කරන්න
        itemService.saveItem(itemDTO);

        // 2. සේව් වුණු item එකම නැවත response එකට යවන්න (හෝ null යවන්න)
        return new ResponseEntity<>(
                new APIResponse<>(201, "Item Saved Successfully", itemDTO),
                HttpStatus.CREATED
        );
    }

    // ===================== UPDATE ITEM =====================
    @PutMapping
    public ResponseEntity<APIResponse<String>> updateItem(@RequestBody ItemDTO itemDTO) {
        itemService.updateItem(itemDTO);
        return new ResponseEntity<>(new APIResponse<>(201, "Item Updated Successfully", null), HttpStatus.CREATED);
    }

    // ===================== DELETE ITEM =====================
    @DeleteMapping("/{iid}")
    public ResponseEntity<APIResponse<String>> deleteItem(@PathVariable String iid) {
        itemService.deleteItemById(iid);
        return new ResponseEntity<>(new APIResponse<>(200, "Item Deleted Successfully", null), HttpStatus.OK);
    }

    // ===================== GET ALL ITEMS =====================
    @GetMapping
    public ResponseEntity<APIResponse<List<ItemDTO>>> getAllItems() {
        List<ItemDTO> items = itemService.getAllItems();
        return new ResponseEntity<>(new APIResponse<>(200, "Items retrieved successfully", items), HttpStatus.OK);
    }

    // ===================== GET NEXT ITEM ID =====================
    @GetMapping("/nextId")
    public ResponseEntity<APIResponse<String>> getNextId() {
        // Repository එකේ findNextId() ලියලා තියෙන්න ඕනේ String එකක් return කරන විදියට
        String nextId = itemRepository.findNextId();
        return new ResponseEntity<>(new APIResponse<>(200, "Next ID fetched", nextId), HttpStatus.OK);
    }
}
package lk.ijse.back_end.controller;

import lk.ijse.back_end.dto.HomePolicyDTO;
import lk.ijse.back_end.service.custom.HomePolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home-policy")
@RequiredArgsConstructor
@CrossOrigin
public class HomePolicyController {

    private final HomePolicyService service;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody HomePolicyDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<String> approve(@PathVariable Integer id) {
        service.updateStatus(id, "APPROVED");
        return ResponseEntity.ok("Approved");
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<String> reject(@PathVariable Integer id) {
        service.updateStatus(id, "REJECTED");
        return ResponseEntity.ok("Rejected");
    }

    @PutMapping("/pay/{id}")
    public ResponseEntity<String> pay(@PathVariable Integer id) {
        service.updateStatus(id, "PAID");
        return ResponseEntity.ok("Paid");
    }
}
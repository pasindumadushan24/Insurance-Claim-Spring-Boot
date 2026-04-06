package lk.ijse.back_end.controller;

import lk.ijse.back_end.dto.LifePolicyDTO;
import lk.ijse.back_end.service.custom.LifePolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/life-policy")
@RequiredArgsConstructor
@CrossOrigin
public class LifePolicyController {

    private final LifePolicyService service;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody LifePolicyDTO dto) {
        String policyCode = service.save(dto);
        return ResponseEntity.ok(policyCode);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAllPolicies());
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

    @GetMapping("/next-policy-code")
    public ResponseEntity<String> getNextPolicyCode() {
        Integer lastId = service.getLastPolicyId();
        int nextId = (lastId == null) ? 1 : lastId + 1;
        return ResponseEntity.ok(String.format("POLL%03d", nextId));
    }
}
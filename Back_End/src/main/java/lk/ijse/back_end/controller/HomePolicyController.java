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
    public ResponseEntity<String> save(@RequestBody HomePolicyDTO dto) {
        String policyCode = service.save(dto);
        return ResponseEntity.ok(policyCode); // Return POLH001, POLH002...
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

    @GetMapping("/next-policy-code")
    public ResponseEntity<String> getNextPolicyCode() {
        Integer lastId = service.getLastPolicyId();
        int nextId = (lastId == null) ? 1 : lastId + 1;
        String nextCode = String.format("POLH%03d", nextId);
        return ResponseEntity.ok(nextCode);
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
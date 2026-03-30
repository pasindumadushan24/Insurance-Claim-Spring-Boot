package lk.ijse.back_end.controller;

import lk.ijse.back_end.dto.PolicyRequestDTO;
import lk.ijse.back_end.entity.VehiclePolicy;
import lk.ijse.back_end.service.custom.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policy")
@RequiredArgsConstructor
@CrossOrigin
public class PolicyController {

    private final PolicyService policyService;

    @PostMapping("/save")
    public ResponseEntity<VehiclePolicy> savePolicy(@RequestBody PolicyRequestDTO dto){
        VehiclePolicy savedPolicy = policyService.savePolicy(dto);
        return ResponseEntity.ok(savedPolicy);
    }


    @GetMapping("/all")
    public ResponseEntity<List<VehiclePolicy>> getAllPolicies() {
        List<VehiclePolicy> policies = policyService.getAllPolicies();
        return ResponseEntity.ok(policies);
    }
}
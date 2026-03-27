package lk.ijse.back_end.controller;

import lk.ijse.back_end.dto.APIResponse;
import lk.ijse.back_end.dto.InsuranceDTO;

import lk.ijse.back_end.service.custom.InsuranceViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurance/view")
@CrossOrigin(origins = "http://localhost:63342")
@RequiredArgsConstructor
public class InsuranceViewController {

    private final InsuranceViewService insuranceViewService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<APIResponse> addInsurance(@RequestBody InsuranceDTO dto){
        InsuranceDTO saved = insuranceViewService.save(dto);
        return ResponseEntity.ok(new APIResponse(200, "Policy added successfully", saved));
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<APIResponse> getAllPolicies(){
        List<InsuranceDTO> list = insuranceViewService.getAll();
        return ResponseEntity.ok(new APIResponse(200, "Success", list));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<APIResponse> deletePolicy(@PathVariable Long id){
        insuranceViewService.delete(id);
        return ResponseEntity.ok(new APIResponse(200, "Policy deleted successfully", null));
    }
}
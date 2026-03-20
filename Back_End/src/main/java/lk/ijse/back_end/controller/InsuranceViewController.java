package lk.ijse.back_end.controller;





import lk.ijse.back_end.dto.APIResponse;
import lk.ijse.back_end.entity.Insurance;
import lk.ijse.back_end.service.InsuranceService;
import lk.ijse.back_end.service.InsuranceViewService;
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
    public ResponseEntity<APIResponse> addInsurance(@RequestBody Insurance insurance){
        insuranceViewService.save(insurance);
        return ResponseEntity.ok(new APIResponse(200, "Policy added successfully", null));
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<APIResponse> getAllPolicies(){
        List<Insurance> list = insuranceViewService.getAll();
        return ResponseEntity.ok(new APIResponse(200, "Success", list));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<APIResponse> deletePolicy(@PathVariable Long id){
        insuranceViewService.delete(id);
        return ResponseEntity.ok(new APIResponse(200, "Policy deleted successfully", null));
    }
}

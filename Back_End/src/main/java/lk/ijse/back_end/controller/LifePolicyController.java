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
    public ResponseEntity<?> save(@RequestBody LifePolicyDTO dto){
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }



    @PutMapping("/approve/{id}")
    public void approve(@PathVariable Integer id){
        service.updateStatus(id, "APPROVED");
    }

    @PutMapping("/reject/{id}")
    public void reject(@PathVariable Integer id){
        service.updateStatus(id, "REJECTED");
    }

    @PutMapping("/pay/{id}")
    public void pay(@PathVariable Integer id){
        service.updateStatus(id, "PAID");
    }
}
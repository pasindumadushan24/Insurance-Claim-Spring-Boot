package lk.ijse.back_end.controller;

import lk.ijse.back_end.dto.InsuranceDTO;

import lk.ijse.back_end.service.custom.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurance")
@CrossOrigin
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService service;

    @PostMapping
    public InsuranceDTO addInsurance(@RequestBody InsuranceDTO dto){
        return service.save(dto);
    }

    @GetMapping
    public List<InsuranceDTO> getAll(){
        return service.getAll();
    }

    @PutMapping("/{id}")
    public InsuranceDTO updateInsurance(@PathVariable Long id, @RequestBody InsuranceDTO dto){
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteInsurance(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("/type/{type}")
    public List<InsuranceDTO> getByType(@PathVariable String type){
        return service.getByType(type);
    }
}
package lk.ijse.back_end.controller;


import lk.ijse.back_end.entity.Insurance;
import lk.ijse.back_end.service.InsuranceService;
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
    public Insurance addInsurance(@RequestBody Insurance insurance){
        return service.save(insurance);
    }

    @GetMapping
    public List<Insurance> getAll(){
        return service.getAll();
    }

}
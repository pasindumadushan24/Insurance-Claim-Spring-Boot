package lk.ijse.back_end.controller;


import lk.ijse.back_end.dto.VehicleFullDTO;
import lk.ijse.back_end.entity.VehicleFull;
import lk.ijse.back_end.service.custom.VehicleFullService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-full")
@RequiredArgsConstructor
@CrossOrigin
public class VehicleFullController {

    private final VehicleFullService service;

    @PostMapping("/save")
    public ResponseEntity<VehicleFull> save(@RequestBody VehicleFullDTO dto){
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<VehicleFull>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
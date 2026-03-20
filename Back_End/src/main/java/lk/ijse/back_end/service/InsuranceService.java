package lk.ijse.back_end.service;


import lk.ijse.back_end.entity.Insurance;
import lk.ijse.back_end.repository.InsuranceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class InsuranceService {

    private final InsuranceRepository repo;

    public Insurance save(Insurance insurance){
        return repo.save(insurance);
    }

    public List<Insurance> getAll(){
        return repo.findAll();
    }

}
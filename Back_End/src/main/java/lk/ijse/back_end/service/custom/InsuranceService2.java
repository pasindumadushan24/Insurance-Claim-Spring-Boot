package lk.ijse.back_end.service.custom;

import lk.ijse.back_end.dto.Insurance2DTO;
import lk.ijse.back_end.dto.InquiryDTO;

public interface InsuranceService2 {

    Insurance2DTO saveVehicle(Insurance2DTO dto);

    InquiryDTO saveInquiry(InquiryDTO dto);
}
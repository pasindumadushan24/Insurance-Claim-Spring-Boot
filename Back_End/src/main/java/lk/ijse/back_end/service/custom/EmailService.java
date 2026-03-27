package lk.ijse.back_end.service.custom;

import lk.ijse.back_end.dto.QuoteDTO;

public interface EmailService {
    void sendQuote(QuoteDTO dto);
}

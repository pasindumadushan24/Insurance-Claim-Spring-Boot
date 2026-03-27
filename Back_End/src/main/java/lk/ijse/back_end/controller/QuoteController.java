package lk.ijse.back_end.controller;

import lk.ijse.back_end.dto.QuoteDTO;

import lk.ijse.back_end.service.custom.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class QuoteController {

    private final EmailService emailService;

    @PostMapping("/quote")
    public String sendQuote(@RequestBody QuoteDTO dto){
        emailService.sendQuote(dto);
        return "Email Sent!";
    }
}
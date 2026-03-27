package lk.ijse.back_end.service;

import lk.ijse.back_end.dto.QuoteDTO;

import lk.ijse.back_end.service.custom.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendQuote(QuoteDTO dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("pasindu24hh@gmail.com");
        message.setSubject("New Vehicle Insurance Request");
        message.setText(
                "Name: " + dto.getName() + "\n" +
                        "Mobile: " + dto.getMobile() + "\n" +
                        "Email: " + dto.getEmail()
        );

        mailSender.send(message);
    }
}
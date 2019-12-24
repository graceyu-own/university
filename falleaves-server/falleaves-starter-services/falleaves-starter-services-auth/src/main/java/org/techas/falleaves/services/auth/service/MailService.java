package org.techas.falleaves.services.auth.service;

import org.springframework.web.bind.annotation.RequestMapping;

/// @FeignClient(value = "services-mail")
public interface MailService {

    @RequestMapping("/send")
    void sendMail(String... args);
}

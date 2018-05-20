package com.arllain.backend.services;

import org.springframework.mail.SimpleMailMessage;

import com.arllain.backend.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}

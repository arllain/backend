package com.arllain.backend.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.arllain.backend.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{

	@Value("$(default.sender)")
	private String sender; 
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sn = prepareSimpleMailMessagePedido(obj);
		sendEmail(sn);
	}

	protected SimpleMailMessage prepareSimpleMailMessagePedido(Pedido obj) {
		SimpleMailMessage sn = new SimpleMailMessage();
		sn.setTo(obj.getCliente().getEmail());
		sn.setFrom(sender);
		sn.setSubject("Pedido confirmado! Código: " + obj.getId());
		sn.setSentDate(new Date(System.currentTimeMillis()));
		sn.setText(obj.toString());
		return sn;
	}
}

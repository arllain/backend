package com.arllain.backend.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.arllain.backend.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{

	@Value("$(default.sender)")
	private String sender;
	
	@Autowired
	TemplateEngine templateEngine;
	
	@Autowired
	JavaMailSender javaMailSender;
	
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
	
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);
		return templateEngine.process("email/confirmacaoPedido", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		try {
			MimeMessage mm = prepareMimeMessagePedido(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	}

	protected MimeMessage prepareMimeMessagePedido(Pedido obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido confirmado! Código: " + obj.getId());
		mmh.setText(htmlFromTemplatePedido(obj),true);
		return mimeMessage;
	}
}

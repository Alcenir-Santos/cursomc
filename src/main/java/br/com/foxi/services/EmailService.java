package br.com.foxi.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.foxi.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfimationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfimationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);
	
}

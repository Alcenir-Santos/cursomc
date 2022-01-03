package br.com.foxi.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.foxi.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfimationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}

package com.capstone.utility;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.capstone.model.Cart;

@Component
public class EmailConstructor {

	@Autowired
	private TemplateEngine templateEngine;

	public MimeMessagePreparator constructOrderConfirmationEmail(String userEmail, List<Cart> cartItemsByUserID,
			BigDecimal grandTotal, Locale locale) {

		Context context = new Context();
		context.setVariable("cartItemsByUserID", cartItemsByUserID);
		context.setVariable("grandTotal", grandTotal);

		String text = templateEngine.process("orderConfirmationEmail", context);

		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(userEmail);
				email.setSubject("Order Confirmation ");
				email.setText(text, true);
				email.setFrom(new InternetAddress("smgugwad@gmail.com"));
			}
		};

		return messagePreparator;
	}

}

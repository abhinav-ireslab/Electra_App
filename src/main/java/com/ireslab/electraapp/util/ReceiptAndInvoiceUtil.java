package com.ireslab.electraapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Service
public class ReceiptAndInvoiceUtil {
	@Autowired
    private SpringTemplateEngine templateEngine;
	
	public String companyDetailsForInvoice(com.ireslab.sendx.electra.dto.ReceiptAndInvoiceUtilDto dto) {
		Context context = new Context();
		 context.setVariable("receiptAndInvoiceUtilDto", dto);
		return templateEngine.process("invoice", context);
	}
	

}

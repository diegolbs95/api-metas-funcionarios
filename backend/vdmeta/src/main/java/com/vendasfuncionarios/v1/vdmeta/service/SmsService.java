package com.vendasfuncionarios.v1.vdmeta.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.vendasfuncionarios.v1.vdmeta.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class SmsService {

    @Autowired
    private SaleRepository repository;

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.key}")
    private String twilioKey;

    @Value("${twilio.phone.from}")
    private String twilioPhoneFrom;

    @Value("${twilio.phone.to}")
    private String twilioPhoneTo;

    public void sendSms(Long saleId) {

        var sale = repository.findById(saleId).get();
        var date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();
        var mensagem = "O Vendedor " + sale.getSellerName() + " Foi destaque em " + date
                + " com um total de R$: " + String.format("%.2f", sale.getAmount());

        Twilio.init(twilioSid, twilioKey);

        PhoneNumber to = new PhoneNumber(twilioPhoneTo);
        PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

        Message message = Message.creator(to, from, mensagem).create();

        System.out.println(message.getSid());
    }
}
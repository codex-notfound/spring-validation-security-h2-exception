package com.example.validationsecurityh2exception.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.validationsecurityh2exception.model.Contact;
import com.example.validationsecurityh2exception.repository.ContactRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepo;

    public void saveContactInfo(Contact contact){
        log.info("saving contact info: " + contact.toString());
        contactRepo.save(contact);
    }
}


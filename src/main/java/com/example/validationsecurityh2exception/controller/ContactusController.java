package com.example.validationsecurityh2exception.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.validationsecurityh2exception.model.Contact;
import com.example.validationsecurityh2exception.repository.ContactRepository;
import com.example.validationsecurityh2exception.service.ContactService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactusController {

    @Autowired
    ContactService contactService;

    @Autowired
    ContactRepository contactRepository;
    
    @RequestMapping(value = {"", "/", "/contact"})
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    @PostMapping(value="/save")
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        if(errors.hasErrors()){
            return "contact.html";
        }
        contactService.saveContactInfo(contact);
        return "redirect:/contact";
    }

    @GetMapping(value="/contacts")
    public String getAllContact(Model model){
        List<Contact> allContacts = contactRepository.findAll();
        model.addAttribute("contacts", allContacts);
        return "allcontacts.html";
    }

    @GetMapping(value="/contacts/{id}/edit")
    public String editContactInfo(@PathVariable("id") Integer id, Model model){
        Contact contact = contactRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("contact", contact);
        return "edit_contact.html";
    }

    @PostMapping(value="/contacts/{id}/edit")
    public String editContactInfoSubmit(@PathVariable("id") Integer id, @ModelAttribute("contact") Contact contact){
        contactRepository.save(contact);
        return "redirect:/contacts";
    }

    @PostMapping(value="/contacts/{id}/delete")
    public String deleteContactInfo(@PathVariable("id") Integer id){
        contactRepository.deleteById(id);
        return "redirect:/contacts";
    }
}

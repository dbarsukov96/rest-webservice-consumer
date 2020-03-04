package rest.webservice.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rest.webservice.consumer.domain.Contact;
import rest.webservice.consumer.service.ContactsService;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactsPageController {
    @Autowired
    private ContactsService contactsService;

    @GetMapping
    public String displayAllContacts(Model model) {
        List<Contact> contacts = contactsService.findAll();
        model.addAttribute("contacts", contacts);
        return "contacts";
    }

    @GetMapping("/edit/{id}")
    public String openEditPage(@PathVariable Long id, Model model) {
        // TODO: Exception handling
        Contact contact = contactsService.getContactById(id);
        model.addAttribute("contact", contact);
        return "edit-page";
    }

    @PostMapping("/edit/{id}")
    public String updateContact(Contact contact, Model model) {
        // TODO: Add validation and exception handling
        contactsService.updateContact(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/create")
    public String openCreationPage(Model model) {
        return "creation-page";
    }

    @PostMapping("/create")
    public String createContact(Contact contact, Model model) {
        // TODO: Add validation and exception handling
        contactsService.createContact(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        // TODO: Exception handling
        contactsService.deleteContact(id);
        return "redirect:/contacts";
    }
}

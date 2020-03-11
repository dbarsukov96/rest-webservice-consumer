package rest.webservice.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("{id}/edit")
    public String openEditPage(@PathVariable Long id, Model model) {
        Contact contact = contactsService.getContactById(id);
        model.addAttribute("contact", contact);
        return "edit-page";
    }

    @PostMapping("{id}/edit")
    public String updateContact(Contact contact, Model model) {
        contactsService.updateContact(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/create")
    public String openCreationPage(Model model) {
        return "creation-page";
    }

    @PostMapping("/create")
    public String createContact(Contact contact, Model model) {
        contactsService.createContact(contact);
        return "redirect:/contacts";
    }

    @GetMapping("{id}/delete")
    public String deleteContact(@PathVariable Long id) {
        contactsService.deleteContact(id);
        return "redirect:/contacts";
    }

    @PostMapping("/search")
    public String searchContactsByName(@RequestParam String search, Model model) {
        List<Contact> contacts = contactsService.findAllByName(search);
        model.addAttribute("contacts", contacts);
        return "contacts";
    }
}

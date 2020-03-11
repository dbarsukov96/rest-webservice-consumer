package rest.webservice.consumer.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.webservice.consumer.domain.Contact;
import rest.webservice.consumer.domain.ContactDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactsService {
    @Autowired
    private ContactsExchangeClient contactsExchangeClient;

    public List<Contact> findAll() {
        return contactsExchangeClient.getContacts()
                .stream()
                .map(this::mapToContact)
                .collect(Collectors.toList());
    }

    public Contact getContactById(Long id) {
        return mapToContact(contactsExchangeClient.getContactById(id));
    }

    public List<Contact> findAllByName(String name) {
        return contactsExchangeClient.getContactsByName(name)
                .stream()
                .map(this::mapToContact)
                .collect(Collectors.toList());
    }

    public void createContact(Contact contact) {
        contactsExchangeClient.createContact(mapToContactDto(contact));
    }

    public Contact updateContact(Contact contact) {
        ContactDto contactDto = contactsExchangeClient.updateContact(mapToContactDto(contact));
        Contact updatedContact = mapToContact(contactDto);

        return updatedContact;
    }

    public void deleteContact(Long id) {
        contactsExchangeClient.deleteContact(id);
    }

    private Contact mapToContact(@NonNull ContactDto contactDto) {
        return new Contact(
                contactDto.getId(),
                contactDto.getName(),
                contactDto.getPhoneNumber());
    }

    private ContactDto mapToContactDto(@NonNull Contact contact) {
        return new ContactDto(
                contact.getId(),
                contact.getName(),
                contact.getPhoneNumber());
    }
}

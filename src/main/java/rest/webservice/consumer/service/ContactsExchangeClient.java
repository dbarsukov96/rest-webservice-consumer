package rest.webservice.consumer.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rest.webservice.consumer.domain.ContactDto;
import rest.webservice.consumer.domain.MessageDto;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class ContactsExchangeClient {
    private RestTemplate restTemplate = new RestTemplate();
    private String url = "http://ec2-35-180-41-25.eu-west-3.compute.amazonaws.com:8090/";

    public List<ContactDto> getContacts() {
        String endpoint = url + "/contacts";

        ContactDto[] contactsDto = null;
        try {
            contactsDto = restTemplate.getForObject(new URI(endpoint), ContactDto[].class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return Arrays.asList(contactsDto);
    }

    public ContactDto getContactById(Long id) {
        String endpoint = url + "/contacts/" + id;

        ContactDto contactDto = null;
        try {
            contactDto = restTemplate.getForObject(new URI(endpoint), ContactDto.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return  contactDto;
    }

    public List<ContactDto> getContactsByName(String name) {
        String endpoint = url + "/contacts?name=" + name;

        ContactDto[] contactsDto = null;
        try {
            contactsDto = restTemplate.getForObject(new URI(endpoint), ContactDto[].class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return Arrays.asList(contactsDto);
    }

    public void createContact(ContactDto contactDto) {
        String endpoint = url + "/contacts";

        HttpEntity<ContactDto> request = new HttpEntity<>(contactDto);
        ResponseEntity<?> response = restTemplate.exchange(
                endpoint,
                HttpMethod.POST,
                request,
                ContactDto.class
        );
    }

    public ContactDto updateContact(ContactDto contactDto) {
        String endpoint = url + "/contacts/" + contactDto.getId();

        HttpEntity<ContactDto> request = new HttpEntity<>(contactDto);
        ResponseEntity<ContactDto> response = restTemplate.exchange(
                endpoint,
                HttpMethod.PUT,
                request,
                ContactDto.class
        );

        ContactDto contactDtoFromResponse = response.getBody();

        return contactDtoFromResponse;
    }

    public void deleteContact(Long id) {
        String endpoint = url + "/contacts/" + id;

        HttpEntity<Long> request = new HttpEntity<>(id);
        ResponseEntity<?> response = restTemplate.exchange(
                endpoint,
                HttpMethod.DELETE,
                request,
                ContactDto.class
        );
    }

    public void sendMessage(Long id, String message) {
        String endpoint = url + "/contacts/" + id + "/message";

        MessageDto messageDto = new MessageDto(new Date(), message);
        HttpEntity<MessageDto> request = new HttpEntity<>(messageDto);
        ResponseEntity<?> response = restTemplate.exchange(
                endpoint,
                HttpMethod.POST,
                request,
                MessageDto.class
        );
    }
}

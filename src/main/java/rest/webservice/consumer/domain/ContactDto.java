package rest.webservice.consumer.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ContactDto {
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String phoneNumber;
}

package rest.webservice.consumer.domain;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class MessageDto {
    private Date timestamp;

    @NonNull
    private String text;
}

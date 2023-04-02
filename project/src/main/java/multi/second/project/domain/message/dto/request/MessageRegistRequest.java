package multi.second.project.domain.message.dto.request;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class MessageRegistRequest {

    private String userId;

    private String content;
}

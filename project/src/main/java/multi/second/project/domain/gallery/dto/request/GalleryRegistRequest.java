package multi.second.project.domain.gallery.dto.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GalleryRegistRequest {

    private String userId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

}
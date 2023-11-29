package muhammadarash.restful.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTaskRequest {

    @NotBlank
    @Size(max = 100)
    private String title;

    @Size(max = 100)
    private String description;

    @Size(max = 100)
    private String status;
}

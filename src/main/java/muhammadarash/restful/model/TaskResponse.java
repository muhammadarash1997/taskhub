package muhammadarash.restful.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private String id;

    private String title;

    private String description;

    private String status;
}

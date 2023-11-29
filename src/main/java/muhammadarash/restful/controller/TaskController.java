package muhammadarash.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import muhammadarash.restful.entity.User;
import muhammadarash.restful.model.*;
import muhammadarash.restful.service.TaskService;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping(
            path = "/api/tasks",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TaskResponse> create(User user, @RequestBody CreateTaskRequest request) {
        TaskResponse taskResponse = taskService.create(user, request);
        return WebResponse.<TaskResponse>builder().data(taskResponse).build();
    }

    @GetMapping(
            path = "/api/tasks/{taskId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TaskResponse> get(User user, @PathVariable("taskId") String taskId) {
        TaskResponse taskResponse = taskService.get(user, taskId);
        return WebResponse.<TaskResponse>builder().data(taskResponse).build();
    }

    @PutMapping(
            path = "/api/tasks/{taskId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<TaskResponse> update(User user,
                                               @RequestBody UpdateTaskRequest request,
                                               @PathVariable("taskId") String taskId) {

        request.setId(taskId);

        TaskResponse taskResponse = taskService.update(user, request);
        return WebResponse.<TaskResponse>builder().data(taskResponse).build();
    }

    @DeleteMapping(
            path = "/api/tasks/{taskId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(User user, @PathVariable("taskId") String taskId) {
        taskService.delete(user, taskId);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/tasks",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<TaskResponse>> search(User user,
                                                     @RequestParam(value = "title", required = false) String title,
                                                     @RequestParam(value = "description", required = false) String description,
                                                     @RequestParam(value = "status", required = false) String status) {

        List<TaskResponse> taskResponses = taskService.search(user);
        return WebResponse.<List<TaskResponse>>builder()
                .data(taskResponses)
                .build();
    }
}

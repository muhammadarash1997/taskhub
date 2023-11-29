package muhammadarash.restful.service;

import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import muhammadarash.restful.entity.Task;
import muhammadarash.restful.entity.User;
import muhammadarash.restful.model.TaskResponse;
import muhammadarash.restful.model.CreateTaskRequest;
import muhammadarash.restful.model.UpdateTaskRequest;
import muhammadarash.restful.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TaskResponse create(User user, CreateTaskRequest request) {
        validationService.validate(request);

        Task task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setUser(user);

        taskRepository.save(task);

        return toTaskResponse(task);
    }

    private TaskResponse toTaskResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
    }

    @Transactional(readOnly = true)
    public TaskResponse get(User user, String id) {
        Task task = taskRepository.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        return toTaskResponse(task);
    }

    @Transactional
    public TaskResponse update(User user, UpdateTaskRequest request) {
        validationService.validate(request);

        Task task = taskRepository.findFirstByUserAndId(user, request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        taskRepository.save(task);

        return toTaskResponse(task);
    }

    @Transactional
    public void delete(User user, String taskId) {
        Task task = taskRepository.findFirstByUserAndId(user, taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        taskRepository.delete(task);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> search(User user) {
        Specification<Task> specification = (root, query, builder) -> {
            Predicate userPredicate = builder.equal(root.get("user"), user);
            return userPredicate;
        };

        Sort sort = Sort.by(Sort.Direction.ASC, "title");

        List<Task> tasks = taskRepository.findAll(specification, sort);
        List<TaskResponse> taskResponses = tasks.stream()
                .map(this::toTaskResponse)
                .toList();

        return taskResponses;
    }
}

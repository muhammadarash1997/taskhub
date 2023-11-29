package muhammadarash.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import muhammadarash.restful.entity.Task;
import muhammadarash.restful.entity.User;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, String>, JpaSpecificationExecutor<Task> {

    Optional<Task> findFirstByUserAndId(User user, String id);
}

package com.example.dailytask.Repository;

import com.example.dailytask.Domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t " +
            "FROM Task t " +
            "WHERE t.deleted = false " +
            "  AND (" +
            "    (DATE(t.start) <= DATE(CURRENT_TIMESTAMP) AND DATE(t.end) >= DATE(CURRENT_TIMESTAMP)) " +
            "    OR (DATE(t.start) >= DATE(CURRENT_TIMESTAMP) AND DATE(t.start) <= DATE(CURRENT_TIMESTAMP)) " +
            "    OR (DATE(t.end) >= DATE(CURRENT_TIMESTAMP) AND DATE(t.end) <= DATE(CURRENT_TIMESTAMP)) " +
            "  ) " +
            "ORDER BY t.id DESC")
    List<Task> findTasksFromToday();

    Task findByIdAndDeleted(Long taskId, boolean deleted);

}

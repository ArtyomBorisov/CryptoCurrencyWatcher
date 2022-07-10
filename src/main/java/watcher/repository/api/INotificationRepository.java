package watcher.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import watcher.repository.entity.NotificationEntity;

import java.util.List;

public interface INotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findAllByEnabledTrue();
}

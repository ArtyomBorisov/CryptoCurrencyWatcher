package watcher.repository.api.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import watcher.model.Notification;
import watcher.repository.entity.NotificationEntity;

@Component
public class NotificationEntityToDtoConverter implements Converter<NotificationEntity, Notification> {

    @Override
    public Notification convert(NotificationEntity entity) {
        return new Notification(entity.getUsername(), entity.getSymbol());
    }
}

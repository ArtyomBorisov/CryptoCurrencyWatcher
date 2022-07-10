package watcher.repository.api.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import watcher.model.Notification;
import watcher.repository.entity.NotificationEntity;

@Component
public class NotificationDtoToEntityConverter implements Converter<Notification, NotificationEntity> {

    @Override
    public NotificationEntity convert(Notification dto) {
        NotificationEntity entity = new NotificationEntity();
        entity.setSymbol(dto.getSymbol());
        entity.setUsername(dto.getUsername());

        return entity;
    }
}

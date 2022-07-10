package watcher.service;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import watcher.model.Coin;
import watcher.model.Notification;
import watcher.repository.api.ICoinRepository;
import watcher.repository.api.INotificationRepository;
import watcher.repository.entity.CoinEntity;
import watcher.repository.entity.NotificationEntity;
import watcher.service.api.ICoinService;
import watcher.service.exception.EntityNotFound;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class CoinService implements ICoinService {

    private final ICoinRepository coinRepository;
    private final INotificationRepository notificationRepository;
    private final ConversionService conversionService;

    public CoinService(ICoinRepository coinRepository,
                       INotificationRepository notificationRepository,
                       ConversionService conversionService) {
        this.coinRepository = coinRepository;
        this.notificationRepository = notificationRepository;
        this.conversionService = conversionService;
    }

    @Override
    public List<Coin> get() {
        return this.coinRepository.findAllByOrderById().stream()
                .map(entity -> this.conversionService.convert(entity, Coin.class))
                .collect(Collectors.toList());
    }

    @Override
    public Coin get(String symbol) {
        CoinEntity entity = this.findBySymbol(symbol);

        return this.conversionService.convert(entity, Coin.class);
    }

    @Transactional
    @Override
    public Coin updatePrice(String symbol, Double newPrice) {
        CoinEntity entity = this.findBySymbol(symbol);
        entity.setPriceUsd(newPrice);
        CoinEntity save = this.coinRepository.save(entity);

        return this.conversionService.convert(save, Coin.class);
    }

    @Transactional
    @Override
    public void addNotification(Notification notification) {
        NotificationEntity entity = this.conversionService.convert(notification, NotificationEntity.class);

        entity.setStartPrice(this.get(notification.getSymbol()).getPriceUsd());

        this.notificationRepository.save(entity);
    }

    private CoinEntity findBySymbol(String symbol) {
        return this.coinRepository.findByDataSymbol(symbol).orElseThrow(
                () -> new EntityNotFound("Передан несуществующий код валюты - " + symbol));
    }
}

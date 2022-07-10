package watcher.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import watcher.model.Coin;
import watcher.repository.api.INotificationRepository;
import watcher.repository.entity.NotificationEntity;
import watcher.service.api.ICoinService;
import watcher.service.api.ISchedulerService;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class SchedulerService implements ISchedulerService {

    private final ICoinService coinService;
    private final INotificationRepository notificationRepository;
    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    @Value("${coin_url}")
    private String urlCoin;

    public SchedulerService(ICoinService coinService,
                            INotificationRepository notificationRepository) {
        this.coinService = coinService;
        this.notificationRepository = notificationRepository;
        this.restTemplate = new RestTemplate();
    }

    @Scheduled(fixedRate = 60 * 1000)
    @Async
    @Transactional
    @Override
    public void updatePrice() {
        List<Coin> coins = this.coinService.get();

        for (Coin coin : coins) {
            this.coinService.updatePrice(
                    coin.getSymbol(),
                    this.getPrice(coin.getId()));
            this.logger.info("{}: Обновление цены для монеты {}", LocalDateTime.now(), coin.getSymbol());
        }

        this.notifyUser();
    }

    @Transactional
    @Override
    public void notifyUser() {
        List<NotificationEntity> entities = this.notificationRepository.findAllByEnabledTrue();

        for (NotificationEntity entity : entities) {
            Coin coin = this.coinService.get(entity.getSymbol());

            Double actualPrice = coin.getPriceUsd();
            Double startPrice = entity.getStartPrice();

            double percent = (actualPrice - startPrice) * 100 / startPrice;

            if (Math.abs(percent) >= 1) {
                this.logger.warn( "{} {} {}", entity.getSymbol(), entity.getUsername(), percent);
                entity.setEnabled(false);
                this.notificationRepository.save(entity);
            }
        }
    }

    private Double getPrice(Long id) {
        Coin[] coinInArray = this.restTemplate.getForObject(this.urlCoin + id, Coin[].class);
        return coinInArray[0].getPriceUsd();
    }
}

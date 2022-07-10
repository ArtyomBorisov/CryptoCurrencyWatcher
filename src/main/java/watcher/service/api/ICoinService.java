package watcher.service.api;

import watcher.model.Coin;
import watcher.model.Notification;

import java.util.List;

public interface ICoinService {
    List<Coin> get();
    Coin get(String symbol);
    Coin updatePrice(String symbol, Double newPrice);
    void addNotification(Notification notification);
}

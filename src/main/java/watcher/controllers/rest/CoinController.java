package watcher.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import watcher.model.Coin;
import watcher.model.Notification;
import watcher.service.api.ICoinService;

import java.util.List;

@RestController
@RequestMapping(value = "/coin", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CoinController {

    private final ICoinService coinService;

    public CoinController(ICoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping
    @ResponseBody
    public List<Coin> get() {
        return this.coinService.get();
    }

    @GetMapping(value = "/{symbol}")
    @ResponseBody
    public Coin get(@PathVariable String symbol) {
        return this.coinService.get(symbol);
    }

    @PostMapping(value = "/notify")
    @ResponseStatus(HttpStatus.CREATED)
    public void notifyPrice(@RequestBody Notification notification) {
        this.coinService.addNotification(notification);
    }
}

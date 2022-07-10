package watcher.repository.api.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import watcher.model.Coin;
import watcher.repository.entity.CoinEntity;

@Component
public class CoinEntityToCoinDtoConverter implements Converter<CoinEntity, Coin> {
    @Override
    public Coin convert(CoinEntity entity) {
        Coin dto = new Coin();
        dto.setId(entity.getId());
        dto.setPriceUsd(entity.getPriceUsd());
        dto.setSymbol(entity.getData() == null ? null : entity.getData().getSymbol());

        return dto;
    }
}

package watcher.repository.api.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import watcher.model.Coin;
import watcher.repository.entity.CoinDataEntity;
import watcher.repository.entity.CoinEntity;

@Component
public class CoinDtoToCoinEntityConverter implements Converter<Coin, CoinEntity> {
    @Override
    public CoinEntity convert(Coin dto) {
        CoinDataEntity coinDataEntity = new CoinDataEntity();
        coinDataEntity.setId(dto.getId());
        coinDataEntity.setSymbol(dto.getSymbol());

        CoinEntity entity = new CoinEntity();
        entity.setId(dto.getId());
        entity.setPriceUsd(dto.getPriceUsd());
        entity.setData(coinDataEntity);

        return entity;
    }
}

package watcher.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import watcher.repository.entity.CoinEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICoinRepository extends JpaRepository<CoinEntity, Integer> {
    List<CoinEntity> findAllByOrderById();
    Optional<CoinEntity> findByDataSymbol(String symbol);
}

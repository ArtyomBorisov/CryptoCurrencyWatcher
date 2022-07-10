package watcher.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "coin", schema = "app")
public class CoinEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "price_usd")
    private double priceUsd;

    @OneToOne
    @PrimaryKeyJoinColumn
    private CoinDataEntity data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public CoinDataEntity getData() {
        return data;
    }

    public void setData(CoinDataEntity data) {
        this.data = data;
    }
}

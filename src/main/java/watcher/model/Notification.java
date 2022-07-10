package watcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Notification {
    @JsonProperty("username")
    private String username;

    @JsonProperty("symbol")
    private String symbol;

    public Notification(String username, String symbol) {
        this.username = username;
        this.symbol = symbol;
    }

    public String getUsername() {
        return username;
    }

    public String getSymbol() {
        return symbol;
    }
}

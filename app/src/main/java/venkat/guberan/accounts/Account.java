package venkat.guberan.accounts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Venkat on 12/6/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    @JsonProperty("desc")
    private String description;
    @JsonProperty("balance")
    private String balance;
    @JsonProperty("currency")
    private String currency;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

package venkat.guberan.accounts.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Venkat on 12/6/15.
 */
@Data
@NoArgsConstructor
public class Amount {
    private double amount;
    private String currency;

    public Amount(Amount amount) {
        this.amount = amount.getAmount();
        this.currency = amount.getCurrency();
    }
}

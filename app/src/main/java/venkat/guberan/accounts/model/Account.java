package venkat.guberan.accounts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import lombok.Data;

/**
 * Created by Venkat on 12/6/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Account {
    private String description;
    private Amount balance;
    /*
    public static void main(String args[]) throws Exception {
        ArrayList<Account> accounts = new ArrayList<>();
        for(int i = 0; i < 3;i++) {
            Account account = new Account();
            account.setDescription("test");
            Amount amount = new Amount();
            amount.setAmount(234.9);
            amount.setCurrency("USD");
            account.setBalance(amount);
            accounts.add(account);
        }
        for(int i = 0; i < 3;i++) {
            Account account = new Account();
            account.setDescription("test");
            Amount amount = new Amount();
            amount.setAmount(23492);
            amount.setCurrency("INR");
            account.setBalance(amount);
            accounts.add(account);
        }

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(accounts);
        System.out.println(str);
    }
    */
}

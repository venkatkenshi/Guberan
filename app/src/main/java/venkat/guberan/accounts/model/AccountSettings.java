package venkat.guberan.accounts.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Venkat on 12/6/15.
 */
@Data
@NoArgsConstructor
public class AccountSettings {
    private String accountName;
    private String username;
    private String password;
}

package venkat.guberan.accounts;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Venkat on 12/6/15.
 */
public class AccountsService {

    //accounts
    public static String retrieveAll() {
        String jsonResponse = "[{\"id\":\"2\",\"desc\":\"Test2\",\"balance\":\"20495\",\"currency\":\"USD\"},{\"id\":\"1\",\"desc\":\"Test1\",\"balance\":\"2045\",\"currency\":\"INR\"},{\"id\":\"3\",\"desc\":\"Test3\",\"balance\":\"290495\",\"currency\":\"USD\"}]";
        return jsonResponse;
    }

    //accounts/{accountid}
    public static String retrieve(String accountId) {
        return "Hello world";
    }

    //accounts - POST
    public static String add(String username, String password, String url) {
        return "Hello world";
    }

    //accounts - delete
    public static String remove(String accountId) {
        return "Hello world";
    }
}

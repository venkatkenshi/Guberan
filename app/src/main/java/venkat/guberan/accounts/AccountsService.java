package venkat.guberan.accounts;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import venkat.guberan.accounts.model.AccountSettings;
import venkat.guberan.accounts.model.Constants;

/**
 * Created by Venkat on 12/6/15.
 */
public class AccountsService extends IntentService {

    private static final String ACCOUNT_PROFILES_NAME = "Profiles";
    private static final String ACCOUNT_IDS = "Ids";


    public AccountsService() {
        super("AccountsService");
    }

    //accounts
    public static String retrieveAll() {
        String jsonResponse = "[{\"description\":\"test\",\"balance\":{\"amount\":234.9,\"currency\":\"USD\"}},{\"description\":\"test\",\"balance\":{\"amount\":234.9,\"currency\":\"USD\"}},{\"description\":\"test\",\"balance\":{\"amount\":234.9,\"currency\":\"USD\"}},{\"description\":\"test\",\"balance\":{\"amount\":23492.0,\"currency\":\"INR\"}},{\"description\":\"test\",\"balance\":{\"amount\":23492.0,\"currency\":\"INR\"}},{\"description\":\"test\",\"balance\":{\"amount\":23492.0,\"currency\":\"INR\"}}]";
        return jsonResponse;
    }

    //accounts/{accountid}
    public static String retrieve(String accountId) {
        return "Hello world";
    }

    private int createId(String accountName, String username) {
        SharedPreferences ids = getSharedPreferences(ACCOUNT_IDS, 0);
        String key = accountName + ":" + username;
        if(ids.contains(key)) {
            return ids.getInt(key, 0);
        }
        int lastId = ids.getInt("last", 0);
        int newId = lastId + 1;
        SharedPreferences.Editor editor = ids.edit();
        editor.putInt("last", newId);
        editor.putInt(key, newId);
        editor.commit();
        return newId;
    }

    //accounts - POST
    public String add(String accountName, String username, String password) {
        SharedPreferences profiles = getSharedPreferences(ACCOUNT_PROFILES_NAME, 0);
        // generate an id for this account.
        int accountId = createId(accountName, username);
        if(profiles.contains(String.valueOf(accountId))) {
            // nothing to do here. Return a message saying account already added.
            return accountName + " already exists.";
        }
        SharedPreferences.Editor editor = profiles.edit();
        // create a json representation of accountName, username, password
        AccountSettings accountSettings = new AccountSettings();
        accountSettings.setAccountName(accountName);
        accountSettings.setUsername(username);
        accountSettings.setPassword(password);
        ObjectMapper mapper = new ObjectMapper();
        String str;
        try {
            str = mapper.writeValueAsString(accountSettings);
        }
        catch (Exception e) {
            Log.d("guberan", e.getLocalizedMessage());
            return "Unable to store account settings for " + accountName + ". Please try again later.";
        }
        editor.putString(String.valueOf(accountId), str);
        editor.commit();
        return "Account " + accountName + " added successfully!!!";
    }

    //accounts - delete
    public static String remove(String accountId) {
        return "Hello world";
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getStringExtra("action");
        Log.d("guberan", "Actionto execute : " + action);
        if(action.equals(Constants.ACCOUNTS_SERVICE_ADD_ACTION)) {
            String accountName = intent.getStringExtra(Constants.PARAM_ACCOUNT_NAME);
            String userName = intent.getStringExtra(Constants.PARAM_USERNAME);
            String password = intent.getStringExtra(Constants.PARAM_PASSWORD);
            String result = add(accountName, userName, password);
            final ResultReceiver receiver = intent.getParcelableExtra("receiver");
            Bundle bundle = new Bundle();
            bundle.putString("result", result);
            bundle.putInt("type", Constants.RESPONSE_TYPE_ADD);
            receiver.send(Constants.RESPONSE_STATUS_SUCCESS, bundle);
        } else if(action.equals(Constants.ACCOUNTS_SERVICE_RETRIEVE_ALL_ACTION)) {
            String result = retrieveAll();
            final ResultReceiver receiver = intent.getParcelableExtra("receiver");
            Bundle bundle = new Bundle();
            bundle.putString("result", result);
            bundle.putInt("type", Constants.RESPONSE_TYPE_RETRIEVE_ALL);
            receiver.send(Constants.RESPONSE_STATUS_SUCCESS, bundle);
        }
    }
}

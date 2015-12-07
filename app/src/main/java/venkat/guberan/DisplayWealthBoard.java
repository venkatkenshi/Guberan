package venkat.guberan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import venkat.guberan.accounts.AccountServiceReceiver;
import venkat.guberan.accounts.TotalAdapter;
import venkat.guberan.accounts.model.Account;
import venkat.guberan.accounts.AccountAdapter;
import venkat.guberan.accounts.AccountsService;
import venkat.guberan.accounts.model.Amount;
import venkat.guberan.accounts.model.Constants;

public class DisplayWealthBoard extends Activity implements AccountServiceReceiver.Receiver{

    private AccountAdapter accountAdapter;
    private TotalAdapter totalAdapter;
    private ObjectMapper mapper;
    private AccountServiceReceiver resultReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wealth_board);
        // list of accounts
        ListView accounts = (ListView)findViewById(R.id.accountList);
        accountAdapter = new AccountAdapter(this, R.layout.list_item_account, new ArrayList<Account>());
        accounts.setAdapter(accountAdapter);
        // list of totals
        ListView totals = (ListView)findViewById(R.id.totalList);
        totalAdapter = new TotalAdapter(this, R.layout.list_item_total, new ArrayList<Amount>());
        totals.setAdapter(totalAdapter);
        // json parser
        mapper = new ObjectMapper();
        // hook up result receiver
        resultReceiver = new AccountServiceReceiver(new Handler());
        resultReceiver.setMReceiver(this);
        // start the account service
        Log.d("guberan", "onCreate() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        // fire an intent here
        final Intent intent = new Intent(Intent.ACTION_SEND, null, this, AccountsService.class);
        intent.putExtra("receiver", resultReceiver);
        intent.putExtra("action", Constants.ACCOUNTS_SERVICE_RETRIEVE_ALL_ACTION);
        startService(intent);
        Log.d("guberan", "onStart() called");
    }

    private ArrayList<Amount> calculateTotals(List<Account> accounts) {
        HashMap<String, Amount> totals = new HashMap<>();
        for(Account account : accounts) {
            Amount totalAmount = null;
            if(totals.containsKey(account.getBalance().getCurrency())) {
                totalAmount = totals.get(account.getBalance().getCurrency());
            }
            else {
                totalAmount = new Amount(account.getBalance());
            }
            totalAmount.setAmount(totalAmount.getAmount() + account.getBalance().getAmount());
            totals.put(account.getBalance().getCurrency(), totalAmount);
        }
        return new ArrayList<>(totals.values());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("guberan", "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("guberan", "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("guberan", "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("guberan", "onDestroy() called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_wealth_board, menu);
        return true;
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case Constants.RESPONSE_STATUS_SUCCESS:
                int type = resultData.getInt("type");
                switch (type) {
                    case Constants.RESPONSE_TYPE_RETRIEVE_ALL:
                        try {
                            String accountsJson = resultData.getString("result");
                            ArrayList<Account> accounts = mapper.readValue(accountsJson, new TypeReference<ArrayList<Account>>() {
                            });
                            accountAdapter.setAccounts(accounts);
                            accountAdapter.notifyDataSetChanged();
                            // filter the totals
                            ArrayList<Amount> totals = calculateTotals(accounts);
                            totalAdapter.setAmounts(totals);
                            totalAdapter.notifyDataSetChanged();
                        }
                        catch (Exception e) {
                            Log.d("guberan",e.getLocalizedMessage());

                        }
                        break;
                }
        }
    }
}

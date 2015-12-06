package venkat.guberan;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import venkat.guberan.accounts.Account;
import venkat.guberan.accounts.AccountAdapter;
import venkat.guberan.accounts.AccountsService;

public class DisplayWealthBoard extends Activity {

    private AccountAdapter accountAdapter;
    private ObjectMapper mapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wealth_board);
        ListView accounts = (ListView)findViewById(R.id.accountList);
        accountAdapter = new AccountAdapter(this, R.layout.content_display_wealth_board, new ArrayList<Account>());
        accounts.setAdapter(accountAdapter);
        mapper = new ObjectMapper();
        Log.d("guberan", "onCreate() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("guberan", "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        String accountsJson = AccountsService.retrieveAll();
        try {
            ArrayList<Account> accounts = mapper.readValue(accountsJson, new TypeReference<ArrayList<Account>>() {
            });
            accountAdapter.setAccounts(accounts);
            accountAdapter.notifyDataSetChanged();
        }
        catch (Exception e) {
            Log.d("guberan",e.getLocalizedMessage());

        }
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
}

package venkat.guberan.accounts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import venkat.guberan.R;
import venkat.guberan.accounts.model.Account;

/**
 * Created by Venkat on 12/6/15.
 */
public class AccountAdapter extends ArrayAdapter {

    private ArrayList<Account> accounts;

    public AccountAdapter(Context context, int resource, ArrayList<Account> objects) {
        super(context, resource, objects);
        this.accounts = objects;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts.clear();
        this.accounts.addAll(accounts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item_account, null);
        }
        Account account = accounts.get(position);
        if (account != null) {
            TextView desc = (TextView) v.findViewById(R.id.accountDescription);
            TextView balance = (TextView) v.findViewById(R.id.accountBalance);
            TextView currency = (TextView) v.findViewById(R.id.accountCurrency);
            if (desc != null) {
                desc.setText(account.getDescription());
            }
            if(balance != null){
                balance.setText(String.valueOf(account.getBalance().getAmount()));
            }
            if(currency != null) {
                currency.setText(account.getBalance().getCurrency());
            }
        }
        return v;
    }
}

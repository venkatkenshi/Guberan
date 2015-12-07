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
import venkat.guberan.accounts.model.Amount;

/**
 * Created by Venkat on 12/6/15.
 */
public class TotalAdapter extends ArrayAdapter {
    private ArrayList<Amount> amounts;

    public TotalAdapter(Context context, int resource, ArrayList<Amount> objects) {
        super(context, resource, objects);
        this.amounts = objects;
    }

    public void setAmounts(ArrayList<Amount> amounts) {
        this.amounts.clear();
        this.amounts.addAll(amounts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item_total, null);
        }
        Amount amount = amounts.get(position);
        if (amount != null) {
            TextView balance = (TextView) v.findViewById(R.id.totalBalance);
            TextView currency = (TextView) v.findViewById(R.id.totalCurrency);
            if(balance != null){
                balance.setText(String.valueOf(amount.getAmount()));
            }
            if(currency != null) {
                currency.setText(amount.getCurrency());
            }
        }
        return v;
    }
}

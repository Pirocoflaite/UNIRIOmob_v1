package unirio.uniriomob.other;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import unirio.uniriomob.R;

/**
 * Created by Casa on 17/11/2016.
 */

public class RestaurantDishCustomAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public RestaurantDishCustomAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        // return list.get(pos).getId();
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.fragment_restaurant_list_item, null);

            TextView listItemText = (TextView) row.findViewById(R.id.txt_dish_name);
            listItemText.setText(list.get(position));

            //Handle buttons and add onClickListeners
            final Button btnLove = (Button) row.findViewById(R.id.btn_love_dish);

            btnLove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //do something
                    btnLove.setBackground(v.getResources().getDrawable(R.drawable.ic_heart_full));
                    Toast.makeText(context, "Gostou do prato? Avisaremos para o pessoal do R.U.!", Toast.LENGTH_LONG).show();
                    //list.remove(position); //or some other task
                    notifyDataSetChanged();
                }
            });

        }

        return row;

    }

}
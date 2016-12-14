package unirio.uniriomob.other;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import unirio.uniriomob.R;

/**
 * Created by Casa on 17/11/2016.
 */

public class BusPlaceCustomAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public BusPlaceCustomAdapter(ArrayList<String> list, Context context) {
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
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.fragment_bus_list_item, null);

            TextView listItemText = (TextView) row.findViewById(R.id.txt_bus_place_title);
            listItemText.setText(list.get(position));

            //Handle buttons and add onClickListeners
            final Button btnViewPlace = (Button) row.findViewById(R.id.btn_view_place);

            btnViewPlace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //do something
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.br/maps?source=tldsi&hl=pt-BR"));
                    context.startActivity(browserIntent);

                    //list.remove(position); //or some other task
                    notifyDataSetChanged();
                }
            });

        }

        return row;

    }

}
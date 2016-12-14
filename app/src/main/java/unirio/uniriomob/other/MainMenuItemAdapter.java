package unirio.uniriomob.other;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import unirio.uniriomob.R;

public class MainMenuItemAdapter extends ArrayAdapter {

    private Context context;

    public MainMenuItemAdapter(Context context) {

        super(context, 0);
        this.context = context;

    }

    public int getCount() {
        return mMenuItensIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.nav_home, parent, false);

            TextView textViewIcon = (TextView) row.findViewById(R.id.mmenu_item_icon);
            TextView textViewDescription = (TextView) row.findViewById(R.id.mmenu_item_description);

            textViewIcon.setTypeface(FontManager.getTypeFace(context, FontManager.FONTAWESOME));

            textViewIcon.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            textViewDescription.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

            if (position > 1) {
                textViewIcon.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                textViewDescription.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            } else {
                textViewIcon.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                textViewDescription.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            }

            textViewIcon.setText(context.getString(mMenuItensIds[position][0]));
            textViewDescription.setText(context.getString(mMenuItensIds[position][1]));

        }

        return row;

    }

    private int[][] mMenuItensIds = {
            {R.string.ic_document_code, R.string.nav_document},
            {R.string.ic_syllabus_code, R.string.nav_syllabus},
            {R.string.ic_secretariat_code, R.string.nav_secretariat},
            {R.string.ic_restaurant_code, R.string.nav_restaurant},
            {R.string.ic_bus_code, R.string.nav_bus},
            {R.string.ic_news_code, R.string.nav_news},
    };


}

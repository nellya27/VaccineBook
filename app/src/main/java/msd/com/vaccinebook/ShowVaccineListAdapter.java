package msd.com.vaccinebook;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


//public class ShowVaccineListAdapter extends ArrayAdapter<String>
public class ShowVaccineListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private ArrayList<String> m_allVaccineData;
    private TextView vaccineData;
    private int index = 0;
    private int indexOfTextView ;
    private String currentListViewString;


    public ShowVaccineListAdapter(Context context, int resource, ArrayList<String> allVaccineData) {

        super(context, resource, allVaccineData);
        this.context = context;
        this.m_allVaccineData = allVaccineData;
    }

    static class ViewHolder {
        public TextView currTxt;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = convertView;
        String currentTextOfTheView;
        ViewHolder holder = new ViewHolder();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (rowView == null)
        {
            rowView = inflater.inflate(R.layout.vaccine_layout, parent, false);
            holder.currTxt = (TextView) rowView.findViewById(R.id.vaccineListTxt);
            rowView.setTag(holder);
        }
        //  vaccineData=(TextView)rowView.findViewById(R.id.vaccineListTxt);
        holder = (ViewHolder) rowView.getTag();

        currentTextOfTheView = m_allVaccineData.get(position);
        // holder.currTxt.setText(m_allVaccineData.get(position).toString());
        Log.i("ListTag", "position id is : " + position);
        holder.currTxt.setText(currentTextOfTheView.toString());
        return rowView;

    }

}


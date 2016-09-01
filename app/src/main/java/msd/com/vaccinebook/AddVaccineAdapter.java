package msd.com.vaccinebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class AddVaccineAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] vaccineTypes;


    public AddVaccineAdapter(Context context, String[] vaccineTypes)
    {
        super(context,R.layout.vaccine_choose_layout,vaccineTypes);
        this.context = context;
        this.vaccineTypes=vaccineTypes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.vaccine_choose_layout, parent, false);
        TextView vaccineType=(TextView)rowView.findViewById(R.id.TypeVaccineTxt);
        vaccineType.setText(vaccineTypes[position]);
        vaccineType.setTag(vaccineTypes[position]);

        return rowView;
    }
}

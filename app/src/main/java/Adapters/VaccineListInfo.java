package Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import msd.com.vaccinebook.R;

/**
 * Created by nelia_000 on 30/08/2016.
 */
public class VaccineListInfo extends ArrayAdapter<String>
{
    private final Activity m_context;
    private final String[] m_vaccineDetails;
    private final Integer[] m_imag;



  public VaccineListInfo(Activity context,String[] vaccineList,Integer[]img)
  {
      super(context, R.layout.listvaccineitem,vaccineList);
      this.m_context=context;
      this.m_vaccineDetails=vaccineList;
      this.m_imag=img;

  }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = m_context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.listitemlayout, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(m_vaccineDetails[position]);


        imageView.setImageResource(m_imag[position]);
        return rowView;
    }
}

package Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import msd.com.vaccinebook.R;


public class UserList extends ArrayAdapter<String> {

    private final Activity context;
    private final String [] userDetails;
    private final Integer[] imageId;
    private final String[] userData;



    public UserList(Activity context, String [] userDetails, Integer[] listOfIcons, String[] userData) {
        super(context, R.layout.listitemlayout,userDetails);
        this.context=context;
        this.userDetails=userDetails;
        this.userData=userData;
        this.imageId=listOfIcons;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.listitemlayout, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        TextView txtTitle2=(TextView)rowView.findViewById(R.id.txt2);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(userDetails[position]);
        txtTitle2.setText(userData[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}

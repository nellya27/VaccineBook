package msd.com.vaccinebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class AddExpandableVaccineListAdapter extends BaseExpandableListAdapter
{
    private final SparseArray<Group> groups;
    public LayoutInflater inflater;
    public Activity activity;
    private Bundle extras;
   // Context context;
    String patID;


    public AddExpandableVaccineListAdapter(Activity activity, SparseArray<Group> groups)
    {
        this.activity = activity;
        this.groups = groups;
        inflater = activity.getLayoutInflater();
      //  patID= activity.getIntent().getExtras().getString("patIDValue");
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).childrenItems.get(childPosition);
    }

    public String getPatID()
    {
        return patID;
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String children = (String) getChild(groupPosition, childPosition);
        TextView text = null;

        extras=new Bundle();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.add_vaccine_chooser_layout, null);
        }
        text = (TextView) convertView.findViewById(R.id.addVaccineInflateTxt);
        text.setText(children);
       convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
              if(children!=null)
              {


                  Log.i("ChildViewTag", "patient id in adapter is is : " + patID);
                  Log.i("ChildViewTag", "child text in adapter is : " + children);
                  Intent vaccineChooseClickIntent = new Intent(activity, ChooseVaccineToAddFromExpandableAdapterActivity.class);
                  extras.putString("choosedVaccine", children);
                  extras.putString("userID",UserLogin.m_userLoginId);
                  vaccineChooseClickIntent.putExtras(extras);
                  vaccineChooseClickIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                  activity.startActivity(vaccineChooseClickIntent);
                //  activity.finish();
              }

            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return groups.get(groupPosition).childrenItems.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.add_vaccine_heading_layout, null);
        }
        Group group = (Group) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(group.string);
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
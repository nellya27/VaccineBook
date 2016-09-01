package msd.com.vaccinebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapters.ExpendableListAdapter;

public class VaccineInfo extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_info);


        expListView = (ExpandableListView) findViewById(R.id.expandableListView);
        prepareListData();
        listAdapter = new ExpendableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });


        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }

        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("סימני מחלה");
        listDataHeader.add("גורם מחלה");
        listDataHeader.add("דרכי העברת מחלה");
        listDataHeader.add("אבחון המחלה");

        // Adding child data
        List<String> simptoms = new ArrayList<String>();
        simptoms.add("חולשה");
        simptoms.add("עייפות");
        simptoms.add("חום");
        simptoms.add("הקאה");
        simptoms.add("הופעת צבע צהוב בעור");


        List<String> reason = new ArrayList<String>();
        reason.add("נגיף דלקת B");


        List<String> transfer = new ArrayList<String>();
        transfer.add("אדם לאדם");
        transfer.add("מגע עם דם מזוהם");
        transfer.add("מגע מיני");
        transfer.add("מאם נשאית ליילוד");

        List<String> observation = new ArrayList<String>();
        observation.add("קיום יחסי מין עם אדם נושא נגיף");
        observation.add("אנשים החולים במחלת מין");
        observation.add("אנשים החולקים מזרקים");
        observation.add("אנשים החיים עם אדם בעל דלקת נגיפית");

        listDataChild.put(listDataHeader.get(0), simptoms); // Header, Child data
        listDataChild.put(listDataHeader.get(1), reason);
        listDataChild.put(listDataHeader.get(2), transfer);
        listDataChild.put(listDataHeader.get(3), observation);
    }
}





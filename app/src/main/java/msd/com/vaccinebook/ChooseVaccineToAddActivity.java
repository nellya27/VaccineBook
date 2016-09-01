package msd.com.vaccinebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ChooseVaccineToAddActivity extends Activity {
    //public class ChooseVaccineToAddActivity extends ListActivity {
    private String userId;
    private Bundle extras;
    private ListView chooseVaccineList;
    //  Intent vaccineClickIntent;
    String [] vaccineTypeArray=new String[]{"פפילומה","אדמת","חזרת","שפעת","דלקת כבד אלף"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_vaccine_to_add);
        userId=getIntent().getExtras().getString("patIDValue");
        extras=new Bundle();
        findViewById();
        chooseVaccineList.setAdapter(new AddVaccineAdapter(this,vaccineTypeArray));
        //setListAdapter(new AddVaccineAdapter(this,vaccineTypeArray));
        chooseVaccineList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String vaccineType =  (String)chooseVaccineList.getAdapter().getItem(position);
                Intent vaccineClickIntent=new Intent(ChooseVaccineToAddActivity.this,AddVaccineActivity.class);
                if(vaccineType.equals("פפילומה"))
                {
                    extras.putString("patientID",userId);
                    extras.putString("vaccineKind","papiloma");
                    vaccineClickIntent.putExtras(extras);
                    startActivity(vaccineClickIntent);
                    //Toast.makeText(this, vaccineType + " selected", Toast.LENGTH_LONG).show();
                }
                if(vaccineType.equals("שפעת"))
                {
                    extras.putString("patientID",userId);
                    extras.putString("vaccineKind","shapaat");
                    vaccineClickIntent.putExtras(extras);
                    startActivity(vaccineClickIntent);
                    // Toast.makeText(this, vaccineType + " selected", Toast.LENGTH_LONG).show();
                }
                if(vaccineType.equals("חזרת"))
                {
                    extras.putString("patientID",userId);
                    extras.putString("vaccineKind","hazeret");
                    vaccineClickIntent.putExtras(extras);
                    startActivity(vaccineClickIntent);
                    // Toast.makeText(this, vaccineType + " selected", Toast.LENGTH_LONG).show();
                }
                if(vaccineType.equals("אדמת"))
                {
                    extras.putString("patientID",userId);
                    extras.putString("vaccineKind","ademet");
                    vaccineClickIntent.putExtras(extras);
                    startActivity(vaccineClickIntent);
                    // Toast.makeText(this, vaccineType + " selected", Toast.LENGTH_LONG).show();
                }
                if(vaccineType.equals("דלקת כבד אלף"))
                {
                    extras.putString("patientID",userId);
                    extras.putString("vaccineKind","daleket kaved alef");
                    vaccineClickIntent.putExtras(extras);
                    startActivity(vaccineClickIntent);
                    //Toast.makeText(this, vaccineType + " selected", Toast.LENGTH_LONG).show();
                }
            }

        });
    }



    public void findViewById()
    {
        chooseVaccineList=(ListView)findViewById(R.id.chooseVaccineToAddList);
    }


/*
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
       // String vaccineType =  (String)getListAdapter().getItem(position);
         String vaccineType =  (String)chooseVaccineList.getAdapter().getItem(position);
        Intent vaccineClickIntent=new Intent(this,AddVaccineActivity.class);
        if(vaccineType.equals("פפילומה"))
        {
            extras.putString("patientID",userId);
            extras.putString("vaccineKind","papiloma");
            vaccineClickIntent.putExtras(extras);
            startActivity(vaccineClickIntent);
            //Toast.makeText(this, vaccineType + " selected", Toast.LENGTH_LONG).show();
        }
        if(vaccineType.equals("שפעת"))
        {
            extras.putString("patientID",userId);
            extras.putString("vaccineKind","shapaat");
            vaccineClickIntent.putExtras(extras);
            startActivity(vaccineClickIntent);
           // Toast.makeText(this, vaccineType + " selected", Toast.LENGTH_LONG).show();
        }
        if(vaccineType.equals("חזרת"))
        {
            extras.putString("patientID",userId);
            extras.putString("vaccineKind","hazeret");
            vaccineClickIntent.putExtras(extras);
            startActivity(vaccineClickIntent);
           // Toast.makeText(this, vaccineType + " selected", Toast.LENGTH_LONG).show();
        }
        if(vaccineType.equals("אדמת"))
        {
            extras.putString("patientID",userId);
            extras.putString("vaccineKind","ademet");
            vaccineClickIntent.putExtras(extras);
            startActivity(vaccineClickIntent);
           // Toast.makeText(this, vaccineType + " selected", Toast.LENGTH_LONG).show();
        }
        if(vaccineType.equals("דלקת כבד אלף"))
        {
            extras.putString("patientID",userId);
            extras.putString("vaccineKind","daleket kaved alef");
            vaccineClickIntent.putExtras(extras);
            startActivity(vaccineClickIntent);
            //Toast.makeText(this, vaccineType + " selected", Toast.LENGTH_LONG).show();
        }
    }
*/
}

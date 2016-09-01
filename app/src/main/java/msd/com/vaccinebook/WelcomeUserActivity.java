package msd.com.vaccinebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeUserActivity extends AppCompatActivity {

    private Button listVaccinsBtn;
    private Button addVaccinsBtn;
    private Button showAdultPlacesMapBtn;
    private String m_patientID;
    private TextView nameOfUser;
    private String m_patientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_options);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewsById();
        //m_patientName=getIntent().getExtras().getString("nameOfUser");
        nameOfUser.setText("שלום "+m_patientName);
        listVaccinsBtn.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v)
                      {
              m_patientID=getIntent().getExtras().getString("ID_of_patient");
              patientsListVaccinesMenu();
          }
      }
        );

        addVaccinsBtn.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v)
                                              {
              m_patientID=getIntent().getExtras().getString("ID_of_patient");
              addNewPatientVaccine();
          }
      }
        );

        showAdultPlacesMapBtn.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View view) {
                 {
                     showAdultVaccinePlacesMap();
                 }
             }
         }
        );
    }

    private void findViewsById()
    {
        listVaccinsBtn=(Button)findViewById(R.id.listVaccinsBtn);
        addVaccinsBtn=(Button)findViewById(R.id.addVaccinBtn);
        showAdultPlacesMapBtn=(Button)findViewById(R.id.showAdultPlacesMapBtn);
        nameOfUser=(TextView)findViewById(R.id.userNameTextView);
        nameOfUser.setText("שלום "+m_patientName);
    }

    private void patientsListVaccinesMenu() {

        Intent viewVaccinsIntent =new Intent(this,ShowPatientVaccinesListActivity.class);
        viewVaccinsIntent.putExtra("patIDValue",m_patientID);
        startActivity(viewVaccinsIntent);
    }

    private void addNewPatientVaccine()
    {
        Intent AddVaccinIntent =new Intent(this,ChooseVaccineToAddFromExpandableAdapterActivity.class);
        AddVaccinIntent.putExtra("patIDValue",m_patientID);
        startActivity(AddVaccinIntent);
    }

    private void showAdultVaccinePlacesMap()
    {
        Intent mapsIntent = new Intent(this,MapsActivity.class);
        startActivity(mapsIntent);
    }
}

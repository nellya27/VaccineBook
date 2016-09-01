package msd.com.vaccinebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mMedButton;
    Button mUserButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        mMedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartDoctorLogMenu();
            }
        });
        mUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartUserLogMenu();
                // goToPatientVaccineList();
            }
        });
    }

    private void findViewsById()
    {
        mMedButton=(Button)findViewById(R.id.medStaff);
        mUserButton=(Button)findViewById(R.id.generalUser);
    }

    private void StartUserLogMenu() {

        Intent intent =new Intent(this,UserLogin.class);
        startActivity(intent);
    }

    private void StartDoctorLogMenu() {

        Intent intent2 =new Intent(this,DoctorLogin.class);
        startActivity(intent2);
    }

}
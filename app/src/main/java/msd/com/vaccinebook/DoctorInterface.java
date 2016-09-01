package msd.com.vaccinebook;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import Adapters.UserList;

public class DoctorInterface extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Doctor newDoctor=new Doctor();
    private TextView m_DoctorName;
    private ListView m_DoctorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_interface);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ברוך הבא לאיזור האישי           ");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FindViewById();
        SetData();
        m_DoctorName.setText(newDoctor.getName()+" "+newDoctor.getLastName());
        SetDoctorList();

    }

    private void FindViewById()
    {
        m_DoctorName=(TextView)findViewById(R.id.doctorName);
    }

    private void SetData()
    {
        Intent newIntent=getIntent();
        newDoctor.setName(newIntent.getStringExtra("name"));
        newDoctor.setLastName(newIntent.getStringExtra("lastname"));
        newDoctor.setDoctorId(newIntent.getStringExtra("id"));
        newDoctor.setDoctorAge(newIntent.getStringExtra("age"));
        newDoctor.setDoctorAdress(newIntent.getStringExtra("adress"));
        newDoctor.setDoctorSpecility(newIntent.getStringExtra("speciality"));
        newDoctor.setWorkPlace(newIntent.getStringExtra("workplace"));
    }
    private String[] GetDoctorArray()
    {
        String [] doctorArray={newDoctor.getDoctorId(),newDoctor.getDoctorAdress(),newDoctor.getWorkPlace(),newDoctor.getDoctorSpecility(),newDoctor.getDoctorAge()};
        return  doctorArray;
    }

    private Integer[] GetImageArray()
    {
        Integer[] images={R.drawable.id,R.drawable.place,R.drawable.hospital,R.drawable.doc,R.drawable.date};
        return images;
    }

    private String [] GetString()
    {
        String[] stringArray={"תעודת זהות","כתובת","מקום עבודה","התמחות","גיל"};
        return stringArray;
    }

    private void SetDoctorList()
    {
        String[]doctorArray=GetDoctorArray();
        Integer[]images=GetImageArray();
        String []headings=GetString();

        UserList userListAdapter=new UserList(DoctorInterface.this,headings,images,doctorArray);
        m_DoctorList=(ListView)findViewById(R.id.doctorList);
        m_DoctorList.setAdapter(userListAdapter);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.doctor_interface, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.vaccinationMenu) {
            // Handle the camera action
        } else if (id == R.id.vaccinationlocation) {

            Intent mapsIntent = new Intent(this,MapsActivity.class);
            startActivity(mapsIntent);
        }

        else if (id == R.id.vaccinationinformation)
        {
            Intent newIntent=new Intent(getApplicationContext(),vaccineForDoctor.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(newIntent);
           // finish();

        }

        else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package msd.com.vaccinebook;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import Adapters.UserList;

public class UserInterface extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView m_userName;
    ListView m_listOfUsers;
    CommonUser m_CommonUser=new CommonUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interface);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ברוך הבא לאיזור האישי           ");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
////////////////////
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
/////////////////////
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FindViewById();
        SetData();
        m_userName.setText(m_CommonUser.getName()+" "+m_CommonUser.getLastName());
        SetListData();


    }
    private void FindViewById()
    {
      m_userName=(TextView)findViewById(R.id.userName);
    }

    private void SetListData()
    {
        String [] userList=GetUserArray();
        Integer[] imgList=GetImageArray();
        String[] userData=GetString();

        UserList userListAdapter=new UserList(UserInterface.this,userData,imgList,userList);
        m_listOfUsers=(ListView)findViewById(R.id.userList);
        m_listOfUsers.setAdapter(userListAdapter);

    }

    private void SetData()
    {
        Intent newIntent=getIntent();
        m_CommonUser.setName(newIntent.getStringExtra("name"));
        m_CommonUser.setLastName(newIntent.getStringExtra("lastname"));
        m_CommonUser.setId(newIntent.getStringExtra("id"));
        m_CommonUser.setMailAdress(newIntent.getStringExtra("mail"));
        m_CommonUser.setAdress(newIntent.getStringExtra("adress"));
        String age=newIntent.getStringExtra("age");
        m_CommonUser.setAge(Integer.valueOf(age));

    }

    private String [] GetString()
    {
        String[] stringArray={"תעודת זהות","כתובת דואר","כתובת","גיל"};
        return stringArray;
    }

    private String[] GetUserArray()
    {
        String age=String.valueOf(m_CommonUser.getAge());
        String[] dataArray={m_CommonUser.getId(),m_CommonUser.getMailAdress(),m_CommonUser.getAdress(),age};
        return dataArray;
    }

    private  Integer [] GetImageArray()
    {
      Integer[] imgArray={R.drawable.id, R.drawable.mailbox, R.drawable.place, R.drawable.date};
      return imgArray;
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
        getMenuInflater().inflate(R.menu.user_interface, menu);
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
        Intent intent;


        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_vaccines) {

            Intent vacIntent=new Intent(getApplicationContext(),ChooseVaccineToAddFromExpandableAdapterActivity.class);
            vacIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            vacIntent.putExtra("ID_of_patient", m_CommonUser.getId());
            startActivity(vacIntent);
           // finish();

//            VaccineFragment vaccineFragment = new VaccineFragment();
//            FragmentManager manager = getSupportFragmentManager();
//            manager.beginTransaction().replace(R.id.).commit();

        } else if (id == R.id.nav_places) {

//            Intent mapsIntent = new Intent(this,MapsActivity.class);
//            startActivity(mapsIntent);

            Intent mapsIntent=new Intent(getApplicationContext(),MapsActivity.class);
            mapsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mapsIntent);
            //finish();



        } else if (id == R.id.nav_infoabout) {

            Intent vacIntent2=new Intent(getApplicationContext(),ShowPatientVaccinesListActivity.class);
            vacIntent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            vacIntent2.putExtra("ID_of_patient", m_CommonUser.getId());
            startActivity(vacIntent2);
            //finish();



        }  else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

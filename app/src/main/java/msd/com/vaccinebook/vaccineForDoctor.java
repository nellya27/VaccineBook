package msd.com.vaccinebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import Adapters.VaccineListInfo;

public class vaccineForDoctor extends AppCompatActivity {

    ListView  m_ListOfVaccine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_for_doctor);
        SetListData();

    }

    private void SetListData()
    {
        final String[] vaccineString=GetVaccineList();
        Integer[] imglist=GetImgList();
        m_ListOfVaccine=(ListView)findViewById(R.id.listVaccineView);
        VaccineListInfo adapter=new VaccineListInfo(vaccineForDoctor.this,vaccineString,imglist);
        m_ListOfVaccine.setAdapter(adapter);

        m_ListOfVaccine.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(vaccineForDoctor.this, "You Clicked at " + vaccineString[+position], Toast.LENGTH_SHORT).show();
                if (vaccineString[+position].equals("HBV")) {
                    Intent newIntent = new Intent(getApplicationContext(), VaccineInfo.class);
                    newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(newIntent);
                    finish();
                }

            }
        });


    }

    private String[] GetVaccineList()
    {

       String[] vaccineList={"טבירקולין","HBV","Tdap","MMR","פוליו","אבעבועות רוח","שפעת"};
       return vaccineList;
    }

    private Integer[] GetImgList()
    {
        Integer [] imgList=new Integer[7];
        for(int i=0;i<7;i++)
        {
            imgList[i]= R.drawable.ic_launcher_info;
        }

        return imgList;
    }

}

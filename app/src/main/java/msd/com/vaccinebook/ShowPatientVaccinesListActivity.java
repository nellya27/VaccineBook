package msd.com.vaccinebook;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ShowPatientVaccinesListActivity extends Activity {
    private String patId;
    private ArrayList<String> vaccineParameterValues = new ArrayList<String>();
    private ArrayList<String> m_allVaccineData = new ArrayList<String>();
    private GoogleApiClient client;
    private JSONObject m_listVaccinsObject;

    private String m_daleketKaved_A_VaccinDesease;
    private String m_daleketKaved_A_VaccinPlace;
    private String m_daleketKaved_A_VaccinComments;
    private String m_daleketKaved_A_VaccinPortion;
    private String m_daleketKaved_A_VaccinSerial;
    private String m_daleketKaved_A_VaccinExpiration;

    private String m_ademet_VaccinDesease;
    private String m_ademet_VaccinPlace;
    private String m_ademet_VaccinComments;
    private String m_ademet_VaccinPortion;
    private String m_ademet_VaccinSerial;
    private String m_ademet_VaccinExpiration;

    private String m_hazeret_VaccinDesease;
    private String m_hazeret_VaccinPlace;
    private String m_hazeret_VaccinComments;
    private String m_hazeret_VaccinPortion;
    private String m_hazeret_VaccinSerial;
    private String m_hazeret_VaccinExpiration;

    private String m_shapaat_VaccinDesease;
    private String m_shapaat_VaccinPlace;
    private String m_shapaat_VaccinComments;
    private String m_shapaat_VaccinPortion;
    private String m_shapaat_VaccinSerial;
    private String m_shapaat_VaccinExpiration;

    private String m_papiloma_VaccinDesease;
    private String m_papiloma_VaccinPlace;
    private String m_papiloma_VaccinComments;
    private String m_papiloma_VaccinPortion;
    private String m_papiloma_VaccinSerial;
    private String m_papiloma_VaccinExpiration;
    // private ArrayAdapter showVaccinesAdapter;
    private ShowVaccineListAdapter vaccinesListAdapter;
    private ListView listOfVaccines;
    private int m_vaccinesNumber;
    private ExpandableListView vaccinesListView;
    /* private String[] m_vaccineParameters = new String[]{"שם החיסון", "מספר סידורי", "מספר מנה",
            "תוקף החיסון", "מיקום החיסון", "הערות"};
*/
    private String[] m_vaccineParameters = new String[]{ "מספר סידורי", "מספר מנה",
            "תוקף החיסון", "מיקום החיסון", "הערות"};
    private ArrayList<String> m_allVaccineDataList = new ArrayList<String>();
    private ArrayList<String> m_allKindVaccinesValues = new ArrayList<String>();
    private SparseArray<Group> groups = new SparseArray<Group>();

    private ArrayList<String>m_vaccineName=new ArrayList<String>();
    private ArrayList<String>m_vaccineParametersValues=new ArrayList<String>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    // private GoogleApiClient client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_patient_vaccines_list);
        patId = getIntent().getExtras().getString("ID_of_patient");
        findViewById();
        new ShowVaccineList(patId).execute();
    }

    public void findViewById() {

        // listOfVaccines = (ListView) findViewById(R.id.AllVaccinesListView);
        vaccinesListView = (ExpandableListView) findViewById(R.id.listViewOfVaccines);
    }



    private class ShowVaccineList extends AsyncTask<String, String, String> {

        private String patientID;

        public ShowVaccineList(String ID_Value) {
            this.patientID = ID_Value;
        }
        //###############################################################

        protected String doInBackground(String... args) {

            int j = 0;
            String respStr = "";

            try {
                OkHttpClient client = new OkHttpClient();

                RequestBody formBody = new FormBody.Builder()
                        .add("patID", this.patientID)
                        .build();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2:8080/VaccineBookProject/ViewAllPatientVaccinsServlet")
//                        .url("http://192.168.43.221:8080/VaccineBookProject/ViewAllPatientVaccinsServlet")
                        .post(formBody)
                        .build();

                Response response = null;
                response = client.newCall(request).execute();

                respStr = response.body().string();

                try {

                    m_listVaccinsObject = new JSONObject(respStr);
                    //###########################################################################
                    m_vaccinesNumber = Integer.parseInt(m_listVaccinsObject.getString("vaccinesNumber"));

                    m_daleketKaved_A_VaccinDesease = m_listVaccinsObject.getString("patientDaleketKaved_A");
                    m_daleketKaved_A_VaccinSerial = m_listVaccinsObject.getString("patientDaleketKaved_A_serial");
                    m_daleketKaved_A_VaccinPortion = m_listVaccinsObject.getString("patientDaleketKaved_A_portion");
                    m_daleketKaved_A_VaccinExpiration = m_listVaccinsObject.getString("patientDaleketKaved_A_expiration");
                    m_daleketKaved_A_VaccinPlace = m_listVaccinsObject.getString("patientDaleketKaved_A_place");
                    m_daleketKaved_A_VaccinComments = m_listVaccinsObject.getString("patientDaleketKaved_A_comments");

                    m_ademet_VaccinDesease = m_listVaccinsObject.getString("patientAdemet");
                    m_ademet_VaccinSerial = m_listVaccinsObject.getString("patientAdemet_serial");
                    m_ademet_VaccinPortion = m_listVaccinsObject.getString("patientAdemet_portion");
                    m_ademet_VaccinExpiration = m_listVaccinsObject.getString("patientAdemet_expiration");
                    m_ademet_VaccinPlace = m_listVaccinsObject.getString("patientAdemet_place");
                    m_ademet_VaccinComments = m_listVaccinsObject.getString("patientAdemet_comments");

                    m_hazeret_VaccinDesease = m_listVaccinsObject.getString("patientHazeret");
                    m_hazeret_VaccinSerial = m_listVaccinsObject.getString("patientHazeret_serial");
                    m_hazeret_VaccinPortion = m_listVaccinsObject.getString("patientHazeret_portion");
                    m_hazeret_VaccinExpiration = m_listVaccinsObject.getString("patientHazeret_expiration");
                    m_hazeret_VaccinPlace = m_listVaccinsObject.getString("patientHazeret_place");
                    m_hazeret_VaccinComments = m_listVaccinsObject.getString("patientHazeret_comments");

                    m_shapaat_VaccinDesease = m_listVaccinsObject.getString("patientShapaat");
                    m_shapaat_VaccinSerial = m_listVaccinsObject.getString("patientShapaat_serial");
                    m_shapaat_VaccinPortion = m_listVaccinsObject.getString("patientShapaat_portion");
                    m_shapaat_VaccinExpiration = m_listVaccinsObject.getString("patientShapaat_expiration");
                    m_shapaat_VaccinPlace = m_listVaccinsObject.getString("patientShapaat_place");
                    m_shapaat_VaccinComments = m_listVaccinsObject.getString("patientShapaat_comments");

                    m_papiloma_VaccinDesease = m_listVaccinsObject.getString("patientPapiloma");
                    m_papiloma_VaccinSerial = m_listVaccinsObject.getString("patientPapiloma_serial");
                    m_papiloma_VaccinPortion = m_listVaccinsObject.getString("patientPapiloma_portion");
                    m_papiloma_VaccinExpiration = m_listVaccinsObject.getString("patientPapiloma_expiration");
                    m_papiloma_VaccinPlace = m_listVaccinsObject.getString("patientPapiloma_place");
                    m_papiloma_VaccinComments = m_listVaccinsObject.getString("patientPapiloma_place");

                    //adding all vaccines values to  m_allKindVaccinesValues ArrayList
                    // this is an arrayList of all vaccines values without parameter names
                    // such as שפ החיסון או מיקום החיסון

                    m_allKindVaccinesValues.add(m_daleketKaved_A_VaccinDesease);
                    m_allKindVaccinesValues.add(m_daleketKaved_A_VaccinSerial);
                    m_allKindVaccinesValues.add(m_daleketKaved_A_VaccinPortion);
                    m_allKindVaccinesValues.add(m_daleketKaved_A_VaccinExpiration);
                    m_allKindVaccinesValues.add(m_daleketKaved_A_VaccinPlace);
                    m_allKindVaccinesValues.add(m_daleketKaved_A_VaccinComments);

                    m_allKindVaccinesValues.add(m_ademet_VaccinDesease);
                    m_allKindVaccinesValues.add(m_ademet_VaccinSerial);
                    m_allKindVaccinesValues.add(m_ademet_VaccinPortion);
                    m_allKindVaccinesValues.add(m_ademet_VaccinExpiration);
                    m_allKindVaccinesValues.add(m_ademet_VaccinPlace);
                    m_allKindVaccinesValues.add(m_ademet_VaccinComments);

                    m_allKindVaccinesValues.add(m_hazeret_VaccinDesease);
                    m_allKindVaccinesValues.add(m_hazeret_VaccinSerial);
                    m_allKindVaccinesValues.add(m_hazeret_VaccinPortion);
                    m_allKindVaccinesValues.add(m_hazeret_VaccinExpiration);
                    m_allKindVaccinesValues.add(m_hazeret_VaccinPlace);
                    m_allKindVaccinesValues.add(m_hazeret_VaccinComments);

                    m_allKindVaccinesValues.add(m_shapaat_VaccinDesease);
                    m_allKindVaccinesValues.add(m_shapaat_VaccinSerial);
                    m_allKindVaccinesValues.add(m_shapaat_VaccinPortion);
                    m_allKindVaccinesValues.add(m_shapaat_VaccinExpiration);
                    m_allKindVaccinesValues.add(m_shapaat_VaccinPlace);
                    m_allKindVaccinesValues.add(m_shapaat_VaccinComments);

                    m_allKindVaccinesValues.add(m_papiloma_VaccinDesease);
                    m_allKindVaccinesValues.add(m_papiloma_VaccinSerial);
                    m_allKindVaccinesValues.add(m_papiloma_VaccinPortion);
                    m_allKindVaccinesValues.add(m_papiloma_VaccinExpiration);
                    m_allKindVaccinesValues.add(m_papiloma_VaccinPlace);
                    m_allKindVaccinesValues.add(m_papiloma_VaccinComments);

                    //#################### NEW CODE  BEGINS ################################
                    //************************************************************************
                    //add values of vaccines names only
                    if(m_daleketKaved_A_VaccinDesease!=null && m_daleketKaved_A_VaccinDesease.length()!=0)
                    {
                        m_vaccineName.add(m_daleketKaved_A_VaccinDesease);
                    }
                    if(m_ademet_VaccinDesease!=null && m_ademet_VaccinDesease.length()!=0)
                    {
                        m_vaccineName.add(m_ademet_VaccinDesease);
                    }
                    if(m_hazeret_VaccinDesease!=null && m_hazeret_VaccinDesease.length()!=0)
                    {
                        m_vaccineName.add(m_hazeret_VaccinDesease);
                    }
                    if(m_shapaat_VaccinDesease!=null && m_shapaat_VaccinDesease.length()!=0)
                    {
                        m_vaccineName.add(m_shapaat_VaccinDesease);
                    }
                    if(m_papiloma_VaccinDesease!=null && m_papiloma_VaccinDesease.length()!=0)
                    {
                        m_vaccineName.add(m_papiloma_VaccinDesease);
                    }

                    //add values of rest vaccine parameters
                    m_vaccineParametersValues.add(m_daleketKaved_A_VaccinSerial);
                    m_vaccineParametersValues.add(m_daleketKaved_A_VaccinPortion);
                    m_vaccineParametersValues.add(m_daleketKaved_A_VaccinExpiration);
                    m_vaccineParametersValues.add(m_daleketKaved_A_VaccinPlace);
                    m_vaccineParametersValues.add(m_daleketKaved_A_VaccinComments);

                    m_vaccineParametersValues.add(m_ademet_VaccinSerial);
                    m_vaccineParametersValues.add(m_ademet_VaccinPortion);
                    m_vaccineParametersValues.add(m_ademet_VaccinExpiration);
                    m_vaccineParametersValues.add(m_ademet_VaccinPlace);
                    m_vaccineParametersValues.add(m_ademet_VaccinComments);

                    m_vaccineParametersValues.add(m_hazeret_VaccinSerial);
                    m_vaccineParametersValues.add(m_hazeret_VaccinPortion);
                    m_vaccineParametersValues.add(m_hazeret_VaccinExpiration);
                    m_vaccineParametersValues.add(m_hazeret_VaccinPlace);
                    m_vaccineParametersValues.add(m_hazeret_VaccinComments);

                    m_vaccineParametersValues.add(m_shapaat_VaccinSerial);
                    m_vaccineParametersValues.add(m_shapaat_VaccinPortion);
                    m_vaccineParametersValues.add(m_shapaat_VaccinExpiration);
                    m_vaccineParametersValues.add(m_shapaat_VaccinPlace);
                    m_vaccineParametersValues.add(m_shapaat_VaccinComments);

                    m_vaccineParametersValues.add(m_papiloma_VaccinSerial);
                    m_vaccineParametersValues.add(m_papiloma_VaccinPortion);
                    m_vaccineParametersValues.add(m_papiloma_VaccinExpiration);
                    m_vaccineParametersValues.add(m_papiloma_VaccinPlace);
                    m_vaccineParametersValues.add(m_papiloma_VaccinComments);


                    for (int k = 0; k <m_vaccineName.size() ; k++)
                    {
                        Group group = new Group(m_vaccineName.get(k));
                        for (int i = k * 5; i < k * 5 + 5; i++) {
                            group.childrenItems.add(m_vaccineParameters[i % 5] + " : " + m_vaccineParametersValues.get(i));
                        }
                        groups.append(k, group);

                    }
                    //clear all lists after each call
                    m_vaccineName.clear();
                    m_vaccineParametersValues.clear();
                    //**************************************************************************
                    //#################### NEW CODE ENDS ################################

                    for (int i = 0; i < (m_vaccineParameters.length * m_vaccinesNumber + m_allKindVaccinesValues.size()); i++) {
                        if (i == 0) {
                            m_allVaccineDataList.add(m_vaccineParameters[j / 2]);
                            j++;
                        } else if (i > 0) {
                            if (i % (m_vaccineParameters.length * 2) == 0) {
                                j = 0;
                            }

                            if (i % 2 == 0) {
                                m_allVaccineDataList.add(m_vaccineParameters[j / 2]);
                            }
                            if (i % 2 != 0) {
                                m_allVaccineDataList.add(m_allKindVaccinesValues.get(i / 2));
                            }
                               /* if(i % (m_vaccineParameters.length*2)!=0 )
                                {
                                    j++;
                                }*/

                            j++;

                        }
                    }


                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return m_shapaat_VaccinDesease;
        }

        protected void onPostExecute(String result)
        {
           AddVaccineActivity.m_portionIterate++;//new
            // ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.vaccine_layout, m_allVaccineDataList);
            /// listOfVaccines.setAdapter(adapter);
            // ArrayAdapter<String>arrayAdapter=listOfVaccines.getAdapter()
            //***************************************************************************************************
            // /vaccinesListAdapter =  new ShowVaccineListAdapter(getApplicationContext(),R.layout.vaccine_layout, m_allVaccineDataList);
            //listOfVaccines.setAdapter(vaccinesListAdapter);

            //************************************************************************************************
            // ##################### last before ######################################
            // vaccinesListAdapter =  new ShowVaccineListAdapter(ShowPatientVaccinesListActivity.this,R.layout.vaccine_layout, m_allVaccineDataList);
            //  listOfVaccines.setAdapter(vaccinesListAdapter);
            // ##################### last before ######################################
            //*************************************************************************************************
            ShowVaccinesExpandableListAdapter adapter = new ShowVaccinesExpandableListAdapter(ShowPatientVaccinesListActivity.this,
                    groups);
            vaccinesListView.setAdapter(adapter);
        }


    }
}





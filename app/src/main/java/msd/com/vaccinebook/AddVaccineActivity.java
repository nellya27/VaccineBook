package msd.com.vaccinebook;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddVaccineActivity extends Activity {

    private JSONObject m_addVaccinsObject;

    private EditText m_vaccineNameTxt;
    private EditText m_vaccineSerialTxt;
    private EditText m_vaccinePortionTxt;
    private EditText m_vaccineExpirationTxt;

    private EditText m_vaccinePlaceTxt;//todo
    private ImageButton m_vaccinePlaceMapData;//todo


    private String m_finalVaccineNameValue;
    private String m_finalVaccineSerialValue;
    private String m_finalVaccineExpirationValue;
    private String m_finalVaccinePortionValue;
    private String m_finalVaccinePlaceValue;
    private String m_finalVaccineCommentsValue;
    public static long m_vaccineSerialNumber=5541;
    private String vaccineExpTimeString;
    private String m_vaccineHebrewName;
    private String m_vaccineExpirationTime;
    private String[] m_expirationTimes=new String[]{"כל החיים","שנה","עשר שנים"};

    private EditText m_vaccineCommentsTxt;
    private String m_prevNewVaccineNameValue;
    private  String m_prevNewVaccineSerialValue;
    private  String m_prevNewVaccineExpirationValue;
    private  String m_prevNewVaccinePortionValue;
    private  String m_prevNewVaccinePlaceValue;
    private  String m_prevNewVaccineCommentsValue;

    private String m_newVaccineNameValue;
    private  String m_newVaccineSerialValue;
    private  String m_newVaccineExpirationValue;
    private  String m_newVaccinePortionValue;
    private  String m_newVaccinePlaceValue;
    private  String m_newVaccineCommentsValue;

    private String m_passedPatientID;
    private String m_passedVaccineKind;
    private GoogleApiClient client;
    private Button confVaccineAddButton;
    private Bundle extras;
    private String urlString;
    public static int m_portionIterate=1;
    private String vaccineDeseaseName;
    public static final MediaType MEDIA_TYPE
            = MediaType.parse("application/json; charset=utf-8");
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccine);
        extras=new Bundle();
        m_passedPatientID=getIntent().getExtras().getString("patientID");
        m_passedVaccineKind=getIntent().getExtras().getString("vaccineKind");
        findViewById();
     */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccine);
        extras=new Bundle();
        m_portionIterate++;

        m_passedPatientID=UserLogin.m_userLoginId;

       // m_passedPatientID=getIntent().getExtras().getString("patID");
        m_passedVaccineKind=getIntent().getExtras().getString("vaccineKind");
        m_vaccineHebrewName=getIntent().getExtras().getString("vaccineHebrewName");
        Log.i("ChildViewTag", "child text in AddVAccineActivity is : " + m_passedVaccineKind);
        Log.i("ChildViewTag", "patient id in AddVAccineActivity is : " + m_passedPatientID);
        Log.i("ChildViewTag", "vaccine Hebrew name is : " + m_vaccineHebrewName);
        findViewById();

        m_vaccinePlaceMapData = (ImageButton) findViewById(R.id.imageBtnPlacesMap);
        m_vaccinePlaceMapData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent mapsIntent = new Intent(v.getContext(), MapsActivity.class);
                //mapsIntent.putExtra()
                startActivity(mapsIntent);
            }
        });

        //get marker data from MapsActivity
        // String markerData = getIntent().getExtras().getString("markerTitle");
        String markerData=MapsActivity.m_markerMapTitle;
        m_vaccinePlaceTxt.setText(markerData);


        confVaccineAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)

            {
                getEditableVaccinParams();
                m_finalVaccineNameValue=m_newVaccineNameValue.replaceAll(m_newVaccineNameValue,m_vaccineHebrewName);
                m_finalVaccineSerialValue=m_newVaccineSerialValue.replaceAll(m_newVaccineSerialValue,String.valueOf(m_vaccineSerialNumber));
                m_finalVaccineExpirationValue=m_newVaccineExpirationValue.replaceAll(m_newVaccineExpirationValue,vaccineExpTimeString);
                m_finalVaccinePortionValue=m_newVaccinePortionValue.replaceAll(m_newVaccinePortionValue,String.valueOf(m_portionIterate));

                new VaccineParams(m_finalVaccineNameValue,m_finalVaccineSerialValue,
                        m_finalVaccineExpirationValue,m_finalVaccinePortionValue,m_newVaccinePlaceValue,
                        m_newVaccineCommentsValue,m_passedPatientID,m_passedVaccineKind).execute();

                /*
                getVaccinParams();
                //#################   LOGS  ####################################
                //Log.i("AddVaccineTag", "vaccine place is  : "+m_newVaccinePlaceValue);

                new VaccineParams(m_newVaccineNameValue,m_newVaccineSerialValue,
                        m_newVaccineExpirationValue,m_newVaccinePortionValue,m_newVaccinePlaceValue,
                        m_newVaccineCommentsValue,m_passedPatientID,m_passedVaccineKind).execute();
                        */
            }
        });
    }
    /*
    public void findViewById()
    {
        confVaccineAddButton=(Button)findViewById(R.id.confirmVaccinAddingBtn);


        m_vaccineNameTxt=(EditText)findViewById(R.id.vaccineNameView);
        m_vaccineSerialTxt=(EditText)findViewById(R.id.vaccineSerialView);
        m_vaccinePortionTxt=(EditText)findViewById(R.id.vaccinePortionView);
        m_vaccineExpirationTxt=(EditText)findViewById(R.id.vaccineExpirationView);
        m_vaccinePlaceTxt=(EditText)findViewById(R.id.vaccinePlaceView);
       // m_vaccinePlaceMapData=(ImageButton)findViewById(R.id.imageBtnPlacesMap);
        m_vaccineCommentsTxt=(EditText)findViewById(R.id.vaccineCommentsView);
    }
*/


    public void findViewById()
    {
        confVaccineAddButton=(Button)findViewById(R.id.confirmVaccinAddingBtn);
        m_vaccineNameTxt=(EditText)findViewById(R.id.vaccineNameView);
        m_vaccineSerialTxt=(EditText)findViewById(R.id.vaccineSerialView);
        m_vaccinePortionTxt=(EditText)findViewById(R.id.vaccinePortionView);
        m_vaccineExpirationTxt=(EditText)findViewById(R.id.vaccineExpirationView);
        m_vaccinePlaceTxt=(EditText)findViewById(R.id.vaccinePlaceView);
        m_vaccineCommentsTxt=(EditText)findViewById(R.id.vaccineCommentsView);

        getNonEditableVaccinParams();

    }

    public void getEditableVaccinParams()
    {
        //m_newVaccinePortionValue=m_vaccinePortionTxt.getText().toString();
        // m_newVaccinePortionValue=String.valueOf(m_portionIterate);
        m_newVaccinePlaceValue=m_vaccinePlaceTxt.getText().toString();
        m_newVaccineCommentsValue=m_vaccineCommentsTxt.getText().toString();
    }

    public void getNonEditableVaccinParams()
    {
        String text;
        // m_newVaccineNameValue=m_vaccineNameTxt.getText().toString();
        vaccineExpTimeString=setVaccineExpirationTime(m_passedVaccineKind);
        m_vaccineNameTxt.setEnabled(true);
        m_vaccineNameTxt.setText("שם : "+m_vaccineHebrewName);
        m_newVaccineNameValue=m_vaccineNameTxt.getText().toString();
        m_vaccineNameTxt.setEnabled(false);
        m_vaccineSerialNumber++;
        m_vaccineSerialTxt.setEnabled(true);
        text="מספר סידורי : "+String.valueOf(m_vaccineSerialNumber);
        m_vaccineSerialTxt.setText(text);

        m_newVaccineSerialValue=m_vaccineSerialTxt.getText().toString();
        m_vaccineSerialTxt.setEnabled(false);
        m_vaccineExpirationTxt.setEnabled(true);
        m_vaccineExpirationTxt.setText("תוקף : "+vaccineExpTimeString);
        m_newVaccineExpirationValue= m_vaccineExpirationTxt.getText().toString();
        m_vaccineExpirationTxt.setEnabled(false);

        m_vaccinePortionTxt.setEnabled(true);
        m_newVaccinePortionValue=String.valueOf(m_portionIterate);
        m_vaccinePortionTxt.setText("מנה :"+m_newVaccinePortionValue);
        m_vaccinePortionTxt.setEnabled(false);
    }

    public String setVaccineExpirationTime(String kindOfVaccine)
    {
        if(kindOfVaccine.equals("papiloma"))
        {
            m_vaccineExpirationTime=m_expirationTimes[0];
        }
        else if(kindOfVaccine.equals("hazeret"))
        {
            m_vaccineExpirationTime=m_expirationTimes[0];
        }
        else if(kindOfVaccine.equals("ademet"))
        {
            m_vaccineExpirationTime=m_expirationTimes[0];
        }
        else if(kindOfVaccine.equals("shapaat"))
        {
            m_vaccineExpirationTime=m_expirationTimes[1];
        }
        else if(kindOfVaccine.equals("daleket kaved alef"))
        {
            m_vaccineExpirationTime=m_expirationTimes[2];
        }
        return m_vaccineExpirationTime;
    }

/*

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("prevVaccineName", m_newVaccineNameValue);
        outState.putString("prevVaccineSerial", m_newVaccineSerialValue);
        outState.putString("prevVaccinePortion", m_newVaccinePortionValue);
        outState.putString("prevVaccineExpiration", m_newVaccineExpirationValue);
        outState.putString("patientIDvalue", m_passedPatientID);
        outState.putString("kindOfvaccineValue", m_passedVaccineKind);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        m_newVaccineNameValue = savedInstanceState.getString("prevVaccineName");
        m_newVaccineSerialValue = savedInstanceState.getString("prevVaccineSerial");
        m_newVaccinePortionValue = savedInstanceState.getString("prevVaccinePortion");
        m_newVaccineExpirationValue = savedInstanceState.getString("prevVaccineExpiration");
        // m_newVaccinePlaceValue="check value";
        m_passedPatientID = savedInstanceState.getString("patientIDvalue");
        m_passedVaccineKind = savedInstanceState.getString("kindOfvaccineValue");

    }

*/

    public void getVaccinParams()
    {
        m_newVaccineNameValue=m_vaccineNameTxt.getText().toString();
        m_newVaccineSerialValue=m_vaccineSerialTxt.getText().toString();
        //m_newVaccinePortionValue=m_vaccinePortionTxt.getText().toString();
        m_newVaccineExpirationValue= m_vaccineExpirationTxt.getText().toString();
        //  m_newVaccinePlaceValue=m_vaccinePlaceTxt.getText().toString();
        m_newVaccineCommentsValue=m_vaccineCommentsTxt.getText().toString();
    }


    private class VaccineParams extends AsyncTask<String, String, String> {

        String m_vaccinDeseaseValue;
        String m_vaccinPlaceValue;//todo
        String m_vaccinCommentsValue;
        String m_vaccinPortionValue;
        String m_vaccinSerialValue;
        String m_vaccinExpirationValue;
        String m_patientID;
        String m_vaccineKind;

        public VaccineParams (String i_vaccinDeseaseValue, String i_vaccinSerialvalue,
                              String i_vaccinExpirationValue, String i_vaccinPortionValue,
                              String i_vaccinPlaceValue, String i_vaccinCommentsValue,
                              String i_patientID, String i_vaccineKind) {


            m_vaccinDeseaseValue = i_vaccinDeseaseValue;
            m_vaccinSerialValue=i_vaccinSerialvalue;
            m_vaccinExpirationValue=i_vaccinExpirationValue;
            m_vaccinPortionValue=i_vaccinPortionValue;
            m_vaccinPlaceValue=i_vaccinPlaceValue;//todo
            m_vaccinCommentsValue=i_vaccinCommentsValue;
            m_patientID=i_patientID;
            m_vaccineKind=i_vaccineKind;
        }
        protected String doInBackground(String... args) {

            String respStr = "";

            try {
                OkHttpClient client = new OkHttpClient();

                RequestBody formBody = new FormBody.Builder()
                        .add("newVaccine", m_vaccinDeseaseValue)
                        .add("newVaccineSerial", m_vaccinSerialValue)
                        .add("newVaccinePortion", m_vaccinPortionValue)
                        .add("newVaccineExpiration", m_vaccinExpirationValue)
                        .add("newVaccinePlace", m_vaccinPlaceValue)
                        .add("newVaccineComments", m_vaccinCommentsValue)
                        .add("patID", m_patientID)
                        .add("vaccineKind", m_vaccineKind)
                        .build();

                if(this.m_vaccineKind.equals("papiloma"))
                {
//                    urlString="http://192.168.43.221:8080/VaccineBookProject/AddPapilomaServlet";
                    urlString="http://10.0.2.2:8080/VaccineBookProject/AddPapilomaServlet";

                }
                else if(this.m_vaccineKind.equals("shapaat"))
                {
//                    urlString="http://192.168.43.221:8080/VaccineBookProject/AddShapaatServlet";
                    urlString="http://10.0.2.2:8080/VaccineBookProject/AddShapaatServlet";

                }
                else if(this.m_vaccineKind.equals("ademet"))
                {
//                    urlString="http://192.168.43.221:8080/VaccineBookProject/AddAdemetServlet";
                    urlString="http://10.0.2.2:8080/VaccineBookProject/AddAdemetServlet";

                }
                else if(this.m_vaccineKind.equals("hazeret"))
                {
//                    urlString="http://192.168.43.221:8080/VaccineBookProject/AddHazeretServlet";
                    urlString="http://10.0.2.2:8080/VaccineBookProject/AddHazeretServlet";


                }
                else if(this.m_vaccineKind.equals("daleket kaved alef"))
                {
//                    urlString="http://192.168.43.221:8080/VaccineBookProject/AddDaleketKavedAlefServlet";
                    urlString="http://10.0.2.2:8080/VaccineBookProject/AddDaleketKavedAlefServlet";

                }

                Request request = new Request.Builder()
                        // .url("http://10.0.2.2:8080/VaccineBookProject/AddVaccinServlet")
                        .url(urlString)
                        .post(formBody)
                        .build();
                Response response = null;
                response = client.newCall(request).execute();

                respStr = response.body().string();

                try
                {
                    m_addVaccinsObject = new JSONObject(respStr);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result)
        {
            onBackPressed();
            //CommonUser cuser=new CommonUser();
            //cuser.setId(UserLogin.m_userLoginId);
//            // Intent addVaccinsIntent =new Intent(getBaseContext(),WelcomeUserActivity.class);
//            Intent addVaccinsIntent =new Intent(getApplicationContext(),UserLogin.class);
//            addVaccinsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            //addVaccinsIntent.putExtra("ID_of_patient",m_patientID);
//            startActivity(addVaccinsIntent);

        }
    }
//
//    public void StartMapsClass() {
//        m_vaccinePlaceMapData = (ImageButton)findViewById(R.id.imageBtnPlacesMap);
//        m_vaccinePlaceMapData.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent mapsIntent = new Intent(this,MapsActivity.class);
//                        startActivity(mapsIntent);
//                    }
//                }
//        );
//    }
}







































//package msd.com.vaccinebook;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//
//import com.google.android.gms.common.api.GoogleApiClient;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//
//import okhttp3.FormBody;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//public class AddVaccineActivity extends Activity {
//
//    private JSONObject m_addVaccinsObject;
//
//    private EditText m_vaccineNameTxt;
//    private EditText m_vaccineSerialTxt;
//    private EditText m_vaccinePortionTxt;
//    private EditText m_vaccineExpirationTxt;
//
//    private EditText m_vaccinePlaceTxt;
//    private ImageButton m_vaccinePlaceMapData;
//
//    private EditText m_vaccineCommentsTxt;
//    private String m_newVaccineNameValue;
//    private String m_newVaccineSerialValue;
//    private String m_newVaccineExpirationValue;
//    private String m_newVaccinePortionValue;
//    private String m_newVaccinePlaceValue;
//    private String m_newVaccineCommentsValue;
//    private String m_passedPatientID;
//    private String m_passedVaccineKind;
//    private GoogleApiClient client;
//    private Button confVaccineAddButton;
//    private Bundle extras;
//    private String urlString;
//
//    private String vaccineDeseaseName;
//    public static final MediaType MEDIA_TYPE
//            = MediaType.parse("application/json; charset=utf-8");
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_vaccine);
//        findViewById();
//        extras=new Bundle();
//
//        m_vaccinePlaceMapData = (ImageButton) findViewById(R.id.imageBtnPlacesMap);
//        m_vaccinePlaceMapData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent mapsIntent = new Intent(v.getContext(), MapsActivity.class);
//                mapsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(mapsIntent);
//                finish();
//            }
//        });
//
//        //get marker data from MapsActivity
//        String markerData = getIntent().getExtras().getString("markerTitle");
//        m_vaccinePlaceTxt.setText(markerData);
//
//        m_passedPatientID=getIntent().getExtras().getString("patientID");
//        m_passedVaccineKind=getIntent().getExtras().getString("vaccineKind");
//
//        confVaccineAddButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getVaccinParams();
//                new VaccineParams(m_newVaccineNameValue,m_newVaccineSerialValue,
//                        m_newVaccineExpirationValue,m_newVaccinePortionValue,m_newVaccinePlaceValue,
//                        m_newVaccineCommentsValue,m_passedPatientID,m_passedVaccineKind).execute();
//            }
//        });
//    }
//    public void findViewById()
//    {
//        confVaccineAddButton=(Button)findViewById(R.id.confirmVaccinAddingBtn);
//        m_vaccineNameTxt=(EditText)findViewById(R.id.vaccineNameView);
//        m_vaccineSerialTxt=(EditText)findViewById(R.id.vaccineSerialView);
//        m_vaccinePortionTxt=(EditText)findViewById(R.id.vaccinePortionView);
//        m_vaccineExpirationTxt=(EditText)findViewById(R.id.vaccineExpirationView);
//        m_vaccinePlaceTxt=(EditText)findViewById(R.id.vaccinePlaceView);
//       // m_vaccinePlaceMapData=(ImageButton)findViewById(R.id.imageBtnPlacesMap);//todo
//        m_vaccineCommentsTxt=(EditText)findViewById(R.id.vaccineCommentsView);
//    }
//
//
//    public void getVaccinParams()
//    {
//        m_newVaccineNameValue=m_vaccineNameTxt.getText().toString();
//        m_newVaccineSerialValue=m_vaccineSerialTxt.getText().toString();
//        m_newVaccinePortionValue=m_vaccinePortionTxt.getText().toString();
//        m_newVaccineExpirationValue= m_vaccineExpirationTxt.getText().toString();
//        m_newVaccinePlaceValue=m_vaccinePlaceTxt.getText().toString();
//        m_newVaccineCommentsValue=m_vaccineCommentsTxt.getText().toString();
//    }
//
////    @Override
////    protected void onSaveInstanceState(Bundle outState) {
////        super.onSaveInstanceState(outState);
////        outState.putString("prevVaccineName", m_newVaccineNameValue);
////        outState.putString("prevVaccineSerial", m_newVaccineSerialValue);
////        outState.putString("prevVaccinePortion", m_newVaccinePortionValue);
////        outState.putString("prevVaccineExpiration", m_newVaccineExpirationValue);
////        outState.putString("patientIDvalue", m_passedPatientID);
////        outState.putString("kindOfvaccineValue", m_passedVaccineKind);
////    }
////
////    @Override
////    protected void onRestoreInstanceState(Bundle savedInstanceState)
////    {
////        super.onRestoreInstanceState(savedInstanceState);
////        m_newVaccineNameValue = savedInstanceState.getString("prevVaccineName");
////        m_newVaccineSerialValue = savedInstanceState.getString("prevVaccineSerial");
////        m_newVaccinePortionValue = savedInstanceState.getString("prevVaccinePortion");
////        m_newVaccineExpirationValue = savedInstanceState.getString("prevVaccineExpiration");
////        // m_newVaccinePlaceValue="check value";
////        m_passedPatientID = savedInstanceState.getString("patientIDvalue");
////        m_passedVaccineKind = savedInstanceState.getString("kindOfvaccineValue");
////
////    }
//
//
//
//
//    private class VaccineParams extends AsyncTask<String, String, String> {
//
//        String m_vaccinDeseaseValue;
//        String m_vaccinPlaceValue;//todo
//        String m_vaccinCommentsValue;
//        String m_vaccinPortionValue;
//        String m_vaccinSerialValue;
//        String m_vaccinExpirationValue;
//        String m_patientID;
//        String m_vaccineKind;
//
//        public VaccineParams (String i_vaccinDeseaseValue, String i_vaccinSerialvalue,
//                              String i_vaccinExpirationValue, String i_vaccinPortionValue,
//                              String i_vaccinPlaceValue, String i_vaccinCommentsValue,
//                              String i_patientID, String i_vaccineKind) {
//
//
//            m_vaccinDeseaseValue = i_vaccinDeseaseValue;
//            m_vaccinSerialValue=i_vaccinSerialvalue;
//            m_vaccinExpirationValue=i_vaccinExpirationValue;
//            m_vaccinPortionValue=i_vaccinPortionValue;
//            m_vaccinPlaceValue=i_vaccinPlaceValue;//todo
//            m_vaccinCommentsValue=i_vaccinCommentsValue;
//            m_patientID=i_patientID;
//            m_vaccineKind=i_vaccineKind;
//        }
//        protected String doInBackground(String... args) {
//
//            String respStr = "";
//
//            try {
//                OkHttpClient client = new OkHttpClient();
//
//                RequestBody formBody = new FormBody.Builder()
//                        .add("newVaccine", m_vaccinDeseaseValue)
//                        .add("newVaccineSerial", m_vaccinSerialValue)
//                        .add("newVaccinePortion", m_vaccinPortionValue)
//                        .add("newVaccineExpiration", m_vaccinExpirationValue)
//                        .add("newVaccinePlace", m_vaccinPlaceValue)
//                        .add("newVaccineComments", m_vaccinCommentsValue)
//                        .add("patID", m_patientID)
//                        .add("vaccineKind", m_vaccineKind)
//                        .build();
//
//                if(this.m_vaccineKind.equals("papiloma"))
//                {
////                    urlString="http://192.168.43.221:8080/VaccineBookProject/AddPapilomaServlet";
//                    urlString="http://10.0.2.2:8080/VaccineBookProject/AddPapilomaServlet";
//
//                }
//                else if(this.m_vaccineKind.equals("shapaat"))
//                {
////                    urlString="http://192.168.43.221:8080/VaccineBookProject/AddShapaatServlet";
//                    urlString="http://10.0.2.2:8080/VaccineBookProject/AddShapaatServlet";
//
//                }
//                else if(this.m_vaccineKind.equals("ademet"))
//                {
////                    urlString="http://192.168.43.221:8080/VaccineBookProject/AddAdemetServlet";
//                     urlString="http://10.0.2.2:8080/VaccineBookProject/AddAdemetServlet";
//
//                }
//                else if(this.m_vaccineKind.equals("hazeret"))
//                {
////                    urlString="http://192.168.43.221:8080/VaccineBookProject/AddHazeretServlet";
//                      urlString="http://10.0.2.2:8080/VaccineBookProject/AddHazeretServlet";
//
//
//                }
//                else if(this.m_vaccineKind.equals("daleket kaved alef"))
//                {
////                    urlString="http://192.168.43.221:8080/VaccineBookProject/AddDaleketKavedAlefServlet";
//                      urlString="http://10.0.2.2:8080/VaccineBookProject/AddDaleketKavedAlefServlet";
//
//                }
//
//                Request request = new Request.Builder()
//                       // .url("http://10.0.2.2:8080/VaccineBookProject/AddVaccinServlet")
//                        .url(urlString)
//                        .post(formBody)
//                        .build();
//                Response response = null;
//                response = client.newCall(request).execute();
//
//                respStr = response.body().string();
//
//                try
//                {
//                    m_addVaccinsObject = new JSONObject(respStr);
//
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        protected void onPostExecute(String result)
//        {
//
//            Intent addVaccinsIntent =new Intent(getBaseContext(),WelcomeUserActivity.class);
//            addVaccinsIntent.putExtra("ID_of_patient",m_patientID);
//            startActivity(addVaccinsIntent);
//        }
//    }
////
////    public void StartMapsClass() {
////        m_vaccinePlaceMapData = (ImageButton)findViewById(R.id.imageBtnPlacesMap);
////        m_vaccinePlaceMapData.setOnClickListener(
////                new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        Intent mapsIntent = new Intent(this,MapsActivity.class);
////                        startActivity(mapsIntent);
////                    }
////                }
////        );
////    }
//}

package msd.com.vaccinebook;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import Alerts.UserAlert;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DoctorLogin extends AppCompatActivity {

    EditText m_UserNameInput;
    EditText m_UserPassInput;
    private Button m_LogButton;
    private boolean isSucceed=true;
    private JSONObject m_Jobject;
    private Doctor m_NewDoctor=new Doctor();
    private UserAlert newUserAlert =new UserAlert();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorlogin2);
        findViewById();
        m_LogButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                new DocLogin(m_UserNameInput.getText().toString(), m_UserPassInput.getText().toString()).execute();
                if(!isSucceed)
                {
                    newUserAlert.DisplayInvalidLoginAlert(DoctorLogin.this);
                    isSucceed=true;
                }
            }
        });
    }

    private void findViewById() {
        m_UserNameInput = (EditText) findViewById(R.id.txtUser);
        m_UserPassInput = (EditText) findViewById(R.id.passUser);
        m_LogButton = (Button) findViewById(R.id.checkLogUser);

    }
    private class DocLogin extends AsyncTask<String, String, String> {

        String uName;
        String pWord;


        public DocLogin(String i_userName, String i_PaasWord) {
            this.uName = i_userName;
            this.pWord = i_PaasWord;
        }

        @Override
        protected String doInBackground(String... args) {
            // Getting username and password from user input
            String respStr = "";

            try {
                OkHttpClient client = new OkHttpClient();

                RequestBody formBody = new FormBody.Builder()
                        .add("doctorId", this.uName)
                        .add("doctorPassword", this.pWord)
                        .build();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2:8080/VaccineBookProject/DoctorLogin")
                        .post(formBody)
                        .build();

                Response response = null;
                response = client.newCall(request).execute();

                respStr = response.body().string();


                String s = null;

                try {
                    Log.d("MainActivity", respStr);

                    m_Jobject = new JSONObject(respStr);

                    s = m_Jobject.getString("info");
                    if (s.equals("success")) {

                        m_NewDoctor.setName(m_Jobject.getString("name"));
                        m_NewDoctor.setLastName(m_Jobject.getString("lastname"));
                        m_NewDoctor.setDoctorAdress(m_Jobject.getString("adress"));
                        m_NewDoctor.setDoctorAge(m_Jobject.getString("age"));
                        m_NewDoctor.setDoctorId(m_Jobject.getString("id"));
                        m_NewDoctor.setDoctorSpecility(m_Jobject.getString("speciality"));
                        m_NewDoctor.setWorkPlace(m_Jobject.getString("placeofwork"));
                        LoadDataForNewActivity();

                    }
                    else
                    {
                        isSucceed=false;
                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        private void LoadDataForNewActivity()
        {
            Intent login = new Intent(getApplicationContext(),DoctorInterface.class);
            login.putExtra("name",m_NewDoctor.getName());
            login.putExtra("lastname",m_NewDoctor.getLastName());
            login.putExtra("id",m_NewDoctor.getDoctorId());
            login.putExtra("speciality",m_NewDoctor.getDoctorSpecility());
            login.putExtra("workplace",m_NewDoctor.getWorkPlace());
            login.putExtra("adress", m_NewDoctor.getDoctorAdress());
            login.putExtra("age", m_NewDoctor.getDoctorAge());
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(login);
            finish();
        }


    }

}








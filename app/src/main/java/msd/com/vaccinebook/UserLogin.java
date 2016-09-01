package msd.com.vaccinebook;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class UserLogin extends AppCompatActivity {

    EditText m_UserNameInput;
    EditText m_UserPassInput;
    private Button m_LogButton;
    private String m_user;
    private String m_pass;
    Button m_SignInButton;

    private String m_loginPatientName;
//    private String m_infoString;
    //private static String s_urlString="http://10.0.2.2:8080/AndroidLogin/servlet";
    private JSONObject m_Jobject;
    private CommonUser newUser=new CommonUser();
    private boolean isSucceed=true;
    private UserAlert newUserAlert=new UserAlert();
    public static String m_userLoginId;//new

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        findViewById();
        m_LogButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                new Login(m_UserNameInput.getText().toString(),m_UserPassInput.getText().toString()).execute();
                if(!isSucceed)
                {
                    newUserAlert.DisplayInvalidLoginAlert(UserLogin.this);
                }

            }
        });
        m_SignInButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {

                StartSignUser();
            }
        });
    }

    private void findViewById()
    {
        m_UserNameInput=(EditText)findViewById(R.id.txtUser);
        m_UserPassInput=(EditText)findViewById(R.id.passUser);
        m_LogButton=(Button)findViewById(R.id.checkLogUser);
        m_SignInButton=(Button)findViewById(R.id.m_signbutton);

    }
    private class Login extends AsyncTask<String, String, String> {

        String uName;
        String pWord;

        public Login(String i_userName,String i_PaasWord)
        {
            this.uName=i_userName;
            this.pWord=i_PaasWord;
        }

        @Override
        protected String doInBackground(String... args) {
            // Getting username and password from user input
            String respStr = "";

            try {
                OkHttpClient client = new OkHttpClient();

                RequestBody formBody = new FormBody.Builder()
                        .add("username", this.uName)
                        .add("password", this.pWord)
                        .build();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2:8080/VaccineBookProject/LoginServlet")
                      //  .url("http://192.168.43.221:8080/VaccineBookProject/LoginServlet")
//                        .url("http://192.168.43.221:8080/VaccineBookProject/LoginServlet")
                        .post(formBody)
                        .build();

                Response response = null;
                response = client.newCall(request).execute();

                respStr = response.body().string();

                String s=null;

//                try {
//                    Log.d("MainActivity", respStr);
//
//                    m_Jobject = new JSONObject(respStr);
//
//                    s = m_Jobject.getString("info");
//                    if (s.equals("success")) {
//
//                        Intent login = new Intent(getApplicationContext(), welcomeUser.class);
//                        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(login);
//                        finish();
//                    }

                //////////////////////// neli code

                try {
                    Log.d("MainActivity", respStr);

                    m_Jobject = new JSONObject(respStr);
                    m_user=m_Jobject.getString("userString");
                    m_pass=m_Jobject.getString("passString");
                    m_loginPatientName=m_Jobject.getString("firstname");
                    s = m_Jobject.getString("info");

                    if (s.equals("success")) {
                        m_userLoginId=uName;//new
                      //  m_loginPatientName=m_Jobject.getString("firstname");
                        //newUser.setName(m_loginPatientName);
                        newUser.setName(m_Jobject.getString("firstname"));
                        newUser.setLastName(m_Jobject.getString("lastname"));
                        newUser.setMailAdress(m_Jobject.getString("mail"));
                        newUser.setId(m_Jobject.getString("id"));
                        newUser.setAge(m_Jobject.getInt("age"));
                        newUser.setAdress(m_Jobject.getString("adress"));
                        SetDataOnNewActivity();

                    }
                    else
                    {
                        isSucceed=false;
                    }

                ///////////////////////


///////////////////////////////////////////////////////////////
//                try {
//                    Log.d("MainActivity",respStr);
//
//                    m_Jobject = new JSONObject(respStr);
//
//                 //   s= m_Jobject.getString("info");
//                    m_infoString=m_Jobject.getString("info");
//                    m_user=m_Jobject.getString("userString");
//                    m_pass=m_Jobject.getString("passString");
//
//                    Log.i("LoginTag", "m_user is : "+m_user);
//                    Log.i("LoginTag", "m_pass is : "+m_pass);
//                    Log.i("LoginTag", "info is  : "+m_infoString);
//
//                    Log.d("Info:", m_Jobject.getString("info"));
//                    Log.d("Msg:", m_Jobject.getString("msg"));

       ///////////////////////////////////////////////////////////////////

                   // if(s.equals("success")){
                    /*
                    if(uName.equals(m_user)&& pWord.equals(m_pass)){

                       // Intent login = new Intent(getApplicationContext(), welcomeUser.class);
                        Intent login = new Intent(getApplicationContext(), WelcomeUserActivity.class);
                        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(login);
                        finish();
                    }
                    else{
                        Intent login = new Intent(getApplicationContext(), PatientVaccineListActivity.class);
                        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(login);
                        finish();
                    }
                    */

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

//        protected void onPostExecute(String result)
//        {
//
//            if(uName.equals(m_user)&& pWord.equals(m_pass)){
//                m_userLoginId=uName;
//
//                // Intent login = new Intent(getApplicationContext(), welcomeUser.class);
//                Intent loginIntent = new Intent(getApplicationContext(), WelcomeUserActivity.class);
//                loginIntent.putExtra("ID_of_patient",m_userLoginId);
//                loginIntent.putExtra("nameOfUser",m_loginPatientName);
//                loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(loginIntent);
//                finish();
//            }
//            /*
//            else{
//                Intent login = new Intent(getApplicationContext(), PatientVaccineListActivity.class);
//                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(login);
//                finish();
//            }
//            */
//        }

    }
    private void StartSignUser()
    {
        Intent intent =new Intent(this,SignUser.class);
        startActivity(intent);
    }

    private void SetDataOnNewActivity()
    {
        Intent login = new Intent(getApplicationContext(), UserInterface.class);
        login.putExtra("name",newUser.getName());
        login.putExtra("lastname",newUser.getLastName());
        login.putExtra("mail",newUser.getMailAdress());
        login.putExtra("id", newUser.getId());
        String age=String.valueOf(newUser.getAge());
        login.putExtra("age",age);
        login.putExtra("adress",newUser.getAdress());
        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(login);
        finish();

    }


}



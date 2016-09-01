package msd.com.vaccinebook;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUser extends AppCompatActivity {

    EditText m_userName;
    EditText m_userLastName;
    EditText m_userId;
    EditText m_userMail;
    EditText m_userNameAccount;
    EditText m_userPassAccount;
    EditText m_userAge;
    EditText m_userAdress;
    Button m_signUser;
    JSONObject m_jsonObj;
    public static final MediaType MEDIA_TYPE
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_user);
        FindViewById();
        m_userName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                                @Override
                                                public void onFocusChange(View v, boolean hasFocus) {

                                                    if (TextUtils.isEmpty(m_userName.getText().toString())) {
                                                        m_userName.setError("Please fill your first name");
                                                    }
                                                }
                                            }

        );
        m_userLastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(TextUtils.isEmpty(m_userLastName.getText().toString()))
                {
                    m_userLastName.setError("Please fill your last name");
                }


            }
        });

        m_userId.setOnFocusChangeListener((new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(m_userId.getText().length()!=9 && m_userId.getText().toString()!=null)
                {
                    m_userId.setError("Please type all 9 digits of your id");
                }

            }
        }));

        m_userMail.setOnFocusChangeListener((new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!IsValidMail(m_userMail.getText().toString()))
                {
                    m_userMail.setError("The mail you typed is invalid");
                }
            }
        }));

        m_userNameAccount.setOnFocusChangeListener((new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                 if(TextUtils.isEmpty(m_userNameAccount.getText().toString()))
                {
                    m_userNameAccount.setError("Please fill your user name");
                }
            }
        }));

        m_userPassAccount.setOnFocusChangeListener((new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(!IsValidPassword(m_userPassAccount.getText().toString()))
                {
                    m_userPassAccount.setError("Your password should contain\n at least one lowercase charachter\nat least one upper case character\n" +
                            "at least one digit\n and the legnth of the password should be at least 6");
                }
            }
        }));

        m_userAge.setOnFocusChangeListener((new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(TextUtils.isEmpty(m_userAge.getText().toString()))
                {
                    m_userAge.setError("Please type your age");
                }
            }
        }));

        m_userAdress.setOnFocusChangeListener((new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(TextUtils.isEmpty(m_userAdress.getText().toString()))
                {
                    m_userAdress.setError("Please type your currentAdress");
                }
            }
        }));

        m_signUser.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                new Register(m_userName.getText().toString(), m_userLastName.getText().toString(), m_userId.getText().toString(), m_userMail.getText().toString(), m_userNameAccount.getText().toString(), m_userPassAccount.getText().toString(),m_userAge.getText().toString(),m_userAdress.getText().toString()).execute();

            }
        });




    }

    private void FindViewById()
    {
        m_userName=(EditText)findViewById(R.id.m_NameSign);
        m_userLastName=(EditText)findViewById(R.id.m_LastNameSign);
        m_userId=(EditText)findViewById(R.id.m_IdSign);
        m_userMail=(EditText)findViewById(R.id.m_MailSign);
        m_userNameAccount=(EditText)findViewById(R.id.m_UserNameSign);
        m_userPassAccount=(EditText)findViewById(R.id.m_PassSign);
        m_userAge=(EditText)findViewById(R.id.m_userAge);
        m_userAdress=(EditText)findViewById(R.id.m_userAdress);
        m_signUser=(Button)findViewById(R.id.m_SignButton);
    }

    private Boolean IsValidPassword(String Password)
    {
        if(Password!=null && Password.length() > 6)
        {
            return true;
        }
        else
            return false;

    }

    private  Boolean IsValidMail(String Mail)
    {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(Mail);
        return matcher.matches();
    }

    private static final Pattern [] passwordRegexes = new Pattern[3];
    {
        passwordRegexes[0] = Pattern.compile(".*[A-Z].*");
        passwordRegexes[1] = Pattern.compile(".*[a-z].*");
        passwordRegexes[2] = Pattern.compile(".*\\d.*");
    }
    public boolean isLegalPassword(String pass) {

        boolean isValid=true;
        if(pass.length()>6 && pass!=null) {

            for (int i = 0; i < passwordRegexes.length; i++) {
                if (!passwordRegexes[i].matcher(pass).matches())
                    isValid=false;
            }

        }
        else
        {
            isValid=false;
        }
        return isValid;
    }

    private class Register extends AsyncTask<String,String,String>
    {
        String  uName;
        String  uLastName;
        String  uId;
        String  uMail;
        String  uNameAccount;
        String  uPassAccount;
        String  uAge;
        String  uAdress;


        public Register(String i_uName,String i_uLastName,String i_uId,String i_uMail,String i_uNameAccount,String i_uPassAccount,String i_Age,String i_Adress)
        {
            uName=i_uName;
            uLastName=i_uLastName;
            uId=i_uId;
            uMail=i_uMail;
            uNameAccount=i_uNameAccount;
            uPassAccount=i_uPassAccount;
            uAge=i_Age;
            uAdress=i_Adress;
        }


        @Override
        protected String doInBackground(String... params) {

            String responseStr="";

            try
            {
                OkHttpClient client=new OkHttpClient();
                RequestBody body=new FormBody.Builder()
//                        .add("userName",uNameAccount)
//                        .add("userPass",uPassAccount)
//                        .add("firstName",uName)
//                        .add("lastName",uLastName)
//                        .add("mail",uMail)
//                        .add("Id",uId)
//                        .add("age",uAge)
//                        .add("adress",uAdress)
                        .add("Id",uId)
                        .add("userName",uNameAccount)
                        .add("userPass",uPassAccount)
                        .add("firstName",uName)
                        .add("lastName",uLastName)
                        .add("adress",uAdress)
                        .add("mail",uMail)
                        .add("age",uAge)

                        .build();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2:8080/VaccineBookProject/RegistrationServlet")
                        //.url("http://192.168.43.20:8080/VaccineBookProject/RegistrationServlet")
                        .post(body)
                        .build();

                Response response=null;
                response=client.newCall(request).execute();
                responseStr=response.body().string();
                String parsedStr=null;

                Log.d("MainActivity",responseStr);
                try {
                    m_jsonObj=new  JSONObject(responseStr);
                    parsedStr=m_jsonObj.getString("info");

                    if(parsedStr.equals("success"))
                    {
                        //Toast.makeText(getApplicationContext(),"Registration completed",Toast.LENGTH_LONG).show();
                        Intent login = new Intent(getApplicationContext(), UserLogin.class);
                        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(login);
                        finish();
                    }
                    else
                    {
                      //  Toast.makeText(getApplicationContext(),"the registration not done",Toast.LENGTH_LONG).show();
                        Intent login = new Intent(getApplicationContext(), SignUser.class);
                        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(login);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }

}

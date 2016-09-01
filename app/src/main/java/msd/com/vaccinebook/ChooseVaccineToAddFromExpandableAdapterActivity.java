

package msd.com.vaccinebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ExpandableListView;

public class ChooseVaccineToAddFromExpandableAdapterActivity extends Activity
{

    private String m_userID;
    private Bundle extras;
    private String m_vaccineChoosedKind;
    private ExpandableListView chooseKindOfVaccineList;
    private final String m_addVaccineHeading = "בחר חיסון";
    String[] m_vaccineTypeArray = new String[]{"פפילומה", "אדמת", "חזרת", "שפעת", "דלקת כבד אלף"};
    private SparseArray<Group> vaccineKindGroup = new SparseArray<Group>();
    AddExpandableVaccineListAdapter addExpandAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_vaccine_to_add_from_expandable_adapter);
        extras = new Bundle();
        findViewById();
        for (int k = 0; k < 1; k++) {
            Group group = new Group(m_addVaccineHeading);
            for (int i = 0; i < m_vaccineTypeArray.length; i++) {
                group.childrenItems.add(m_vaccineTypeArray[i]);
            }
            vaccineKindGroup.append(k, group);
        }
        addExpandAdapter = new AddExpandableVaccineListAdapter(this,
                vaccineKindGroup);
        chooseKindOfVaccineList.setAdapter(addExpandAdapter);
        //m_userID=addExpandAdapter.getPatID();
        // m_userID = getIntent().getExtras().getString("userID");
        m_userID=UserLogin.m_userLoginId;
        m_vaccineChoosedKind=getIntent().getExtras().getString("choosedVaccine");
        // m_userID = getApplicationContext().m_userId;
        Log.i("ChildViewTag", "child text in activity is : "+m_vaccineChoosedKind);
        Log.i("ChildViewTag", "user id from viewOptionsActivity is : "+m_userID);
        Log.i("ChildViewTag", "patient id from adapter in ChooseVaccineToAddFromExpandableAdapterActivity is : "+m_userID);
        if(m_vaccineChoosedKind!=null)
        {
            Intent vaccineClickIntent = new Intent(this, AddVaccineActivity.class);
            if (m_vaccineChoosedKind.equals("פפילומה")) {
                extras.putString("patID", UserLogin.m_userLoginId);
                extras.putString("vaccineKind", "papiloma");
                extras.putString("vaccineHebrewName",m_vaccineChoosedKind);
                vaccineClickIntent.putExtras(extras);
                startActivity(vaccineClickIntent);
            }
            if (m_vaccineChoosedKind.equals("שפעת")) {
                extras.putString("patID", UserLogin.m_userLoginId);
                extras.putString("vaccineKind", "shapaat");
                extras.putString("vaccineHebrewName",m_vaccineChoosedKind);
                vaccineClickIntent.putExtras(extras);
                startActivity(vaccineClickIntent);
            }
            if (m_vaccineChoosedKind.equals("חזרת")) {
                extras.putString("patID", UserLogin.m_userLoginId);
                extras.putString("vaccineKind", "hazeret");
                extras.putString("vaccineHebrewName",m_vaccineChoosedKind);
                vaccineClickIntent.putExtras(extras);
                startActivity(vaccineClickIntent);
            }
            if (m_vaccineChoosedKind.equals("אדמת")) {
                extras.putString("patID", UserLogin.m_userLoginId);
                extras.putString("vaccineKind", "ademet");
                extras.putString("vaccineHebrewName",m_vaccineChoosedKind);
                vaccineClickIntent.putExtras(extras);
                startActivity(vaccineClickIntent);
            }
            if (m_vaccineChoosedKind.equals("דלקת כבד אלף")) {
                extras.putString("patID", UserLogin.m_userLoginId);
                extras.putString("vaccineKind", "daleket kaved alef");
                extras.putString("vaccineHebrewName",m_vaccineChoosedKind);
                vaccineClickIntent.putExtras(extras);
                startActivity(vaccineClickIntent);
            }
        }
    }

    private void findViewById()
    {
        chooseKindOfVaccineList = (ExpandableListView) findViewById(R.id.chooseVaccineKindListView);
    }
}




























//package msd.com.vaccinebook;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.util.SparseArray;
//import android.widget.ExpandableListView;
//
//public class ChooseVaccineToAddFromExpandableAdapterActivity extends Activity
//{
//
//    private String m_userID;
//    private Bundle extras;
//    private String m_vaccineChoosedKind;
//    private ExpandableListView chooseKindOfVaccineList;
//    private final String m_addVaccineHeading = "בחר חיסון";
//    String[] m_vaccineTypeArray = new String[]{"פפילומה", "אדמת", "חזרת", "שפעת", "דלקת כבד אלף"};
//    private SparseArray<Group> vaccineKindGroup = new SparseArray<Group>();
//    AddExpandableVaccineListAdapter addExpandAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_choose_vaccine_to_add_from_expandable_adapter);
//        extras = new Bundle();
//        findViewById();
//        for (int k = 0; k < 1; k++) {
//            Group group = new Group(m_addVaccineHeading);
//            for (int i = 0; i < m_vaccineTypeArray.length; i++) {
//                group.childrenItems.add(m_vaccineTypeArray[i]);
//            }
//            vaccineKindGroup.append(k, group);
//        }
//        addExpandAdapter = new AddExpandableVaccineListAdapter(this,
//                vaccineKindGroup);
//        chooseKindOfVaccineList.setAdapter(addExpandAdapter);
//        //m_userID=addExpandAdapter.getPatID();
//       // m_userID = getIntent().getExtras().getString("userID");
//        m_userID=UserLogin.m_userLoginId;
//        m_vaccineChoosedKind=getIntent().getExtras().getString("choosedVaccine");
//       // m_userID = getApplicationContext().m_userId;
//        Log.i("ChildViewTag", "child text in activity is : "+m_vaccineChoosedKind);
//        Log.i("ChildViewTag", "user id from viewOptionsActivity is : "+m_userID);
//        Log.i("ChildViewTag", "patient id from adapter in ChooseVaccineToAddFromExpandableAdapterActivity is : "+m_userID);
//        if(m_vaccineChoosedKind!=null)
//        {
//            Intent vaccineClickIntent = new Intent(this, AddVaccineActivity.class);
//            if (m_vaccineChoosedKind.equals("פפילומה")) {
//                extras.putString("patID", UserLogin.m_userLoginId);
//                extras.putString("vaccineKind", "papiloma");
//                extras.putString("vaccineHebrewName",m_vaccineChoosedKind);
//                vaccineClickIntent.putExtras(extras);
//                startActivity(vaccineClickIntent);
//            }
//            if (m_vaccineChoosedKind.equals("שפעת")) {
//                extras.putString("patID", UserLogin.m_userLoginId);
//                extras.putString("vaccineKind", "shapaat");
//                extras.putString("vaccineHebrewName",m_vaccineChoosedKind);
//                vaccineClickIntent.putExtras(extras);
//                startActivity(vaccineClickIntent);
//            }
//            if (m_vaccineChoosedKind.equals("חזרת")) {
//                extras.putString("patID", UserLogin.m_userLoginId);
//                extras.putString("vaccineKind", "hazeret");
//                extras.putString("vaccineHebrewName",m_vaccineChoosedKind);
//                vaccineClickIntent.putExtras(extras);
//                startActivity(vaccineClickIntent);
//            }
//            if (m_vaccineChoosedKind.equals("אדמת")) {
//                extras.putString("patID", UserLogin.m_userLoginId);
//                extras.putString("vaccineKind", "ademet");
//                extras.putString("vaccineHebrewName",m_vaccineChoosedKind);
//                vaccineClickIntent.putExtras(extras);
//                startActivity(vaccineClickIntent);
//            }
//            if (m_vaccineChoosedKind.equals("דלקת כבד אלף")) {
//                extras.putString("patID", UserLogin.m_userLoginId);
//                extras.putString("vaccineKind", "daleket kaved alef");
//                extras.putString("vaccineHebrewName",m_vaccineChoosedKind);
//                vaccineClickIntent.putExtras(extras);
//                startActivity(vaccineClickIntent);
//            }
//        }
//    }
//
//    private void findViewById()
//    {
//        chooseKindOfVaccineList = (ExpandableListView) findViewById(R.id.chooseVaccineKindListView);
//    }
//}

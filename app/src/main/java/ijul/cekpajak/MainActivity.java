package ijul.cekpajak;

/*  I am not Android Developer, just person who wanna try developing an apps
*   If you see this, then you're a developer. Thanks for see my bad code
*
*   ijuldz@gmail.com
* */


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        AwalActivity awalActivity = new AwalActivity();
        ft.add(R.id.frame, awalActivity);
        ft.commit();

    }



}
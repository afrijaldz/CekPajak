package ijul.cekpajak;

/*  I am not Android Developer, just person who wanna try developing an apps
*   If you see this, then you're a developer. Thanks for see my bad code
*
*   ijuldz@gmail.com
* */


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //  variable
    private EditText nomorplat, kodeplat, seriplat;
    private Button btn;
    private TextView plat, provinsi, jenis, merk, type, tahun, warna, wilayah, jumlah, jatuh, baliknama, jasaraharja;
    private TextView tprovinsi, tjenis, tmerk, ttype, ttahun, twarna, twilayah, tjumlah, tpkb, datakendaraan, tbiaya, tjatuh, tbaliknama, tjasa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ambil ID
        nomorplat = (EditText) findViewById(R.id.nomerplat);
        kodeplat = (EditText)findViewById(R.id.kodeplat);
        seriplat = (EditText)findViewById(R.id.seriplat);

        // text
        tbaliknama = (TextView)findViewById(R.id.tbaliknama);
        tjasa = (TextView)findViewById(R.id.tjasaraharja);
        tjatuh = (TextView)findViewById(R.id.tjatuh);
        tprovinsi = (TextView)findViewById(R.id.tprovinsi);
        tjenis = (TextView)findViewById(R.id.tjenis);
        tmerk = (TextView)findViewById(R.id.tmerk);
        ttype = (TextView)findViewById(R.id.ttype);
        ttahun = (TextView)findViewById(R.id.ttahun);
        twarna = (TextView)findViewById(R.id.twarna);
        twilayah = (TextView)findViewById(R.id.twilayah);
        tjumlah = (TextView)findViewById(R.id.tjumlah);
        tpkb = (TextView)findViewById(R.id.tpkb);
        datakendaraan = (TextView)findViewById(R.id.datakendaraan);
        tbiaya = (TextView)findViewById(R.id.tbiaya);

        // hasil
        plat = (TextView)findViewById(R.id.plat);
        provinsi = (TextView)findViewById(R.id.provinsi);
        jenis = (TextView)findViewById(R.id.jenis);
        merk = (TextView)findViewById(R.id.merk);
        type = (TextView)findViewById(R.id.type);
        tahun = (TextView)findViewById(R.id.tahun);
        warna = (TextView)findViewById(R.id.warna);
        wilayah = (TextView)findViewById(R.id.wilayah);
        jumlah = (TextView)findViewById(R.id.jumlah);
        jatuh = (TextView)findViewById(R.id.jatuh);
        baliknama = (TextView)findViewById(R.id.baliknama);
        jasaraharja = (TextView)findViewById(R.id.jasaraharja);

        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(this);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void getData(String kodeplat , String nomorplat, String seriplat){
        String URL = "http://ibacor.com/api/pajak-kendaraan?kode="+kodeplat+"&nomor="+nomorplat+"&seri="+seriplat;
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("DATA", response.toString());
                try {
                    // convert data json ke string
                    JSONObject object = new JSONObject(response.toString());

                    String provins = object.getString("provinsi");

                    datakendaraan.setText("DATA KENDARAAN : ");
                    provinsi.setText(provins);
                    tprovinsi.setText("Provinsi");

                    JSONObject data = object.getJSONObject("data");
                    String nopol = data.getString("nopol");
                    plat.setText(nopol);


                    JSONObject kendaraan = data.getJSONObject("kendaraan");
                    String jtype = kendaraan.getString("type");
                    String jjenis = kendaraan.getString("jenis");
                    String mmerk = kendaraan.getString("merk");
                    String jtahun = kendaraan.getString("tahun_pembuatan");
                    String wwarna = kendaraan.getString("warna");
                    String wwilayah = kendaraan.getString("wilayah");

                    tjenis.setText("Jenis Kendaraan");
                    jenis.setText(jjenis);

                    tmerk.setText("Merk Kendaraan");
                    merk.setText(mmerk);

                    ttype.setText("Tipe Kendaraan");
                    type.setText(jtype);

                    ttahun.setText("Tahun Pembuatan");
                    tahun.setText(jtahun);

                    twarna.setText("Warna Kendaraan");
                    warna.setText(wwarna);

                    twilayah.setText("Wilayah");
                    wilayah.setText(wwilayah);

                    JSONObject pkb = data.getJSONObject("pkb");
                    String jjumlah = pkb.getString("jumlah");
                    String jjatuh = pkb.getString("jatuh_tempo");

                    tpkb.setText("PAJAK KENDARAAN :");
                    tjumlah.setText("Jumlah");
                    jumlah.setText(jjumlah);

                    tjatuh.setText("Jatuh Tempo");
                    jatuh.setText(jjatuh);

                    JSONObject biaya = data.getJSONObject("biaya");
                    String balikname = biaya.getString("balik_nama");
                    String jasa = biaya.getString("jasa_raharja");

                    tbiaya.setText("BIAYA :");
                    tbaliknama.setText("Balik Nama");
                    baliknama.setText(balikname);
                    tjasa.setText("Jasa Raharja");
                    jasaraharja.setText(jasa);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("DATA", error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    public void onClick(View v) {
        if (v == btn) {

            String plat = nomorplat.getText().toString();
            String kode = kodeplat.getText().toString();
            String seri = seriplat.getText().toString();

            if (plat.isEmpty() || kode.isEmpty() || seri.isEmpty()){
                Toast.makeText(getApplicationContext(),"Anda tidak mengisi dengan benar", Toast.LENGTH_SHORT).show();

            }
            try {
                getData(kode, plat, seri);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Koneksi Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

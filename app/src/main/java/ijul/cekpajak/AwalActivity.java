package ijul.cekpajak;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by afrijal on 27/12/16.
 */

public class AwalActivity extends Fragment implements View.OnClickListener {
    private Button btn;
    private EditText nomorplat, kodeplat, seriplat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        v = inflater.inflate(R.layout.activity_awal, container, false);

        btn = (Button)v.findViewById(R.id.btn);
        btn.setOnClickListener(this);

        nomorplat = (EditText)v.findViewById(R.id.nomerplat);
        kodeplat = (EditText)v.findViewById(R.id.kodeplat);
        seriplat = (EditText)v.findViewById(R.id.seriplat);

        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == btn){
            String plat = nomorplat.getText().toString();
            String kode = kodeplat.getText().toString();
            String seri = seriplat.getText().toString();

            if (plat.isEmpty() || kode.isEmpty() || seri.isEmpty()){
                Toast.makeText(getActivity(),"Anda tidak mengisi dengan benar", Toast.LENGTH_SHORT).show();
            }
            else {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                HasilActivity hasilActivity = new HasilActivity();
                ft.replace(R.id.frame, hasilActivity);
                Bundle bundle = new Bundle();
                hasilActivity.setArguments(bundle);
                ft.addToBackStack(null);
                ft.commit();

                try {

                    getData(kode, plat, seri);
                }

                catch (Exception e) {
                    Toast.makeText(getActivity(), "Koneksi Error", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    private void getData(String kodeplat, String nomorplat, String seriplat){
        String URL = "http://ibacor.com/api/pajak-kendaraan?kode="+kodeplat+"&nomor="+nomorplat+"&seri="+seriplat;
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("DATA", response.toString());
                try {

                    JSONObject object = new JSONObject(response.toString());
                    String provins = object.getString("provinsi");

                    JSONObject data = object.getJSONObject("data");
                    String nopol = data.getString("nopol");

                    JSONObject kendaraan = data.getJSONObject("kendaraan");
                    String jtype = kendaraan.getString("type");
                    String jjenis = kendaraan.getString("jenis");
                    String mmerk = kendaraan.getString("merk");
                    String jtahun = kendaraan.getString("tahun_pembuatan");
                    String wwarna = kendaraan.getString("warna");
                    String wwilayah = kendaraan.getString("wilayah");

                    JSONObject pkb = data.getJSONObject("pkb");
                    String jjumlah = pkb.getString("jumlah");
                    String jjatuh = pkb.getString("jatuh_tempo");


                    JSONObject biaya = data.getJSONObject("biaya");
                    String balikname = biaya.getString("balik_nama");
                    String jasa = biaya.getString("jasa_raharja");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("DATA", error.toString());
                Toast.makeText(getActivity(),"Error Silahkan Coba dan Periksa Kembali",Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }
}

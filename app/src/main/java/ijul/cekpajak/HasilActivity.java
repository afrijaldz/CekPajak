package ijul.cekpajak;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HasilActivity extends Fragment {
    private TextView plat, provinsi, jenis, merk, type, tahun, warna, wilayah, jumlah, jatuh, baliknama, jasaraharja;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        v =  inflater.inflate(R.layout.activity_hasil, container, false);

        // hasil
        plat = (TextView)v.findViewById(R.id.plat);
        provinsi = (TextView)v.findViewById(R.id.provinsi);
        jenis = (TextView)v.findViewById(R.id.jenis);
        merk = (TextView)v.findViewById(R.id.merk);
        type = (TextView)v.findViewById(R.id.type);
        tahun = (TextView)v.findViewById(R.id.tahun);
        warna = (TextView)v.findViewById(R.id.warna);
        wilayah = (TextView)v.findViewById(R.id.wilayah);
        jumlah = (TextView)v.findViewById(R.id.jumlah);
        jatuh = (TextView)v.findViewById(R.id.jatuh);
        baliknama = (TextView)v.findViewById(R.id.baliknama);
        jasaraharja = (TextView)v.findViewById(R.id.jasaraharja);

        provinsi.setText(provins);
        plat.setText(nopol);
        jenis.setText(jjenis);
        merk.setText(mmerk);
        type.setText(jtype);
        tahun.setText(jtahun);
        warna.setText(wwarna);
        wilayah.setText(wwilayah);
        jumlah.setText(jjumlah);
        jatuh.setText(jjatuh);
        baliknama.setText(balikname);
        jasaraharja.setText(jasa);
        return v;
    }

}
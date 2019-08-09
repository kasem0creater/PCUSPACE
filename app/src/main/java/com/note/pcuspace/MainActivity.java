package com.note.pcuspace;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView cardView1,cardView2,cardView3,cardView4,cardView5;
    private AdView adView1,adView2,adView3,adView4,adView5;
    private Button btn_getSpeedCPU,btn_getNumber_Core,btn_totalMemory,btn_Archit,btn_ram_letf;
    private Button btn_hardware,btn_screen_size,btn_get_info,btn_process,btn_total_ram;
    private Space space;
    private  TextView txt_Speed_cpu,txt_number_core,txt_totalMemory,txt_Archit,txt_process,txt_total_ram,txt_CPU_Info;
    private TextView txt_hardware,txt_screen_size,txt_android_name,txt_version,txt_memory_letf,txt_serial,txt_ram_letf;

    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        space = new Space(getApplicationContext());



        cardView1 =findViewById(R.id.card1);
        cardView2 =findViewById(R.id.card2);
        cardView3 =findViewById(R.id.card3);
        cardView4 =findViewById(R.id.card4);
        cardView5 =findViewById(R.id.card5);

        //adview instance
        adView1 = findViewById(R.id.adView1);
        adView2 = findViewById(R.id.adView2);
        adView3 = findViewById(R.id.adView3);
        adView4 = findViewById(R.id.adView4);
        adView5 = findViewById(R.id.adView5);


        //Button
        btn_getSpeedCPU =findViewById(R.id.btn_cpu_speed);
        btn_getNumber_Core =findViewById(R.id.btn_number_core);
        btn_totalMemory =findViewById(R.id.btn_totalMemory);
        btn_Archit =findViewById(R.id.btn_Archit);
        btn_hardware =findViewById(R.id.btn_hardware);
        btn_screen_size =findViewById(R.id.btn_screen_size);
        btn_get_info =findViewById(R.id.btn_get_into_tocard);
        btn_process =findViewById(R.id.btn_Process);
        btn_total_ram =findViewById(R.id.btn_Total_Ram);
        btn_ram_letf =findViewById(R.id.btn_Ram_letf);



        //TextView
        txt_number_core =findViewById(R.id.txt_number_core);
        txt_Speed_cpu =findViewById(R.id.txt_android_cpu_speed);
        txt_totalMemory =findViewById(R.id.txt_totalMemory);
        txt_Archit =findViewById(R.id.txt_Archit);
        txt_hardware =findViewById(R.id.txt_hardwaer);
        txt_screen_size =findViewById(R.id.txt_screen_size);
        txt_version =findViewById(R.id.txt_android_version);
        txt_android_name =findViewById(R.id.txt_android_name);
        txt_memory_letf =findViewById(R.id.txt_android_memory_letf);
        txt_serial =findViewById(R.id.txt_android_serial_number);
        txt_process =findViewById(R.id.txt_Process);
        txt_total_ram =findViewById(R.id.txt_Total_ram);
        txt_ram_letf =findViewById(R.id.txt_Ram_letf);
        txt_CPU_Info =findViewById(R.id.txt_CPU_info);


        //setbackground color cardView
        cardView1.setBackgroundResource(R.drawable.cardcolor1);
        cardView2.setBackgroundResource(R.drawable.cardcolor2);
        cardView3.setBackgroundResource(R.drawable.cardcolor3);
        cardView4.setBackgroundResource(R.drawable.cardcolor4);
        cardView5.setBackgroundResource(R.drawable.cardcolor3);

        //ad mobe
        MobileAds.initialize(this, "ca-app-pub-7633958771517626~9774885927");
        Location location = new Location("AdMobProvider");
        location.setLatitude(13.543296);
        location.setLatitude(100.924562);

        AdRequest.Builder adBuilder = new AdRequest.Builder();
        adBuilder.setLocation(location);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-7633958771517626/4023209251");
        AdRequest.Builder builder = new AdRequest.Builder();
        builder.setLocation(location);

        AdRequest interstitialAd_adBuilder =builder.build();
        interstitialAd.loadAd(interstitialAd_adBuilder);
       // interstitialAd.loadAd(new AdRequest.Builder().build());


        AdRequest adRequest = adBuilder.build();
        adView1.loadAd(adRequest);
        adView2.loadAd(adRequest);
        adView3.loadAd(adRequest);
        adView4.loadAd(adRequest);
        adView5.loadAd(adRequest);


        btn_getSpeedCPU.setOnClickListener(this);
        btn_getNumber_Core.setOnClickListener(this);
        btn_totalMemory.setOnClickListener(this);
        btn_Archit.setOnClickListener(this);
        btn_hardware.setOnClickListener(this);
        btn_screen_size.setOnClickListener(this);
        btn_get_info.setOnClickListener(this);
        btn_process.setOnClickListener(this);
        btn_total_ram.setOnClickListener(this);
        btn_ram_letf.setOnClickListener(this);


        interstitialAd.setAdListener(new AdListener()
        {
            @Override
            public void onAdClosed() {
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public  void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_cpu_speed:
                try { txt_Speed_cpu.setText(String.format("Model :" + "\n"+space.getCPU()+"")); }
                catch (Exception e){
                    Toast.makeText(view.getContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();}
                break;
            case R.id.btn_number_core:
                txt_number_core.setText("Number Core :"+"\n"+space.getNumberCore()+"");
                break;
            case R.id.btn_totalMemory:
                txt_totalMemory.setText("Total Memory :"+"\n"+space.getTotalMemory()+"");
                break;
            case R.id.btn_Archit:
                txt_Archit.setText("Architectures:"+"\n"+space.getArchitectures()+"");
                break;
            case R.id.btn_hardware:
                txt_hardware.setText("Hardware :"+"\n"+space.getHardware()+"");
                break;
            case R.id.btn_screen_size:
                txt_screen_size.setText("Screen Size :"+"\n"+space.getScreenSize()+"");
                break;
            case R.id.btn_get_into_tocard:
                    if(interstitialAd.isLoaded()) {
                        interstitialAd.show();
                    }
            txt_android_name.setText("Android Name :" + "\n" + space.getName() + "");
            txt_version.setText("Android Version :" + "\n" + space.getVersion() + "");
            txt_memory_letf.setText("Momey Letf :" + "\n" + space.getMemoryStatus());
            txt_serial.setText("Serial Number :" + "\n" + space.getSerial());
                txt_CPU_Info.setTextColor(R.color.textcolor);
            txt_CPU_Info.setText("CPU INFO :"+"\n"+space.getCPUinfo());
                 break;
            case R.id.btn_Process:
                txt_process.setText("Process :"+"\n"+space.getProcess()+"");
                break;
            case R.id.btn_Total_Ram:
                txt_total_ram.setText("Ram Size :"+"\n"+space.getTotalRam()+"");
                break;
            case R.id.btn_Ram_letf:
                if(interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }
                txt_ram_letf.setText("Ram Letf :"+"\n"+space.getRamLetf()+"");
                break;
                default:
                    break;

        }

    }
}

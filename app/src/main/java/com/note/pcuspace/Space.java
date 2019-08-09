package com.note.pcuspace;


import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.os.Build.*;


public class Space
{

    private Context context;


    public Space(Context context)
    {
        this.context = context;
    }


public String getSerial()
{
    return  SERIAL;
}

    public String getVersion()
    {

        return VERSION.SDK_INT+"";
    }
    public String getName()
    {

        return MANUFACTURER + " " + MODEL;
    }
    public String getScreenSize()
    {
        DisplayMetrics displayMetrics =new DisplayMetrics();
        return Resources.getSystem().getDisplayMetrics().widthPixels+" X "+ Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public String getHardware()
    {
        return HARDWARE;
    }
    public String getCPU()
    {
     return MODEL;
    }

    public String getArchitectures()
    {
      return CPU_ABI+" "+ CPU_ABI2+" "+System.getProperty("os.arch");
    }


    @RequiresApi(api = VERSION_CODES.JELLY_BEAN)
    public long getTotalMemory()
    {
        ActivityManager actManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        actManager.getMemoryInfo(memInfo);
        long totalMemory = memInfo.totalMem;

        return totalMemory;
    }


    public long getMemoryStatus()
    {
        return Runtime.getRuntime().freeMemory();
    }

    public int getNumberCore()
    {
        try
        {

            int i = new File("/sys/devices/system/cpu/").listFiles(new FileFilter()
           {
                public boolean accept(File params)
               {
                  return Pattern.matches("cpu[0-9]", params.getName());
              }}).length;
            return i;
        }
        catch (Exception e)
        {
            Toast.makeText(context.getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
        return 1;
    }

    public int getProcess()
    {
        return Runtime.getRuntime().availableProcessors();
    }

    public String getTotalRam()
    {
        RandomAccessFile reader = null;
        String load = null;
        DecimalFormat twoDecimalForm = new DecimalFormat("#.##");
        double totalRam = 0;
        String lastValue = "";
        try {
            reader = new RandomAccessFile("/proc/meminfo", "r");
            load = reader.readLine();

            // Get the Number value from the string
            Pattern p = Pattern.compile("(\\d+)");
            Matcher m = p.matcher(load);
            String value = "";
            while (m.find()) {
                value = m.group(1);
                // System.out.println("Ram : " + value);
            }
            reader.close();

            totalRam = Double.parseDouble(value);
            // totRam = totRam / 1024;

            double mb = totalRam / 1024.0;
            double gb = totalRam / 1048576.0;
            double tb = totalRam / 1073741824.0;

            if (tb > 1) {
                lastValue = twoDecimalForm.format(tb).concat(" TB");
            } else if (gb > 1) {
                lastValue = twoDecimalForm.format(gb).concat(" GB");
            } else if (mb > 1) {
                lastValue = twoDecimalForm.format(mb).concat(" MB");
            } else {
                lastValue = twoDecimalForm.format(totalRam).concat(" KB");
            }



        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            // Streams.close(reader);
        }

        return lastValue;
    }
    public long getRamLetf()
    {
        return Runtime.getRuntime().freeMemory();
    }
    public String getCPUinfo()
    {
        ProcessBuilder cmd;
        String result="";

        try{
            String[] args = {"/system/bin/cat", "/proc/cpuinfo"};
            cmd = new ProcessBuilder(args);

            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[1024];
            while(in.read(re) != -1){
                System.out.println(new String(re));
                result = result + new String(re);
            }
            in.close();
        } catch(IOException ex){
            ex.printStackTrace();
        }
        return result;
    }

}

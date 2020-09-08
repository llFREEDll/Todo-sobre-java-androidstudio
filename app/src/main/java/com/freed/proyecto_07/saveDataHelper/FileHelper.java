package com.freed.proyecto_07.saveDataHelper;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileHelper {
    private final String FILE_NAME = "mydata.txt";
    private Context context;
    public FileHelper(Context context){
        this.context = context;

    }
    public boolean saveData(String data){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(FILE_NAME,context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            return true;
        }catch (FileNotFoundException f){
            f.printStackTrace();
            return false;
        }catch (IOException f){
            f.printStackTrace();
            return false;
        }finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public String readData(){

        FileInputStream fileInputStream = null;
        String mydata = "" , aux = " ";
        try {

            fileInputStream = context.openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while ((mydata = bufferedReader.readLine()) != null){
                mydata += "\n";
                stringBuilder.append(mydata).append("\n");
                Log.e("datos", stringBuilder.toString());
            }
            return stringBuilder.toString();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
            return " ";

        }catch (IOException ex){
            ex.printStackTrace();
            return " ";
        }

    }
}

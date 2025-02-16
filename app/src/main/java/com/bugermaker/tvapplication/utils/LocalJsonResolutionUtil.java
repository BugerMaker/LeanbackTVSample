package com.bugermaker.tvapplication.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LocalJsonResolutionUtil {

    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();

        AssetManager manager = context.getAssets();

        BufferedReader br = null;
        try{
            br = new BufferedReader(new InputStreamReader(manager.open(fileName) , "utf-8"));
            String temp = "";
            while((temp = br.readLine()) != null){
                stringBuilder.append(temp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return stringBuilder.toString();
    }

    public static <T> T JsonToObject(String json, Class<T> type){
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }
}

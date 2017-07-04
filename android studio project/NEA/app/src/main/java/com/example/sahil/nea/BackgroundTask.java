package com.example.sahil.nea;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Sahil on 20-04-2017.
 */
public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;
    BackgroundTask(Context ctx){
        this.ctx = ctx;
    }
    ProgressDialog dialog;
    String json_string;

    @Override
    protected void onPreExecute() {
        dialog=new ProgressDialog((Activity)ctx);
        dialog.setMessage("Getting Data From network...");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {


        String JSON_STRING;
        String query_url = "http://192.168.43.160/NEA/query.php";
        String fetch_url = "http://192.168.43.160/NEA/fetch_data.php";
        String method = params[0];
        if (method.equals("query_message")) {
            String query_type = params[1];
            String time = params[2];

            if(time.equals("latest")) {
                try {
                    URL url = new URL(query_url);
                    HttpURLConnection httpurlConnection = (HttpURLConnection) url.openConnection();
                    httpurlConnection.setRequestMethod("POST");
                    httpurlConnection.setDoOutput(true);
                    OutputStream OS = httpurlConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("query_type", "UTF-8") + "=" + URLEncoder.encode(query_type, "UTF-8") + "&" + URLEncoder.encode("time", "UTF-8") + "=" + URLEncoder.encode(time, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpurlConnection.getInputStream();
                    IS.close();
                    //return "Fetching data from network.....";

                    try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);}

                    URL url3 = new URL(fetch_url);
                    HttpURLConnection httpurlConnection3 = (HttpURLConnection) url3.openConnection();
                    httpurlConnection3.setRequestMethod("POST");
                    httpurlConnection3.setDoOutput(true);
                    OutputStream OS3 = httpurlConnection3.getOutputStream();
                    BufferedWriter bufferedWriter3 = new BufferedWriter(new OutputStreamWriter(OS3, "UTF-8"));
                    String data3 = URLEncoder.encode("query_type", "UTF-8") + "=" + URLEncoder.encode(query_type, "UTF-8") + "&" + URLEncoder.encode("time", "UTF-8") + "=" + URLEncoder.encode(time, "UTF-8");
                    bufferedWriter3.write(data3);
                    bufferedWriter3.flush();
                    bufferedWriter3.close();
                    OS3.close();

                  //  URL url3 = new URL(fetch_url);
                   // HttpURLConnection httpurlConnection3 = (HttpURLConnection) url3.openConnection();
                    InputStream inputStream3 = httpurlConnection3.getInputStream();
                    BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(inputStream3));
                    StringBuilder stringBuilder3 = new StringBuilder();
                    while((JSON_STRING = bufferedReader3.readLine())!= null){

                        stringBuilder3.append(JSON_STRING+"\n");
                    }
                    bufferedReader3.close();
                    inputStream3.close();
                    httpurlConnection3.disconnect();
                    return stringBuilder3.toString().trim();


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{

                try {
                    URL url2 = new URL(fetch_url);
                    HttpURLConnection httpurlConnection2 = (HttpURLConnection) url2.openConnection();

                    httpurlConnection2.setRequestMethod("POST");
                    httpurlConnection2.setDoOutput(true);
                    OutputStream OS2 = httpurlConnection2.getOutputStream();
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(OS2, "UTF-8"));
                    String data2 = URLEncoder.encode("query_type", "UTF-8") + "=" + URLEncoder.encode(query_type, "UTF-8") + "&" + URLEncoder.encode("time", "UTF-8") + "=" + URLEncoder.encode(time, "UTF-8");
                    bufferedWriter2.write(data2);
                    bufferedWriter2.flush();
                    bufferedWriter2.close();
                    OS2.close();

                    InputStream inputStream2 = httpurlConnection2.getInputStream();
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2));
                    StringBuilder stringBuilder2 = new StringBuilder();
                    while((JSON_STRING = bufferedReader2.readLine())!= null){

                        stringBuilder2.append(JSON_STRING+"\n");
                    }
                    bufferedReader2.close();
                    inputStream2.close();
                    httpurlConnection2.disconnect();
                    return stringBuilder2.toString().trim();



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        Activity y= (Activity) ctx;
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        TextView textView = (TextView)y.findViewById(R.id.textView3);
        textView.setText(result);
        json_string = result;

        Intent intent3 = new Intent(ctx,DisplayListView.class);
        intent3.putExtra("json_data",json_string);
        ctx.startActivity(intent3);


//        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }


}

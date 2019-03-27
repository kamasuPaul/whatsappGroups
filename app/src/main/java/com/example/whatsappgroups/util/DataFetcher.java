package com.example.whatsappgroups.util;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.whatsappgroups.models.group;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataFetcher {
    public static List<group> fetch(final Context context, String url){
        final List<group> s = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>(){

                    /**
                     * Called when a response is received.
                     *
                     * @param response
                     */
                    @Override
                    public void onResponse(String response) {
                        s.add(new group("english learn","learn english in 2 days","kdkdkdk"));
                        Toast.makeText(context,"finished",Toast.LENGTH_SHORT).show();


                    }
                },
                new Response.ErrorListener(){
                    /**
                     * Callback method that an error has been occurred with the provided error code and optional
                     * user-readable message.
                     *
                     * @param error
                     */
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        s.add(new group("english learn","learn english in 2 days","kdkdkdk"));
                        Toast.makeText(context,"err",Toast.LENGTH_SHORT).show();

                    }
                });
        requestQueue.add(stringRequest);
        return  s;
    }
}

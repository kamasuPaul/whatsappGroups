package com.softAppsUganda.WhatsappGroups;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.softAppsUganda.WhatsappGroups.R;
import com.softAppsUganda.WhatsappGroups.adapters.recyclerAdapter;
import com.softAppsUganda.WhatsappGroups.models.group;
import com.softAppsUganda.WhatsappGroups.util.MyWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
     public  List<group> groupsList;
    RecyclerView v;
    ProgressBar progressBar;
    public String URL = "https://groupouts.com/";
    private OnFragmentInteractionListener mListener;
    public recyclerAdapter recyViewAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        groupsList = new ArrayList<>();
        groupsList.add(new group("masterJavaprogramming","i love java","kdkdk"));
        groupsList.add(new group("master ai ","java","kdkdk"));
        recyViewAdapter = new recyclerAdapter(groupsList);
        recyclerAdapter.ClickListener listener = new recyclerAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Toast.makeText(getContext(),"hi",Toast.LENGTH_SHORT);
                Toast.makeText(getContext(),groupsList.get(position).getGrpName(),Toast.LENGTH_SHORT).show();
            }
        };
        recyViewAdapter.setOnItemClickListener(listener);
            //TODO fetch data from the database properly;
//        fetchDeta("http://whatsappgroups.epizy.com/whatsappgroups/fetch_course_units_from_db.php?");
//        List<group> d = DataFetcher.fetch(getContext(),"dkkd");
//        for(group g: d){
//            groupsList.add(g);
//        }
//        recyViewAdapter.notifyDataSetChanged();//notify achange in data
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View parent = inflater.inflate(R.layout.fragment_home, container, false);
       v = parent.findViewById(R.id.RecylerViewHome);
        v.setLayoutManager(new LinearLayoutManager(getContext()));
        v.setAdapter(recyViewAdapter);
        v.setVisibility(View.GONE);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(v.getContext(),
                DividerItemDecoration.VERTICAL);
        v.addItemDecoration(dividerItemDecoration);//code for adding dividers but layout manager
        WebView webView =  parent.findViewById(R.id.webViewHome);
        progressBar = parent.findViewById(R.id.progressBarHome);
        progressBar.setVisibility(View.VISIBLE);


        // Enable Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(new MyWebView(getContext(),progressBar));
        webView.loadUrl(URL);
        return parent;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//        search(searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyViewAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyViewAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    public void fetchDeta(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>(){

                    /**
                     * Called when a response is received.
                     *
                     * @param response
                     */
                    @Override
                    public void onResponse(String response) {
                        groupsList.add(new group("english learn","learn english in 2 days","kdkdkdk"));
                        Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();
                        Log.d("kenya",response);

                        try {
                            JSONArray root = new JSONArray(response);
                            for(int i =0;i<root.length();i++)
                            {
                                JSONObject row = root.getJSONObject(i);
                                groupsList.add(new group("english learn","learn english in 2 days","kdkdkdk"));
                            }
//                            progress.dismiss();
                            recyViewAdapter.notifyDataSetChanged();//refresh recycler view

                        } catch (JSONException e) {
                            e.getMessage();
                            e.printStackTrace();
                        }

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
//                        groupsList.add(new group("english learn","learn english in 2 days","kdkdkdk"));
                        Toast.makeText(getContext(),"err",Toast.LENGTH_SHORT).show();

                    }
                });
        requestQueue.add(stringRequest);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}

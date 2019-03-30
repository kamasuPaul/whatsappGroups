package com.example.whatsappgroups.adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.whatsappgroups.MyItemRecyclerViewAdapter;
import com.example.whatsappgroups.R;
import com.example.whatsappgroups.models.group;

import java.util.ArrayList;
import java.util.List;
import com.example.whatsappgroups.HomeFragment.*;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ViewHolder>
                                        implements Filterable {
    List<group>groupsList;
    List<group>filteredGroups;

public recyclerAdapter(List g){
    groupsList = g;
    filteredGroups = new ArrayList<>(g);
}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.group_row, viewGroup, false);
        return new recyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        group item = groupsList.get(i);
        int color = ColorGenerator.MATERIAL.getRandomColor();
        TextDrawable.IBuilder d = TextDrawable.builder()
                .beginConfig()
                .withBorder(4)
                .endConfig()
                .round();
        TextDrawable drawable = d.build(Character.toString(item.getGrpName().charAt(0)).toUpperCase(),color);
        viewHolder.imageView.setImageDrawable(drawable);
        viewHolder.grpDescp.setText(item.getGrpDscp());
        viewHolder.grpname.setText(item.getGrpName());
    }

    @Override
    public int getItemCount() {
        return groupsList.size();
    }

    /**
     * <p>Returns a filter that can be used to constrain data with a filtering
     * pattern.</p>
     *
     * <p>This method is usually implemented by {@link Adapter}
     * classes.</p>
     *
     * @return a filter used to constrain data
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<group> filteredList = new ArrayList<>();
                String charString = constraint.toString();
                if(constraint == null || constraint.length() == 0){
                    filteredList.addAll(filteredGroups);
                    notifyDataSetChanged();
                }else{
                    for(group row: filteredGroups){
                        //add groups matching the query
                        if(row.getGrpName().toLowerCase().contains(charString.toLowerCase())
                                ||row.getGrpDscp().contains(charString.toLowerCase())){
                            filteredList.add(row);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                Log.d("RESULTS",filterResults.values.toString());

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                groupsList.clear();
                groupsList.addAll((ArrayList<group>)results.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView grpname,grpDescp;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            grpname = itemView.findViewById(R.id.txtViewGrpName);
            grpDescp = itemView.findViewById(R.id.txtViewDescrition);
            imageView = itemView.findViewById(R.id.group_image);

        }
    }
}

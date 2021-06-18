package com.example.termproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SectionRecyclerAdapter extends RecyclerView.Adapter<SectionRecyclerAdapter.sectionRecyclerHolder> {

    Context context;
    List<Section> sectionList;
    public SectionRecyclerAdapter(List<Section> sl){
        sectionList=sl;
    }

    @NonNull
    @Override
    public sectionRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        @SuppressLint("ViewHolder") View view = layoutInflater.inflate(R.layout.section_list_layout, parent, false);

        SectionRecyclerAdapter.sectionRecyclerHolder sectionRecyclerHolder = new SectionRecyclerAdapter.sectionRecyclerHolder(view);
        return sectionRecyclerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SectionRecyclerAdapter.sectionRecyclerHolder holder, int position) {
        holder.tvSectionItem.setText(sectionList.get(position).toString());
        String OldData = holder.tvSectionItem.getText().toString();

        holder.btnSectionView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SectionRUD.class);

                String si = sectionList.get(position).getSectionID();
                String s = sectionList.get(position).getSection();

                //Create the bundle
                Bundle bundle = new Bundle();

                //Add your data to bundle
                bundle.putString("ID", si);
                bundle.putString("section", s);

                //Add the bundle to the intent
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    public class sectionRecyclerHolder extends RecyclerView.ViewHolder {
        EditText tvSectionItem;
        Button btnSectionView;
        public sectionRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tvSectionItem = itemView.findViewById(R.id.tvSectionItem);
            btnSectionView = itemView.findViewById(R.id.btnSectionView);
        }
    }

}

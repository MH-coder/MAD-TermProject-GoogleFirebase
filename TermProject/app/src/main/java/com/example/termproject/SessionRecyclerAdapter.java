package com.example.termproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SessionRecyclerAdapter extends RecyclerView.Adapter<SessionRecyclerAdapter.sessionRecyclerHolder>{
    Context context;
    List<Session> sessionList;
    public SessionRecyclerAdapter(List<Session> sl){
        sessionList=sl;
    }

    @NonNull
    @Override
    public SessionRecyclerAdapter.sessionRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        @SuppressLint("ViewHolder") View view = layoutInflater.inflate(R.layout.session_list_layout, parent, false);

        SessionRecyclerAdapter.sessionRecyclerHolder sessionRecyclerHolder = new SessionRecyclerAdapter.sessionRecyclerHolder(view);
        return sessionRecyclerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SessionRecyclerAdapter.sessionRecyclerHolder holder, int position) {
        holder.tvSessionItem.setText(sessionList.get(position).toString());

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SessionRUD.class);

                String ID = sessionList.get(position).getSessionId();
                String title = sessionList.get(position).getSessionTitle();
                String desc = sessionList.get(position).getSessionDesc();
                String date = sessionList.get(position).getSessionDate();
                String time = sessionList.get(position).getSessionTime();

                //Create the bundle
                Bundle bundle = new Bundle();

                //Add your data to bundle
                bundle.putString("ID", ID);
                bundle.putString("title", title);
                bundle.putString("desc", desc);
                bundle.putString("date", date);
                bundle.putString("time", time);

                //Add the bundle to the intent
                intent.putExtras(bundle);
                Log.i("Result before", ID);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sessionList.size();
    }

    public class sessionRecyclerHolder extends RecyclerView.ViewHolder {
        EditText tvSessionItem;
        Button btnView;
        public sessionRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tvSessionItem = itemView.findViewById(R.id.tvSessionItem);
            btnView = itemView.findViewById(R.id.btnView);
        }
    }
}

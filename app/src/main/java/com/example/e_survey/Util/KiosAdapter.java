package com.example.e_survey.Util;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_survey.Activity.ScanActivity;
import com.example.e_survey.Model.Lokasi.Kios;
import com.example.e_survey.R;


import java.security.Permission;
import java.util.ArrayList;

public class KiosAdapter extends RecyclerView.Adapter<KiosAdapter.KiosViewHolder> {

    private RelativeLayout item_list_kios;
    private Context context;
    private ArrayList<Kios> listKios;

    public KiosAdapter(ArrayList<Kios> listKios) {
        this.listKios = listKios;
    }

    @Override
    public KiosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_kios, parent, false);
        return new KiosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KiosViewHolder holder, int position) {
        int noSem = position + 1;
        holder.tv_kios.setText(noSem + ". " + "KIOS: "+listKios.get(position).getNama_kios() + "\nDESA: " + listKios.get(position).getDesa());

    }

    @Override
    public int getItemCount() {
        return (listKios != null) ? listKios.size() : 0;
    }

    public class KiosViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_kios;

        public KiosViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();

            tv_kios = itemView.findViewById(R.id.tv_kios);
            item_list_kios = itemView.findViewById(R.id.item_list_kios);
            item_list_kios.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ScanActivity.class);
                    String coba= (String) tv_kios.getText();
                    intent.putExtra("nama", coba);
                    context.startActivity(intent);
                }
            });
        }
    }
}

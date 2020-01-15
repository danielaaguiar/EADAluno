package com.eda.eadaluno.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eda.eadaluno.R;
import com.eda.eadaluno.activity.aulas.ActivityLicoes;
import com.eda.eadaluno.model.Habilidade;

import java.util.ArrayList;

public class HabilidadeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "HabilidadeRecyclerViewAdapter";

    private ArrayList<Habilidade> mHabilidades = new ArrayList<>();
    private Context mContext;
    private int mSelectedHabilidadeIndex;

    public HabilidadeRecyclerViewAdapter(Context context, ArrayList<Habilidade> habilidades) {
        mHabilidades = habilidades;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_habilidade_list_item, parent, false);

        holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ViewHolder){
            ((ViewHolder)holder).nome.setText(mHabilidades.get(position).getNome());

//          SimpleDateFormat spf = new SimpleDateFormat("MMM dd, yyyy");
//            String date = spf.format(mHabilidades.get(position).getTimestamp());
//            ((ViewHolder)holder).timestamp.setText(date);
        }
    }

    @Override
    public int getItemCount() {
        return mHabilidades.size();
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener{

        TextView nome;

        public ViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome_habilidade);

            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }
        //Proxima activity
        public void onClick(View v) {
            mSelectedHabilidadeIndex = getAdapterPosition();
            Intent intent = new Intent(mContext, ActivityLicoes.class);
            intent.putExtra("habilidade_id",mHabilidades.get(mSelectedHabilidadeIndex).getHabilidade_id());
            mContext.startActivity(intent);
        }
        //Abre a dialog para modificar ou deletar
        public boolean onLongClick(View view) {
            mSelectedHabilidadeIndex = getAdapterPosition();
            return false;
        }


    }


}
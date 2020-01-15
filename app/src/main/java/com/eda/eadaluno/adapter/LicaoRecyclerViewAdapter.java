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
import com.eda.eadaluno.activity.aulas.ActivityLicao;
import com.eda.eadaluno.activity.aulas.ActivityLicoes;
import com.eda.eadaluno.model.Licao;

import java.util.ArrayList;

public class LicaoRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "LicaoRecyclerViewAdapter";
    public static final String KEY_LICAO_ID= "licao_ID";
    public static final String KEY_LICAO_DESCRICAO = "licao_descricao";
    public static final String KEY_LICAO_YOUTUBE = "licao_youtube";
    public static final String KEY_LICAO_PDF = "licao_pdf";

    private ArrayList<Licao> mLicoes = new ArrayList<>();
    private Context mContext;
    private int mSelectedLicaoIndex;

    public LicaoRecyclerViewAdapter(Context context, ArrayList<Licao> licoes) {
        mLicoes = licoes;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_licao_list_item, parent, false);

        holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ViewHolder){
            ((ViewHolder)holder).nome.setText(mLicoes.get(position).getNome());

        }
    }

    @Override
    public int getItemCount() {
        return mLicoes.size();
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        TextView nome;

        public ViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome_licao);

            //itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }
        //Proxima activity
        public void onClick(View v) {
            mSelectedLicaoIndex = getAdapterPosition();
            Intent intent = new Intent(mContext, ActivityLicao.class);
            intent.putExtra(KEY_LICAO_ID, mLicoes.get(mSelectedLicaoIndex).getLicao_id());
            intent.putExtra(KEY_LICAO_DESCRICAO, mLicoes.get(mSelectedLicaoIndex).getDescricao());
            intent.putExtra(KEY_LICAO_YOUTUBE, mLicoes.get(mSelectedLicaoIndex).getLinkYoutube());
            intent.putExtra(KEY_LICAO_PDF, mLicoes.get(mSelectedLicaoIndex).getLinkPDF());
            mContext.startActivity(intent);
        }
/*        //Abre a dialog para modificar ou deletar
        public boolean onLongClick(View view) {
            mSelectedLicaoIndex = getAdapterPosition();
            return false;
        }*/


    }


}
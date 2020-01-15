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
import com.eda.eadaluno.interfaceActivity.IQuestaoActivity;
import com.eda.eadaluno.model.Questao;

import java.util.ArrayList;

public class QuestaoRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "QuestaoRecyclerViewAdapter";

    private ArrayList<Questao> mQuestoes = new ArrayList<>();
    //private IQuestaoActivity mIQuestaoActivity;
    private Context mContext;
    private int mSelectedQuestaoIndex;

    public QuestaoRecyclerViewAdapter(Context context, ArrayList<Questao> questoes) {
        mQuestoes = questoes;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_questao_list_item, parent, false);

        holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ViewHolder){
            ((ViewHolder)holder).texto.setText(mQuestoes.get(position).getTexto());

        }
    }

    @Override
    public int getItemCount() {
        return mQuestoes.size();
    }



    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        //mIQuestaoActivity = (IQuestaoActivity) mContext;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView texto;

        public ViewHolder(View itemView) {
            super(itemView);
            texto = itemView.findViewById(R.id.questao_texto);

            //itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }
        //Proxima activity
        public void onClick(View v) {
            mSelectedQuestaoIndex = getAdapterPosition();
            //Intent intent = new Intent(mContext, ActivityHabilidade.class);
            //mContext.startActivity(intent);
        }
        //Abre a dialog para modificar ou deletar
        public boolean onLongClick(View view) {
            mSelectedQuestaoIndex = getAdapterPosition();
            //mIQuestaoActivity.onLongQuestaoSelected(mQuestoes.get(mSelectedQuestaoIndex));
            return false;
        }


    }


}
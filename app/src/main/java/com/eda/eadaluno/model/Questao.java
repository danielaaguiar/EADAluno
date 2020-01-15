package com.eda.eadaluno.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

//import com.google.firebase.firestore.IgnoreExtraProperties;
//import com.google.firebase.firestore.ServerTimestamp;

@IgnoreExtraProperties
public class Questao implements Parcelable {

    private String validacao;
    private String texto;
    private String pontuacao;
    private String link_imagem;
    private @ServerTimestamp
    Date timestamp;
    private String tarefa_id;
    private String questao_id;
    private  String usuario_id;
    private String licao_id;

    public Questao(String validacao, String texto, String pontuacao, String link_imagem, String tarefa_id, String questao_id, String usuario_id, Date timestamp, String licao_id) {
        this.validacao = validacao;
        this.texto = texto;
        this.pontuacao = pontuacao;
        this.link_imagem = link_imagem;
        this.tarefa_id = tarefa_id;
        this.questao_id = questao_id;
        this.usuario_id = usuario_id;
        this.timestamp = timestamp;
        this.licao_id = licao_id;
    }

    public Questao(Parcel in){

        validacao = in.readString();
        texto= in.readString();
        pontuacao= in.readString();
        link_imagem= in.readString();
        tarefa_id= in.readString();
        questao_id= in.readString();
        usuario_id= in.readString();
        licao_id = in.readString();

    }

    public Questao(){}

    public String getLicao_id() {
        return licao_id;
    }

    public void setLicao_id(String licao_id) {
        this.licao_id = licao_id;
    }

    public String getLink_imagem() {
        return link_imagem;
    }

    public void setLink_imagem(String link_imagem) {
        this.link_imagem = link_imagem;
    }

    public String getTarefa_id() {
        return tarefa_id;
    }

    public void setTarefa_id(String tarefa_id) {
        this.tarefa_id = tarefa_id;
    }

    public String getValidacao() {
        return validacao;
    }

    public void setValidacao(String validacao) {
        this.validacao = validacao;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(String pontuacao) {
        this.pontuacao = pontuacao;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


    public String getQuestao_id() {
        return questao_id;
    }

    public void setQuestao_id(String questao_id) {
        this.questao_id = questao_id;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(validacao);
        parcel.writeString(texto);
        parcel.writeString(pontuacao);
        parcel.writeString(link_imagem);
        parcel.writeString(tarefa_id);
        parcel.writeString(questao_id);
        parcel.writeString(usuario_id);
        parcel.writeString(licao_id);
    }

    public static final Creator<Questao> CREATOR = new Creator<Questao>() {
        @Override
        public Questao createFromParcel(Parcel in) {
            return new Questao(in);
        }

        @Override
        public Questao[] newArray(int size) {
            return new Questao[size];
        }
    };
}

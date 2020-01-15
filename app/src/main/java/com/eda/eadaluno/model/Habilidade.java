package com.eda.eadaluno.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

//import com.google.firebase.firestore.IgnoreExtraProperties;
//import com.google.firebase.firestore.ServerTimestamp;

@IgnoreExtraProperties
public class Habilidade implements Parcelable {

    private String nome;
    private String descricao;
    private String pontuacao;
    private @ServerTimestamp
    Date timestamp;
    private String habilidade_id;
    private  String usuario_id;
    private String numeroLicoes;

    public Habilidade(String nome, String descricao, String pontuacao, Date timestamp, String habilidade_id, String usuario_id, String numeroLicoes) {
        this.nome = nome;
        this.descricao = descricao;
        this.pontuacao = pontuacao;
        this.timestamp = timestamp;
        this.habilidade_id = habilidade_id;
        this.usuario_id = usuario_id;
        this.numeroLicoes = numeroLicoes;
    }

    public Habilidade(){}


    protected Habilidade(Parcel in){
        nome = in.readString();
        descricao = in.readString();
        pontuacao = in.readString();
        habilidade_id= in.readString();
        usuario_id= in.readString();
        numeroLicoes =in.readString();
    }

    public String getNumeroLicoes() {
        return numeroLicoes;
    }

    public void setNumeroLicoes(String numeroLicoes) {
        this.numeroLicoes = numeroLicoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getHabilidade_id() {
        return habilidade_id;
    }

    public void setHabilidade_id(String habilidade_id) {
        this.habilidade_id = habilidade_id;
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
        parcel.writeString(nome);
        parcel.writeString(descricao);
        parcel.writeString(pontuacao);
        parcel.writeString(habilidade_id);
        parcel.writeString(usuario_id);
        parcel.writeString(numeroLicoes);
    }

    public static final Creator<Habilidade> CREATOR = new Creator<Habilidade>() {
        @Override
        public Habilidade createFromParcel(Parcel in) {
            return new Habilidade(in);
        }

        @Override
        public Habilidade[] newArray(int size) {
            return new Habilidade[size];
        }
    };
}

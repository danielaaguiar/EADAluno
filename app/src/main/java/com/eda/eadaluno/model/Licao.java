package com.eda.eadaluno.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

//import com.google.firebase.firestore.IgnoreExtraProperties;
//import com.google.firebase.firestore.ServerTimestamp;


@IgnoreExtraProperties
public class Licao implements Parcelable {

    private String nome;
    private String descricao;
    private String pontuacao;
    private String linkYoutube;
    private String linkPDF;
    private @ServerTimestamp
    Date timestamp;
    private String habilidade_id;
    private String licao_id;
    private  String usuario_id;
    private String numeroTarefas;

    public Licao(String nome, String descricao, String pontuacao, String linkYoutube, String linkPDF, Date timestamp, String habilidade_id, String licao_id, String usuario_id, String numeroTarefas) {
        this.nome = nome;
        this.descricao = descricao;
        this.pontuacao = pontuacao;
        this.linkYoutube = linkYoutube;
        this.linkPDF = linkPDF;
        this.timestamp = timestamp;
        this.habilidade_id = habilidade_id;
        this.licao_id = licao_id;
        this.usuario_id = usuario_id;
        this.numeroTarefas =numeroTarefas;
    }

    public Licao(Parcel in){
        nome = in.readString();
        descricao = in.readString();
        pontuacao = in.readString();
        linkYoutube = in.readString();
        linkPDF = in.readString();
        habilidade_id = in.readString();
        licao_id = in.readString();
        usuario_id = in.readString();
        numeroTarefas = in.readString();

    }

    public Licao(){}

    public String getNumeroTarefas() {
        return numeroTarefas;
    }

    public void setNumeroTarefas(String numeroTarefas) {
        this.numeroTarefas = numeroTarefas;
    }

    public String getLinkYoutube() {
        return linkYoutube;
    }

    public void setLinkYoutube(String linkYoutube) {
        this.linkYoutube = linkYoutube;
    }

    public String getLinkPDF() {
        return linkPDF;
    }

    public void setLinkPDF(String linkPDF) {
        this.linkPDF = linkPDF;
    }

    public String getHabilidade_id() {
        return habilidade_id;
    }

    public void setHabilidade_id(String habilidade_id) {
        this.habilidade_id = habilidade_id;
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

    public String getLicao_id() {
        return licao_id;
    }

    public void setLicao_id(String licao_id) {
        this.licao_id = licao_id;
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
        parcel.writeString(linkYoutube);
        parcel.writeString(linkPDF);
        parcel.writeString(habilidade_id);
        parcel.writeString(licao_id);
        parcel.writeString(usuario_id);
        parcel.writeString(numeroTarefas);
    }

    public static final Creator<Licao> CREATOR = new Creator<Licao>() {
        @Override
        public Licao createFromParcel(Parcel in) {
            return new Licao(in);
        }

        @Override
        public Licao[] newArray(int size) { return new Licao[size];}
    };
}

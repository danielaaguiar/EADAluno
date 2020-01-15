package com.eda.eadaluno.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

//import com.google.firebase.firestore.IgnoreExtraProperties;
//import com.google.firebase.firestore.ServerTimestamp;

@IgnoreExtraProperties
public class Tarefa implements Parcelable {

    private String nome;
    private String enunciado;
    private String pontuacao;
    private String link_imagem;
    private String tipo;
    private String tempo;
    private @ServerTimestamp
    Date timestamp;
    private String licao_id;
    private String tarefa_id;
    private  String usuario_id;

    public Tarefa(String nome, String enunciado, String pontuacao, String link_imagem, String tipo, String tempo, Date timestamp, String licao_id, String tarefa_id, String usuario_id) {
        this.nome = nome;
        this.enunciado = enunciado;
        this.pontuacao = pontuacao;
        this.link_imagem = link_imagem;
        this.tipo = tipo;
        this.tempo = tempo;
        this.timestamp = timestamp;
        this.licao_id = licao_id;
        this.tarefa_id = tarefa_id;
        this.usuario_id = usuario_id;
    }

    public Tarefa (Parcel in) {
        nome = in.readString();
        enunciado = in.readString();
        pontuacao = in.readString();
        link_imagem = in.readString();
        tipo = in.readString();
        tempo = in.readString();
        licao_id = in.readString();
        tarefa_id = in.readString();
        usuario_id = in.readString();

    }

    public Tarefa(){}

    public String getLink_imagem() {
        return link_imagem;
    }

    public void setLink_imagem(String link_imagem) {
        this.link_imagem = link_imagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getLicao_id() {
        return licao_id;
    }

    public void setLicao_id(String licao_id) {
        this.licao_id = licao_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
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

    public String getTarefa_id() {
        return tarefa_id;
    }

    public void setTarefa_id(String tarefa_id) {
        this.tarefa_id = tarefa_id;
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
        parcel.writeString(enunciado);
        parcel.writeString(pontuacao);
        parcel.writeString(link_imagem);
        parcel.writeString(tipo);
        parcel.writeString(tempo);
        parcel.writeString(licao_id);
        parcel.writeString(tarefa_id);
        parcel.writeString(usuario_id);

    }

    public static final Creator<Tarefa> CREATOR = new Creator<Tarefa>() {
        @Override
        public Tarefa createFromParcel(Parcel in) {
            return new Tarefa(in);
        }

        @Override
        public Tarefa[] newArray(int size) {
            return new Tarefa[size];
        }
    };
}

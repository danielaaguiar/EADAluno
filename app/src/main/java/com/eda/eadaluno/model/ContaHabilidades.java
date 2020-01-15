package com.eda.eadaluno.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

//import com.google.firebase.firestore.IgnoreExtraProperties;
//import com.google.firebase.firestore.ServerTimestamp;

@IgnoreExtraProperties
public class ContaHabilidades implements Parcelable {

    private String numeroHabilidades;
    private String numeroHabilidadesId;
    private @ServerTimestamp
    Date timestamp;
    public ContaHabilidades(String numeroHabilidades,String numeroHabilidadesId, Date timestamp) {

        this.numeroHabilidades = numeroHabilidades;
        this.numeroHabilidadesId = numeroHabilidadesId;
        this.timestamp =timestamp;
    }

    public ContaHabilidades(){}


    protected ContaHabilidades(Parcel in){

        numeroHabilidades =in.readString();
        numeroHabilidadesId =in.readString();
    }

    public static final Creator<ContaHabilidades> CREATOR = new Creator<ContaHabilidades>() {
        @Override
        public ContaHabilidades createFromParcel(Parcel in) {
            return new ContaHabilidades(in);
        }

        @Override
        public ContaHabilidades[] newArray(int size) {
            return new ContaHabilidades[size];
        }
    };

    public String getNumeroHabilidades() {
        return numeroHabilidades;
    }

    public void setNumeroHabilidades(String numeroHabilidades) {
        this.numeroHabilidades = numeroHabilidades;

    }

    public String getNumeroHabilidadesId() {
        return numeroHabilidadesId;
    }

    public void setNumeroHabilidadesId(String numeroHabilidadesId) {
        this.numeroHabilidadesId = numeroHabilidadesId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(numeroHabilidades);
        parcel.writeString(numeroHabilidadesId);}
}

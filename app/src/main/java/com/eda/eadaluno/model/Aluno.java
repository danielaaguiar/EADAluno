package com.eda.eadaluno.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Aluno implements Parcelable {

    private String nome;
    private String matricula;
    private String scoreTotal;
    private String scoreHabilidade1;
    private String scoreHabilidade2;
    private String scoreHabilidade3;
    private String scoreHabilidade4;
    private String scoreHabilidade5;
    private String scoreHabilidade6;
    private String scoreHabilidade7;
    private String scoreHabilidade8;
    private String scoreHabilidade9;
    private String scoreHabilidade10;
    private String alunoid;


    protected Aluno(Parcel in) {
        nome = in.readString();
        matricula = in.readString();
        scoreTotal = in.readString();
        scoreHabilidade1 = in.readString();
        scoreHabilidade2 = in.readString();
        scoreHabilidade3 = in.readString();
        scoreHabilidade4 = in.readString();
        scoreHabilidade5 = in.readString();
        scoreHabilidade6 = in.readString();
        scoreHabilidade7 = in.readString();
        scoreHabilidade8 = in.readString();
        scoreHabilidade9 = in.readString();
        scoreHabilidade10 = in.readString();
        alunoid = in.readString();
    }

    public static final Creator<Aluno> CREATOR = new Creator<Aluno>() {
        @Override
        public Aluno createFromParcel(Parcel in) {
            return new Aluno(in);
        }

        @Override
        public Aluno[] newArray(int size) {
            return new Aluno[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeString(matricula);
        dest.writeString(scoreTotal);
        dest.writeString(scoreHabilidade1);
        dest.writeString(scoreHabilidade2);
        dest.writeString(scoreHabilidade3);
        dest.writeString(scoreHabilidade4);
        dest.writeString(scoreHabilidade5);
        dest.writeString(scoreHabilidade6);
        dest.writeString(scoreHabilidade7);
        dest.writeString(scoreHabilidade8);
        dest.writeString(scoreHabilidade9);
        dest.writeString(scoreHabilidade10);
        dest.writeString(alunoid);
    }

    public Aluno(String nome, String matricula, String scoreTotal, String scoreHabilidade1, String scoreHabilidade2, String scoreHabilidade3, String scoreHabilidade4, String scoreHabilidade5, String scoreHabilidade6, String scoreHabilidade7, String scoreHabilidade8, String scoreHabilidade9, String scoreHabilidade10, String alunoid) {
        this.nome = nome;
        this.matricula = matricula;
        this.scoreTotal = scoreTotal;
        this.scoreHabilidade1 = scoreHabilidade1;
        this.scoreHabilidade2 = scoreHabilidade2;
        this.scoreHabilidade3 = scoreHabilidade3;
        this.scoreHabilidade4 = scoreHabilidade4;
        this.scoreHabilidade5 = scoreHabilidade5;
        this.scoreHabilidade6 = scoreHabilidade6;
        this.scoreHabilidade7 = scoreHabilidade7;
        this.scoreHabilidade8 = scoreHabilidade8;
        this.scoreHabilidade9 = scoreHabilidade9;
        this.scoreHabilidade10 = scoreHabilidade10;
        this.alunoid = alunoid;
    }

    public Aluno() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(String scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public String getScoreHabilidade1() {
        return scoreHabilidade1;
    }

    public void setScoreHabilidade1(String scoreHabilidade1) {
        this.scoreHabilidade1 = scoreHabilidade1;
    }

    public String getScoreHabilidade2() {
        return scoreHabilidade2;
    }

    public void setScoreHabilidade2(String scoreHabilidade2) {
        this.scoreHabilidade2 = scoreHabilidade2;
    }

    public String getScoreHabilidade3() {
        return scoreHabilidade3;
    }

    public void setScoreHabilidade3(String scoreHabilidade3) {
        this.scoreHabilidade3 = scoreHabilidade3;
    }

    public String getScoreHabilidade4() {
        return scoreHabilidade4;
    }

    public void setScoreHabilidade4(String scoreHabilidade4) {
        this.scoreHabilidade4 = scoreHabilidade4;
    }

    public String getScoreHabilidade5() {
        return scoreHabilidade5;
    }

    public void setScoreHabilidade5(String scoreHabilidade5) {
        this.scoreHabilidade5 = scoreHabilidade5;
    }

    public String getScoreHabilidade6() {
        return scoreHabilidade6;
    }

    public void setScoreHabilidade6(String scoreHabilidade6) {
        this.scoreHabilidade6 = scoreHabilidade6;
    }

    public String getScoreHabilidade7() {
        return scoreHabilidade7;
    }

    public void setScoreHabilidade7(String scoreHabilidade7) {
        this.scoreHabilidade7 = scoreHabilidade7;
    }

    public String getScoreHabilidade8() {
        return scoreHabilidade8;
    }

    public void setScoreHabilidade8(String scoreHabilidade8) {
        this.scoreHabilidade8 = scoreHabilidade8;
    }

    public String getScoreHabilidade9() {
        return scoreHabilidade9;
    }

    public void setScoreHabilidade9(String scoreHabilidade9) {
        this.scoreHabilidade9 = scoreHabilidade9;
    }

    public String getScoreHabilidade10() {
        return scoreHabilidade10;
    }

    public void setScoreHabilidade10(String scoreHabilidade10) {
        this.scoreHabilidade10 = scoreHabilidade10;
    }

    public String getAlunoid() {
        return alunoid;
    }

    public void setAlunoid(String alunoid) {
        this.alunoid = alunoid;
    }

    public static Creator<Aluno> getCREATOR() {
        return CREATOR;
    }
}

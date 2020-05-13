package br.edu.unicsul.agendatelefonica.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
    private String id;
    private String nomecontato;
    private String email;
    private String endereco;
    private String genero;
    private String celular;

    public String getId() {
        return id;
    }

    public String getNomecontato() {
        return nomecontato;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getGenero() {
        return genero;
    }

    public String getCelular() {
        return celular;
    }

    public Contact(String id, String nomecontato, String email, String endereco, String genero, String celular) {
        this.id = id;
        this.nomecontato = nomecontato;
        this.email = email;
        this.endereco = endereco;
        this.genero = genero;
        this.celular = celular;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.nomecontato);
        dest.writeString(this.email);
        dest.writeString(this.endereco);
        dest.writeString(this.genero);
        dest.writeString(this.celular);
    }

    protected Contact(Parcel in) {
        this.id = in.readString();
        this.nomecontato = in.readString();
        this.email = in.readString();
        this.endereco = in.readString();
        this.genero = in.readString();
        this.celular = in.readString();
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}

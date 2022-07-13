package com.balius.filimo.model.login;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "tbl_account")
public class Login implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int accountId;

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("success")
    @Expose
    private String success;

    protected Login(Parcel in) {
        userId = in.readString();
        name = in.readString();
        email = in.readString();
        success = in.readString();
    }

    public Login(String userId, String name, String email, String success) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.success = success;
    }

    @Ignore
    public Login(int accountId, String userId, String name, String email, String success) {
        this.accountId = accountId;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.success = success;
    }

    public Login() {
    }

    public static final Creator<Login> CREATOR = new Creator<Login>() {
        @Override
        public Login createFromParcel(Parcel in) {
            return new Login(in);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userId);
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(success);
    }
}

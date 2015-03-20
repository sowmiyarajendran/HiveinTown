package com.androidexample.hiveintown;

import android.widget.ImageView;
import android.widget.RadioGroup;

/**
 * Created by sowmiya on 3/16/2015.
 */
public class HelperDatabase  {

    String Helper_Name,Helper_Age,Helper_PhoneNo,Helper_Address,Visiting_Appartments;
    RadioGroup Helper_Sex;
    ImageView Helper_Photo,Helper_AddressProof;
    public HelperDatabase(){}
    public HelperDatabase(String Name,String Age,RadioGroup Sex,String PhoneNo,String Address,String Visiting,ImageView Photo,ImageView Addproof){
        this.Helper_Name=Name;
        this.Helper_Age=Age;
        this.Helper_Sex=Sex;
        this.Helper_PhoneNo=PhoneNo;
        this.Helper_Address=Address;
        this.Visiting_Appartments=Visiting;
        this.Helper_Photo=Photo;
        this.Helper_AddressProof=Addproof;
    }
    //getters and setters


    public void setHelper_Name(String helper_Name) {
        this.Helper_Name = helper_Name;
    }

    public void setHelper_Age(String helper_Age) {
        this.Helper_Age = helper_Age;
    }

    public void setHelper_Sex(RadioGroup helper_Sex) {
        this.Helper_Sex = helper_Sex;
    }

    public void setHelper_PhoneNo(String helper_PhoneNo) {
        this.Helper_PhoneNo = helper_PhoneNo;
    }

    public void setHelper_Address(String helper_Address) {
        this.Helper_Address = helper_Address;
    }

    public void setVisiting_Appartments(String visiting_Appartments) {
        this.Visiting_Appartments = visiting_Appartments;
    }

    public void setHelper_Photo(ImageView helper_Photo) {
        this.Helper_Photo = helper_Photo;
    }

    public void setHelper_AddressProof(ImageView helper_AddressProof) {
        this.Helper_AddressProof = helper_AddressProof;
    }

    //getters


    public String getHelper_Name() {
        return this.Helper_Name;
    }

    public String getHelper_Age() {
        return this.Helper_Age;
    }

    public RadioGroup getHelper_Sex() {
        return this.Helper_Sex;
    }

    public String getHelper_Address() {
        return this.Helper_Address;
    }

    public String getHelper_PhoneNo() {
        return this.Helper_PhoneNo;
    }

    public String getVisiting_Appartments() {
        return this.Visiting_Appartments;
    }

    public ImageView getHelper_AddressProof() {
        return this.Helper_AddressProof;
    }

    public ImageView getHelper_Photo() {
        return this.Helper_Photo;
    }
}

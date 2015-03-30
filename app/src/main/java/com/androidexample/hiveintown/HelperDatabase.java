package com.androidexample.hiveintown;

/**
 * Created by sowmiya on 3/16/2015.
 */
public class HelperDatabase  {

    String Helper_Name,Helper_Age,Helper_PhoneNo,Helper_Address,Visiting_Appartments;
    String Helper_Sex,DocType1,DocType2;
    byte[] Helper_Photo,Helper_AddressProof,Identity_Proof;
    public HelperDatabase(){}
    public HelperDatabase(String Name,String Age,String Sex,String PhoneNo,String Address,String Visiting,byte[] Photo,String DocType1,byte[] Addproof,String DocType2,byte[] IdProof){
        this.Helper_Name=Name;
        this.Helper_Age=Age;
        this.Helper_Sex=Sex;
        this.Helper_PhoneNo=PhoneNo;
        this.Helper_Address=Address;
        this.Visiting_Appartments=Visiting;
        this.Helper_Photo=Photo;
        this.DocType1=DocType1;
        this.Helper_AddressProof=Addproof;
        this.DocType2=DocType2;
        this.Identity_Proof=IdProof;
    }
    //getters and setters


    public void setHelper_Name(String helper_Name) {
        this.Helper_Name = helper_Name;
    }

    public void setHelper_Age(String helper_Age) {
        this.Helper_Age = helper_Age;
    }

    public void setHelper_Sex(String helper_Sex) {
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

    public void setHelper_Photo(byte[] helper_Photo) {
        this.Helper_Photo = helper_Photo;
    }

    public void setHelper_AddressProof(byte[] helper_AddressProof) {
        this.Helper_AddressProof = helper_AddressProof;
    }

    public void setDocType1(String docType1) {
        this.DocType1 = docType1;
    }

    public void setDocType2(String docType2) {
        this.DocType2 = docType2;
    }

    public void setIdentity_Proof(byte[] identity_Proof) {
        this.Identity_Proof = identity_Proof;
    }
    //getters


    public String getHelper_Name() {
        return this.Helper_Name;
    }

    public String getHelper_Age() {
        return this.Helper_Age;
    }

    public String getHelper_Sex() {
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

    public byte[] getHelper_AddressProof() {
        return this.Helper_AddressProof;
    }

    public byte[] getHelper_Photo() {
        return this.Helper_Photo;
    }

    public byte[] getIdentity_Proof() {
        return this.Identity_Proof;
    }

    public String getDocType1() {
        return this.DocType1;
    }

    public String getDocType2() {
        return this.DocType2;
    }
}

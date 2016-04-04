package com.prolan.hm.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Prolan on 31/03/2016.
 */
public class Stores implements Parcelable {
    private String address;
    private String city;
    private String name;
    private String latitude;
    private String zipcode;
    private String storeLogoURL;
    private String phone;
    private String longitude;
    private String storeID;
    private String state;

    public static final Creator<Stores> CREATOR = new Creator<Stores>() {
        @Override
        public Stores createFromParcel(Parcel source) {
            return new Stores(source);
        }

        @Override
        public Stores[] newArray(int size) {
            return new Stores[size];
        }
    };


    /**
     *
     * @return
     * The address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     *
     * @param zipcode
     * The zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     *
     * @return
     * The storeLogoURL
     */
    public String getStoreLogoURL() {
        return storeLogoURL;
    }

    /**
     *
     * @param storeLogoURL
     * The storeLogoURL
     */
    public void setStoreLogoURL(String storeLogoURL) {
        this.storeLogoURL = storeLogoURL;
    }

    /**
     *
     * @return
     * The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The storeID
     */
    public String getStoreID() {
        return storeID;
    }

    /**
     *
     * @param storeID
     * The storeID
     */
    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    /**
     *
     * @return
     * The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     * The state
     */
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(address);
        dest.writeString(storeLogoURL);
        dest.writeString(city);
        dest.writeString(longitude);
        dest.writeString(latitude);
        dest.writeString(zipcode);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(storeID);
        dest.writeString(state);

    }

    public Stores(){

    }

    private Stores(Parcel in){
        address     = in.readString();
        storeID     = in.readString();
        city        = in.readString();
        longitude   = in.readString();
        latitude    = in.readString();
        zipcode     = in.readString();
        name        = in.readString();
        phone       = in.readString();
        storeID     = in.readString();
        state       = in.readString();
    }

    @Override
    public String toString() {
        return "Stores{" +
                "address='" + address + '\'' +
                ", storeID='" + storeID + '\'' +
                ", city='" + city + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", storeID='" + storeID + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}

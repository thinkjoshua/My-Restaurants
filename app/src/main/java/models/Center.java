package models;




        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;


public class Center {

    @SerializedName("latitude")
    @Expose
    private Float latitude;
    @SerializedName("longitude")
    @Expose
    private Float longitude;

    /**
     * No args constructor for use in serialization
     *
     */
    public Center() {
    }

    /**
     *
     * @param latitude
     * @param longitude
     */
    public Center(Float latitude, Float longitude) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

}
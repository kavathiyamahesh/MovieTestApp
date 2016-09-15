
package com.android.mayojava.movies.repository.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Thumb {

    @SerializedName("full")
    @Expose
    private String full;

    /**
     * 
     * @return
     *     The full
     */
    public String getFull() {
        return full;
    }

    /**
     * 
     * @param full
     *     The full
     */
    public void setFull(String full) {
        this.full = full;
    }
}

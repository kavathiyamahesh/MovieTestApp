
package com.android.mayojava.trivago.repository.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Ids {

    @SerializedName("trakt")
    @Expose
    private Integer trakt;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("imdb")
    @Expose
    private String imdb;
    @SerializedName("tmdb")
    @Expose
    private Integer tmdb;

    /**
     * 
     * @return
     *     The trakt
     */
    public Integer getTrakt() {
        return trakt;
    }

    /**
     * 
     * @param trakt
     *     The trakt
     */
    public void setTrakt(Integer trakt) {
        this.trakt = trakt;
    }

    /**
     * 
     * @return
     *     The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * 
     * @param slug
     *     The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * 
     * @return
     *     The imdb
     */
    public String getImdb() {
        return imdb;
    }

    /**
     * 
     * @param imdb
     *     The imdb
     */
    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    /**
     * 
     * @return
     *     The tmdb
     */
    public Integer getTmdb() {
        return tmdb;
    }

    /**
     * 
     * @param tmdb
     *     The tmdb
     */
    public void setTmdb(Integer tmdb) {
        this.tmdb = tmdb;
    }

}

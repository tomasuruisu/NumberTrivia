package com.thomas.numbertrivia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TriviaNumber {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("number")
    @Expose
    private Integer number;

    public TriviaNumber(Integer number, String text) {
        super();
        this.number = number;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
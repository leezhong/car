package com.leezhong.dto;

public class JsonRes {

    private int resCode;

    private String resString;

    private Object data;

    public int getResCode() {
        return resCode;
    }

    public JsonRes() {}

    public JsonRes(int resCode, String resString) {
        this.resCode = resCode;
        this.resString = resString;
    }

    public JsonRes(int resCode, String resString, Object data) {
        this.resCode = resCode;
        this.resString = resString;
        this.data = data;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResString() {
        return resString;
    }

    public void setResString(String resString) {
        this.resString = resString;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

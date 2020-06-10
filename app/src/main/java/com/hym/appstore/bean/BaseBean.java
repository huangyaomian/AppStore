package com.hym.appstore.bean;

import java.io.Serializable;
import java.util.List;

import retrofit2.http.PUT;

public class BaseBean<T> implements Serializable {

    public static final int  SUCCESS = 1;
    private int status;
    private String message;
    private T datas;


    public boolean success(){
        return (status == SUCCESS);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return datas;
    }

    public void setData(T datas) {
        this.datas = datas;
    }
}

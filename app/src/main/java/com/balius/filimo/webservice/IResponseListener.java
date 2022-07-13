package com.balius.filimo.webservice;

public interface IResponseListener <T>{
    public void onSuccess(T responseMessage);

    public void onFailure(String onErrorMessage);
}

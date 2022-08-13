package com.balius.filimo.webservice;

public interface IResponseListener {
    public void onSuccess(Object responseMessage);

    public void onFailure(String onErrorMessage);
}

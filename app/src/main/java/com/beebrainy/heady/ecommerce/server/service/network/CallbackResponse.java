package com.beebrainy.heady.ecommerce.server.service.network;

public interface CallbackResponse<T> {

    void onResponse(T t);


}

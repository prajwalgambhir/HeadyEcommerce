package com.beebrainy.heady.ecommerce.server.services.network;

public interface CallbackResponse<T> {

    void onResponse(T t);


}

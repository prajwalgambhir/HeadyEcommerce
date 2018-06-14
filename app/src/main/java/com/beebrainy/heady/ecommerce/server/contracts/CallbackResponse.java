package com.beebrainy.heady.ecommerce.server.contracts;

/**
 * Parameterized Callback interface.
 *
 * @param <T> - Expected output
 */
public interface CallbackResponse<T> {

    /**
     * Invoked for callback.
     *
     * @param t - Expected output
     */
    void onResponse(T t);

}

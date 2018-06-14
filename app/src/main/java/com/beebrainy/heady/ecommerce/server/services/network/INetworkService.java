package com.beebrainy.heady.ecommerce.server.services.network;

import com.beebrainy.heady.ecommerce.server.contracts.CallbackResponse;

/**
 * Used for network calls
 */
public interface INetworkService {

    /**
     * Makes a GET Request with specified URL.
     *
     * @param url              - URL
     * @param callbackResponse - Interface for callback response
     */
    void getRequest(String url, CallbackResponse callbackResponse);
}

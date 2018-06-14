package com.beebrainy.heady.ecommerce.client.listeners;

/**
 * Parameterized Interface for handling click events of list items.
 *
 * @param <T>
 */
public interface ItemClickListener<T> {

    void onItemClick(T t);
}

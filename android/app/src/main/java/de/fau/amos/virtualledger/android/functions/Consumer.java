package de.fau.amos.virtualledger.android.functions;

/**
 * Created by sebastian on 21.05.17.
 */

public interface Consumer<T> {

    void accept(T item);
}

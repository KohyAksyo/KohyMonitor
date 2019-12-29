package net.kohy.utils;

import java.util.function.Consumer;

public abstract class ArrayUtils {

    private ArrayUtils() {}

    public static <T> void executeArray(T[] objects, Consumer<T> consumer) {
        for(T object : objects) {
            consumer.accept(object);
        }
    }

    public static <T> boolean contains(T[] objects, T reference) {
        for(T object : objects) {
            if(object.equals(reference)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static <T, C> C[] castArray(T[] objects, C cast) {
        C[] outputArray = (C[]) new Object[objects.length];
        for(int i = 0; i < objects.length; i++) {
            outputArray[i] = (C) objects[i];
        }
        return outputArray;
    }

}

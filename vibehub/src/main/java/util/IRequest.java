package main.java.util;

/**
 * Created by artur on 04/04/2017.
 */
public interface IRequest {
    Iterable<String> getContent(String path);
}

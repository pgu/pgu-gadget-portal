package pgu.gadget.portal.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
    void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;

    void getGadgetUrls(AsyncCallback<ArrayList<String>> callback);

    void getServerDate(AsyncCallback<String> asyncCallback);
}

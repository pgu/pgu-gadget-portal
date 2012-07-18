package pgu.gadget.portal.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pgu.gadget.portal.client.GreetingService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    @Override
    public String greetServer(final String input) throws IllegalArgumentException {
        return "Hello, ";
    }

    @Override
    public ArrayList<String> getGadgetUrls() {
        final ArrayList<String> urls = new ArrayList<String>();
        urls.add("http://www.google.com/ig/modules/horoscope.xml");
        urls.add("http://www.google.com/ig/modules/eyes/eyes.xml");
        urls.add("http://www.google.com/ig/modules/builtin_weather.xml");
        urls.add("http://www.labpixies.com/campaigns/todo/todo.xml");
        urls.add("http://localhost:8080/pgu-gadget/pgu_gadget/Pgu_gadget.gadget.xml");
        return urls;
    }

    @Override
    public String getServerDate() {
        return new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date());
    }
}

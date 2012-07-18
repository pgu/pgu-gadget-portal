package pgu.gadget.portal.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Pgu_gadget_portal implements EntryPoint {

    private final GreetingServiceAsync service      = GWT.create(GreetingService.class);

    private static int                 count        = 0;
    private Timer                      timerShindig = new Timer() {

                                                        @Override
                                                        public void run() {
                                                            if (isShindigLoaded()) {
                                                                timerShindig.cancel();
                                                                timerShindig = null;

                                                                moduleLoad();
                                                            } else {
                                                                timerShindig.schedule(50);
                                                            }
                                                        }
                                                    };

    public static native boolean isShindigLoaded() /*-{
		return (typeof $wnd.shindig != "undefined");
    }-*/;

    @Override
    public void onModuleLoad() {
        // wait for shindig scripts to be loaded
        timerShindig.run();
    }

    public void moduleLoad() {

        // load the portal
        final MyGrid gadgetsContainer = new MyGrid();
        final Label dateLabel = new Label();

        final VerticalPanel vp = new VerticalPanel();
        vp.add(new Label("Welcome to the portal!"));
        vp.add(gadgetsContainer);
        vp.add(dateLabel);
        RootPanel.get().add(vp);

        service.getGadgetUrls(new AsyncCallback<ArrayList<String>>() {

            @Override
            public void onSuccess(final ArrayList<String> gadgetUrls) {
                gadgetsContainer.fillWithGadgets(gadgetUrls);
            }

            @Override
            public void onFailure(final Throwable caught) {
                GWT.log(" ******* " + caught.getMessage());
            }
        });

        service.getServerDate(new AsyncCallback<String>() {

            @Override
            public void onFailure(final Throwable caught) {
                dateLabel.setText("");
                GWT.log(" ******* " + caught.getMessage());
            }

            @Override
            public void onSuccess(final String result) {
                dateLabel.setText(result);
            }

        });

    }
}

package pgu.gadget.portal.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class MyGrid extends Composite {

    private static MyGridUiBinder uiBinder = GWT.create(MyGridUiBinder.class);

    interface MyGridUiBinder extends UiBinder<Widget, MyGrid> {
    }

    @UiField
    FlexTable gadgetsContainer;

    public MyGrid() {
        initWidget(uiBinder.createAndBindUi(this));

        gadgetsContainer.setWidth("32em");
    }

    public void fillWithGadgets(final ArrayList<String> gadgetUrls) {
        gadgetUrls.add("http://www.labpixies.com/campaigns/weather/weather.xml");
        gadgetUrls.add("http://myweather.googlecode.com/svn/trunk/weather.xml");

        int row = 0;
        int col = 0;
        int nb = 0;

        final ArrayList<String> gadgetContainerIds = new ArrayList<String>();

        for (final String gadgetUrl : gadgetUrls) {
            GWT.log(gadgetUrl);
            // gadget id 
            final String gadgetContainerId = "gadget_" + nb++;
            gadgetContainerIds.add(gadgetContainerId);

            final HTMLPanel gadgetContainer = new HTMLPanel("");
            gadgetContainer.getElement().setId(gadgetContainerId);

            gadgetsContainer.setWidget(row, col, gadgetContainer);

            // gadget 
            createGadget(gadgetUrl);

            // grid layout
            if (col == 2) {
                row++;
            }

            if (col == 2) {
                col = 0;
            } else {
                col++;
            }
        }
        // render gadgets
        renderGadgets(arrayConvert(gadgetContainerIds));
    }

    public static native void createGadget(String url) /*-{
		var gadget = $wnd.shindig.container.createGadget({
			specUrl : url
		});
		$wnd.shindig.container.addGadget(gadget);
    }-*/;

    public static native void renderGadgets(JavaScriptObject arrayGadgetIds) /*-{
		$wnd.shindig.container.layoutManager.setGadgetChromeIds(arrayGadgetIds);
		$wnd.shindig.container.renderGadgets();

    }-*/;

    // for ( var i = 0; i < gadgets.length; i++) {
    //  shindig.container.renderGadget(gadgets[i]);
    //	}

    public static JavaScriptObject arrayConvert(final ArrayList<String> list) {
        final JavaScriptObject result = newJSArray(list.size());

        for (int i = 0; i < list.size(); i++) {
            arraySet(result, i, list.get(i));
        }
        return result;
    }

    private static native JavaScriptObject newJSArray(int length) /*-{
		return new Array(length);
    }-*/;

    public static native int arrayLength(JavaScriptObject array) /*-{
		return array.length;
    }-*/;

    public static native String arrayGetObject(JavaScriptObject array, int index) /*-{
		return array[index];
    }-*/;

    public static native void arraySet(JavaScriptObject array, int index, String value) /*-{
		array[index] = value;
    }-*/;

}

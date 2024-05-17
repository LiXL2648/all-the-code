package com.li.adapter.springmvc;

import java.util.ArrayList;
import java.util.List;

public class DispatchServlet {

    private List<HandlerAdapter> handlerAdapters = new ArrayList<>();
    public DispatchServlet() {
        this.handlerAdapters.add(new HttpHandlerAdapter());
        this.handlerAdapters.add(new SimpleHandlerAdapter());
        this.handlerAdapters.add(new AnnotationHandlerAdapter());
    }

    public void doDispatch() {
        HttpController httpController = new HttpController();
        HandlerAdapter handler = getHandler(httpController);
        handler.handle(httpController);
    }

    public HandlerAdapter getHandler(Controller controller) {
        for (HandlerAdapter handlerAdapter : this.handlerAdapters) {
            if (handlerAdapter.supports(controller)) {
                return handlerAdapter;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        DispatchServlet dispatchServlet = new DispatchServlet();
        dispatchServlet.doDispatch();
    }
}

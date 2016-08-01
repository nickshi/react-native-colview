package com.nick.nativecolview;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

/**
 * Created by nick on 7/31/16.
 */
public class ReactCOLWhiteBoardViewManager extends SimpleViewManager<ReactCOLWhiteBoardView> {

    public static final String REACT_CLASS = "WhiteBoardView";
    public static final String PROP_POINTS = "points";
    public static final String PROP_MILSECOND = "milsecond";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected ReactCOLWhiteBoardView createViewInstance(ThemedReactContext reactContext) {
        return new ReactCOLWhiteBoardView(reactContext);
    }

    @ReactProp(name = PROP_POINTS)
    public void setPoints(final ReactCOLWhiteBoardView whiteBoardView, final ReadableArray readableArray) {
        whiteBoardView.setPoints(readableArray);
    }
    
    @ReactProp(name = PROP_MILSECOND)
    public void setMilsecond(final ReactCOLWhiteBoardView whiteBoardView, final int milsecond) {
        whiteBoardView.setMilsecond(milsecond);
    }
}

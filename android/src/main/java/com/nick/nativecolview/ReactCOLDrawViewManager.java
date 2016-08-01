package com.nick.nativecolview;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

/**
 * Created by nick on 7/29/16.
 */
public class ReactCOLDrawViewManager extends SimpleViewManager<ReactCOLDrawView> {
    public static final String REACT_CLASS = "DrawView";
    public static final String PROP_TILE_WIDTH = "tileWith";
    public static final String PROP_TILE_HEIGHT = "tileWith";
    public static final String PROP_SOURCE = "src";
    public static final String PROP_TILES = "tiles";
    @Override
    public String getName() {
        return REACT_CLASS;
    }
    @Override
    protected ReactCOLDrawView createViewInstance(ThemedReactContext reactContext) {
        return new ReactCOLDrawView(reactContext);
    }

    @ReactProp(name = PROP_TILE_WIDTH)
    public void setTileWidth(final ReactCOLDrawView drawView, final int tileWidth) {
        drawView.setTileWidth(tileWidth);
    }

    @ReactProp(name = PROP_TILE_HEIGHT)
    public void setTileHeight(final ReactCOLDrawView drawView, final int tileHeight) {
        drawView.setTileHeight(tileHeight);
    }

    @ReactProp(name = PROP_SOURCE)
    public void setSrc(final ReactCOLDrawView drawView, final ReadableMap readableMap) {
        drawView.setSource(readableMap);
    }

    @ReactProp(name = PROP_TILES)
    public void setTiles(final ReactCOLDrawView drawView, final ReadableMap readableMap) {
        drawView.setTiles(readableMap);
    }

}

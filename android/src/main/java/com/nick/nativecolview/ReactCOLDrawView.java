package com.nick.nativecolview;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import javax.annotation.Nullable;

/**
 * Created by nick on 7/29/16.
 */
public class ReactCOLDrawView extends ImageView{
    private Bitmap srcImage;
    private HashMap<Integer, Integer> tiles;
    private int tileWidth = 128;
    private int tileHeight = 96;
    private Bitmap prevImage;
    private Context mContext;

    public ReactCOLDrawView(Context context) {
        super(context);
        
        this.setBackgroundColor(Color.GRAY);
        
        tiles = new HashMap<>();
        for (int i = 0; i < 64; i++) {
            tiles.put(i, -1);
        }
        mContext = context;
        
    }

    private Bitmap getDestinationBitmap(HashMap<Integer, Integer> changedTiles) {
        Bitmap desBitmap = Bitmap.createBitmap(tileWidth * 8, tileHeight * 8, Bitmap.Config.RGB_565);
        Canvas descanvas = new Canvas(desBitmap);
        if(prevImage != null && changedTiles.size() < 64)
           descanvas.drawBitmap(prevImage, 0, 0, null);
        
        for(Map.Entry<Integer, Integer> entry : changedTiles.entrySet()) {
            int tileIndex = entry.getKey();
            int bitmapIndex = entry.getValue();

            int col = tileIndex % 8;
            int row = tileIndex / 8;
            Bitmap resizeBitmap = Bitmap.createBitmap(srcImage, 0, tileHeight * bitmapIndex, tileWidth, tileHeight);
            descanvas.drawBitmap(resizeBitmap, col * tileWidth, row * tileHeight, null);

        }
        prevImage = desBitmap;
        return desBitmap;
    }

    public void setTiles(ReadableMap readableMap) {

        if (readableMap != null) {

            HashMap<Integer, Integer> changedTiles = new HashMap<>();
            ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
            while(iterator.hasNextKey()) {
                String key = iterator.nextKey();
                int tileIndex = Integer.parseInt(key);
                int oldIndex = tiles.get(tileIndex);
                int newIndex = readableMap.getInt(key);
                if (oldIndex != newIndex) {
                    changedTiles.put(tileIndex, newIndex);
                }
                tiles.put(tileIndex,newIndex);
            }

            if (changedTiles.size() > 0 && srcImage != null)
                setImageBitmap(getDestinationBitmap(changedTiles));
        }
        else
        {
            HashMap<Integer, Integer> changedTiles = new HashMap<>();
            for (int i = 0; i < 64; i++) {
                changedTiles.put(i, i);
            }

            if (changedTiles.size() > 0 && srcImage != null)
                setImageBitmap(getDestinationBitmap(changedTiles));
        }

    }

    public void setSource(@Nullable ReadableMap source) {
        String uri = source != null ? source.getString("uri") : null;
        
        if (uri != null)
        {
            File imgFile = new  File(uri);
            
            if(imgFile.exists()){
                
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                
                setSrcImage(myBitmap);
                
            }
            
        }
        else
        {
            
            Log.e("ERROR", "Please set the file path");
        }

    }

    private void setSrcImage(Bitmap newSrcImage) {
        srcImage = newSrcImage;
        prevImage = null;
        tiles.clear();
        for (int i = 0; i < 64; i++) {
            tiles.put(i, -1);
        }
    }

    public void setTileWidth(int newTileWidth) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        tileWidth =  Math.round(newTileWidth * metrics.density);
    }

    public void setTileHeight(int newTileHeight) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        tileHeight =  Math.round(newTileHeight * metrics.density);
    }
}

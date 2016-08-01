//
//  DrawView.m
//  DrawView
//
//  Created by Junhua Shi on 6/16/16.
//  Copyright © 2016 Facebook. All rights reserved.
//

#import "DrawView.h"
#import "RCTConvert.h"

@interface DrawView()

@property(nonatomic, retain) UIColor *squareColor;
@property(nonatomic, retain) UIImage *lastImage;
@property(nonatomic, assign) BOOL isPainting;
@property(nonatomic, retain) NSMutableDictionary *changedTiles;
@end
@implementation DrawView

- (id)init {
    if (self = [super init]) {
        self.changedTiles = [NSMutableDictionary new];
        [self refill];
    }
    return self;
}

- (void)refill {
    self.lastImage = nil;
    for (int i = 0; i < 64; i++) {
        [self.changedTiles setObject:[NSNumber numberWithInt:i] forKey:[NSString stringWithFormat:@"%d", i]];
    }
}

- (void)reDraw {
    if (!self.isPainting) {
        self.isPainting = true;
        [self drawTiles];
        self.isPainting = false;
    }
}
- (void)drawTiles {
    NSLog(@"#####Drawing");
    
    UIImage *displayImage = nil;
    if (self.innerTiles != nil && self.src != nil) {
        displayImage = [self getFullImage:self.changedTiles];
        self.lastImage = displayImage;
        
    }
    else
    {
        displayImage = self.lastImage;
    }
    
    self.layer.contents = (id)[displayImage CGImage];
    //  dispatch_async(dispatch_get_main_queue(), ^{
    //
    //  });
    
}

- (UIImage*)getFullImage:(NSDictionary*)beChangedTiles {
    
    CGImageRef cgImage = self.src.CGImage;
    
    float width = self.tileWidth * 8;
    float height = self.tileHeight * 8;
    
    UIGraphicsBeginImageContextWithOptions(CGSizeMake(width, height), false, 0.0);
    CGContextRef context = UIGraphicsGetCurrentContext();
    CGContextTranslateCTM(context, 0, height);
    CGContextScaleCTM(context, 1.0, -1.0);
    
    if (self.lastImage != nil && beChangedTiles.allKeys.count < 64) CGContextDrawImage(context, CGRectMake(0, 0, width, height), self.lastImage.CGImage);
    
    
    NSLog(@"ChangedTileCount: %d", beChangedTiles.allKeys.count);
    for (NSString* mIdx in beChangedTiles) {
        
        int tileIndex = [mIdx intValue];
        int mIndex = [[beChangedTiles objectForKey:mIdx] intValue];
        
        int col = tileIndex % 8;
        int row = tileIndex / 8;
        CGRect oriRect = CGRectMake(0, self.tileHeight * mIndex, self.tileWidth, self.tileHeight);
        CGRect drawRect = CGRectMake(col * self.tileWidth, height - row * self.tileHeight - self.tileHeight, self.tileWidth, self.tileHeight);
        
        CGImageRef drawImage = CGImageCreateWithImageInRect(cgImage, oriRect);
        
        // Draw the image
        CGContextDrawImage(context, drawRect, drawImage);
        
        // Clean up memory and restore previous state
        CGImageRelease(drawImage);
        
        
    }
    
    // Get the result
    UIImage *resultImage = UIGraphicsGetImageFromCurrentImageContext();
    
    // End the image context
    
    UIGraphicsEndImageContext();
    
    return resultImage;
}

- (void)setTiles:(NSDictionary*)tiles {
    [_changedTiles removeAllObjects];
    if(_innerTiles != nil && tiles != nil) {
        for (NSString* mIdx in tiles) {
            int newTileIndex = [[tiles objectForKey:mIdx] intValue];
            int oldTileIndex = [[_innerTiles objectForKey:mIdx] intValue];
            if (newTileIndex != oldTileIndex) {
                [_changedTiles setObject:[NSNumber numberWithInt:newTileIndex] forKey:mIdx];
            }
            
            [self.innerTiles setValue:[tiles objectForKey:mIdx] forKey:mIdx];
        }
    }
    else{
        self.innerTiles = [NSMutableDictionary dictionaryWithDictionary:tiles] ;
        [self refill];
    }
    if (self.innerTiles != nil && self.innerTiles.allKeys.count > 0 && _src != nil) {
        [self drawTiles];
    }
    
}
- (void)setSrc:(UIImage*)image {
    _src = image;
    [self refill];
    if (_src != nil) {
        [self drawTiles];
    }
    
}

- (void)setForceDraw:(BOOL)forceDraw {
    [self refill];
    [self drawTiles];
}
@end

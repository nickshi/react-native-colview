//
//  DrawView.h
//  DrawView
//
//  Created by Junhua Shi on 6/16/16.
//  Copyright © 2016 Facebook. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DrawView : UIView
@property(nonatomic, retain) UIImage* src;
@property(nonatomic, assign) CGFloat tileWidth;
@property(nonatomic, assign) CGFloat tileHeight;
@property(nonatomic, retain) NSDictionary* tiles;
@property(nonatomic, retain) NSMutableDictionary* innerTiles;
@property(nonatomic, assign) BOOL forceDraw;
@end
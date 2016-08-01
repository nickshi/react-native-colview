//
//  DrawViewManager.m
//  DrawView
//
//  Created by Junhua Shi on 6/16/16.
//  Copyright © 2016 Facebook. All rights reserved.
//

#import "DrawViewManager.h"
#import "DrawView.h"
@implementation DrawViewManager

RCT_EXPORT_MODULE()


- (UIView *)view
{
  DrawView *drawView = [[DrawView alloc] init];
  return drawView;
}
RCT_EXPORT_VIEW_PROPERTY(src, UIImage)
RCT_EXPORT_VIEW_PROPERTY(tileWidth, CGFloat)
RCT_EXPORT_VIEW_PROPERTY(tileHeight, CGFloat)
RCT_EXPORT_VIEW_PROPERTY(tiles, NSDictionary)
@end

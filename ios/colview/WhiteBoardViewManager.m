//
//  WhiteBoardManager.m
//  DrawView
//
//  Created by nick.shi on 6/28/16.
//  Copyright © 2016 Facebook. All rights reserved.
//

#import "WhiteBoardViewManager.h"
#import "WhiteBoardView.h"
@implementation WhiteBoardViewManager

RCT_EXPORT_MODULE()

- (UIView *)view
{
  WhiteBoardView *whiteboard = [[WhiteBoardView alloc] init];
  return whiteboard;
}
RCT_EXPORT_VIEW_PROPERTY(milsecond, int)
RCT_EXPORT_VIEW_PROPERTY(points, NSArray)
@end

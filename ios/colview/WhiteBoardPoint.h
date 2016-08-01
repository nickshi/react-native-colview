//
//  WhiteBoardPoint.h
//  DrawView
//
//  Created by nick.shi on 6/27/16.
//  Copyright © 2016 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef enum  {
  RedPenDown = -1,
  BluePenDown = -2,
  GreenPenDown = -3,
  BrownPenDown = -4,
  OrangePenDown = -5,
  PurplePenDown = -6,
  YelloPenDown = -7,
  BlackPenDown = -8,
  WideEraser = -9,
  NarrowEraser = -10,
  PenUp = -100,
  Clear = -200,
  
} WhiteBoardPointType;

@interface WhiteBoardPoint : NSObject
@property(nonatomic, assign) int x;
@property(nonatomic, assign) int y;
@property(nonatomic, assign) int millisecond;

- (id)initWithDic:(NSDictionary*)dic;
@end

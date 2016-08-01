//
//  WhiteBoardPoint.m
//  DrawView
//
//  Created by nick.shi on 6/27/16.
//  Copyright © 2016 Facebook. All rights reserved.
//

#import "WhiteBoardPoint.h"


@implementation WhiteBoardPoint

- (id)initWithDic:(NSDictionary*)dic {
  if (self = [super init]) {
    self.x = [[dic objectForKey:@"x"] intValue];
    self.y = [[dic objectForKey:@"y"] intValue];
    self.millisecond = [[dic objectForKey:@"millisecond"] intValue];
  }
  return self;
}
@end

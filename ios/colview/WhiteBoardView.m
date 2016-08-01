//
//  WhiteBoardView.m
//  DrawView
//
//  Created by nick.shi on 6/27/16.
//  Copyright © 2016 Facebook. All rights reserved.
//

#import "WhiteBoardView.h"
#import "WhiteBoardPoint.h"
@implementation WhiteBoardView

- (id)init {
  if (self = [super init]) {
    self.lastX = -1;
    self.lastY = -1;
    self.lineWidth = 2;
  }
  return self;
}

- (void)setPoints:(NSArray *)points {
  _points = points;
  [self setNeedsDisplay];
}

- (void)drawRect:(CGRect)rect {
  
  CGContextRef context = UIGraphicsGetCurrentContext();
  [[UIColor whiteColor] setFill];
  CGContextFillRect(context, rect);
  if (self.points != nil && self.points.count > 0) {
    for (NSDictionary *pDic in self.points) {
      WhiteBoardPoint *p = [[WhiteBoardPoint alloc] initWithDic:pDic];
      [self drawPoint:p context:context];
    }
  }
}

- (void)drawPoint:(WhiteBoardPoint*)point context:(CGContextRef)context {
  float hscale = self.bounds.size.width / 960;
  float vscale = self.bounds.size.height / 480;
  
  WhiteBoardPointType type = (WhiteBoardPointType)point.x;
  switch (type) {
    case BlackPenDown:
      CGContextSetStrokeColorWithColor(context, [UIColor blackColor].CGColor);
      break;
    case RedPenDown:
      CGContextSetStrokeColorWithColor(context, [UIColor redColor].CGColor);
      break;
    case BluePenDown:
      CGContextSetStrokeColorWithColor(context, [UIColor blueColor].CGColor);
      break;
    case GreenPenDown:
      CGContextSetStrokeColorWithColor(context, [UIColor greenColor].CGColor);
      break;
    case BrownPenDown:
      CGContextSetStrokeColorWithColor(context, [UIColor brownColor].CGColor);
      break;
    case OrangePenDown:
      CGContextSetStrokeColorWithColor(context, [UIColor orangeColor].CGColor);
      break;
    case PurplePenDown:
      CGContextSetStrokeColorWithColor(context, [UIColor purpleColor].CGColor);
      break;
    case YelloPenDown:
      CGContextSetStrokeColorWithColor(context, [UIColor yellowColor].CGColor);
      break;
    case WideEraser:
      CGContextSetStrokeColorWithColor(context, [UIColor whiteColor].CGColor);
      self.lineWidth = 390 / 12;
      break;
    case NarrowEraser:
      CGContextSetStrokeColorWithColor(context, [UIColor whiteColor].CGColor);
      self.lineWidth = 390 / 48;
      break;
    case PenUp:
      self.lineWidth = 2;
      self.lastX = -1;
      self.lastY = -1;
      CGContextStrokePath(context);
      break;
    case Clear:
      [[UIColor whiteColor] setFill];
      CGContextFillRect(context, CGRectMake(0, 0, self.bounds.size.width, self.bounds.size.height));
      self.lineWidth = 390 / 48;
      break;

    default:
      if (self.lastX > -1 && self.lastY > -1 && point.x < 65300 && self.lastY < 10000) {
        CGContextSetLineWidth(context, self.lineWidth * hscale);
        float startX = self.lastX * hscale / 12;
        float startY = self.lastY * vscale / 12;
        float endX = point.x * hscale / 12;
        float endY = point.y * vscale / 12;
        
        CGContextMoveToPoint(context, startX , startY);
        CGContextAddLineToPoint(context, endX , endY);
        
      }
      self.lastX = point.x;
      self.lastY = point.y;
      break;
  }
}

@end

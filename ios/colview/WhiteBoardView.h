//
//  WhiteBoardView.h
//  DrawView
//
//  Created by nick.shi on 6/27/16.
//  Copyright © 2016 Facebook. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface WhiteBoardView : UIView

@property (nonatomic, retain) NSArray* points;
@property (nonatomic, assign) int lineWidth;
@property (nonatomic, assign) int lastX;
@property (nonatomic, assign) int lastY;
@property (nonatomic, assign) int milsecond;
@end

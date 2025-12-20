#import <Cordova/CDV.h>
#import <Cordova/CDVPlugin.h>
#include "IBActionSheet.h"
@interface CordovaActionSheetPlugin : CDVPlugin<IBActionSheetDelegate>
@property (nonatomic, copy) NSString *callbackId;
// TODO remove one day, as it's deprecated since iOS 8
@property (nonatomic, retain) IBActionSheet *actionSheet;

- (void) show:(CDVInvokedUrlCommand*)command;
@end

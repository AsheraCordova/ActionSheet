#import "CordovaActionSheetPlugin.h"
#import "ActionSheetPlugin.h"
#include "ASMainViewController.h"

@implementation CordovaActionSheetPlugin
+ (void)initialize
{
    [ASActionSheetPlugin initPlugin];
}

- (void)show:(CDVInvokedUrlCommand *)command
{
  self.callbackId = command.callbackId;
  	NSDictionary *options = command.arguments[0];
	NSString *title = options[@"title"] ?: nil;
	NSArray *buttons = options[@"buttonLabels"];
	NSArray *position = options[@"position"] ?: nil;
	NSString *addCancelButtonWithLabel = options[@"addCancelButtonWithLabel"] ?: nil;
	NSString *addDestructiveButtonWithLabel = options[@"addDestructiveButtonWithLabel"] ?: nil;
	BOOL destructiveButtonLast = [options[@"destructiveButtonLast"] isEqual:@YES];
	self.actionSheet = [[IBActionSheet alloc] initWithTitle:title delegate:self cancelButtonTitle:addCancelButtonWithLabel destructiveButtonTitle:addDestructiveButtonWithLabel 
	  	destructiveButtonLast: destructiveButtonLast otherButtonTitlesArray:buttons];
  
  	NSString *parentFragmentId = options[@"parentFragmentId"] ?: nil;
  
  	ASActionSheetPlugin* plugin = [[ASActionSheetPlugin alloc] initWithNSString: parentFragmentId withId: self.actionSheet];

  	NSString *titleTextColor = options[@"titleTextColor"] ?: @"#000000";
    [plugin setTitleTextColorWithNSString:titleTextColor];

    NSString *titleBackgroundColor = options[@"titleBackgroundColor"];
    if (titleBackgroundColor) {
        [plugin setTitleBackgroundColorWithNSString:titleBackgroundColor];
    }

    NSString *titleFontFamily = options[@"titleFontFamily"];
    NSNumber *titleTextSize   = options[@"titleTextSize"] ?: @(16);
    if (titleFontFamily) {
        [plugin setTitleFontFamilyWithNSString:titleFontFamily withInt:[titleTextSize intValue]];
    } else {
    	[plugin setTitleTextSizeWithInt:[titleTextSize intValue]];
    }

    #pragma mark - Cancel button attributes

    NSString *cancelTextColor = options[@"cancelTextColor"] ?: @"#1E82FF";
    [plugin setCancelTextColorWithNSString:cancelTextColor];

    NSString *cancelBackgroundColor = options[@"cancelBackgroundColor"];
    if (cancelBackgroundColor) {
        [plugin setCancelBackgroundColorWithNSString:cancelBackgroundColor];
    }

    NSString *cancelHighlightColor = options[@"cancelHighlightColor"];
    if (cancelHighlightColor) {
        [plugin setCancelHighlightColorWithNSString:cancelHighlightColor];
    }

    NSString *cancelFontFamily = options[@"cancelFontFamily"];
    NSNumber *cancelTextSize   = options[@"cancelTextSize"] ?: @(16);
    if (cancelFontFamily) {
        [plugin setCancelFontFamilyWithNSString:cancelFontFamily
                                         withInt:[cancelTextSize intValue]];
    } else {
    	[plugin setCancelTextSizeWithInt:[cancelTextSize intValue]];
    }

    #pragma mark - Destructive button attributes

    NSString *destructiveTextColor = options[@"destructiveTextColor"] ?: @"#1E82FF";
    [plugin setDestructiveTextColorWithNSString:destructiveTextColor];

    NSString *destructiveBackgroundColor = options[@"destructiveBackgroundColor"];
    if (destructiveBackgroundColor) {
        [plugin setDestructiveBackgroundColorWithNSString:destructiveBackgroundColor];
    }

    NSString *destructiveHighlightColor = options[@"destructiveHighlightColor"];
    if (destructiveHighlightColor) {
        [plugin setDestructiveHighlightColorWithNSString:destructiveHighlightColor];
    }

    NSString *destructiveFontFamily = options[@"destructiveFontFamily"];
    NSNumber *destructiveTextSize   = options[@"destructiveTextSize"] ?: @(16);
    if (destructiveFontFamily) {
        [plugin setDestructiveFontFamilyWithNSString:destructiveFontFamily
                                               withInt:[destructiveTextSize intValue]];
    } else {
    	[plugin setDestructiveTextSizeWithInt:[destructiveTextSize intValue]];
    }

    #pragma mark - Other buttons attributes

    NSString *otherTextColor = options[@"otherTextColor"] ?: @"#1E82FF";;
    [plugin setOtherTextColorWithNSString:otherTextColor];

    NSString *otherBackgroundColor = options[@"otherBackgroundColor"];
    if (otherBackgroundColor) {
        [plugin setOtherBackgroundColorWithNSString:otherBackgroundColor];
    }

    NSString *otherHighlightColor = options[@"otherHighlightColor"];
    if (otherHighlightColor) {
        [plugin setOtherHighlightColorWithNSString:otherHighlightColor];
    }

    NSString *otherFontFamily = options[@"otherFontFamily"];
    NSNumber *otherTextSize   = options[@"otherTextSize"] ?: @(16);
    if (otherFontFamily) {
        [plugin setOtherFontFamilyWithNSString:otherFontFamily
                                        withInt:[otherTextSize intValue]];
    } else {
    	[plugin setOtherTextSizeWithInt:[otherTextSize intValue]];
    }

    #pragma mark - Show
    ASMainViewController* mainViewController = (ASMainViewController*) [UIApplication sharedApplication].delegate.window.rootViewController;
   	UINavigationController* navController =  (UINavigationController*) mainViewController.navController;
  	[self.actionSheet showInView:navController.view];	
}

#pragma mark - IBActionSheetDelegate methods
- (void)respondWithButtonIndex:(int)index {
  CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK
                                                       messageAsInt:index];
  [self.commandDelegate sendPluginResult:pluginResult callbackId:self.callbackId];
}

- (void)actionSheet:(IBActionSheet *)actionSheet clickedButtonAtIndex:(NSInteger)buttonIndex {
  	buttonIndex++;
  	[self respondWithButtonIndex:(int) buttonIndex];
}

// optional delegate methods
- (void)actionSheet:(IBActionSheet *)actionSheet willDismissWithButtonIndex:(NSInteger)buttonIndex {
}

- (void)actionSheet:(IBActionSheet *)actionSheet didDismissWithButtonIndex:(NSInteger)buttonIndex {

}
@end

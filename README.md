# ActionSheet

ActionSheet project adds support for android ActionSheet widget.

## Installation
To install the plugin use:

```
cordova plugin add https://github.com/AsheraCordova/ActionSheet.git
```

## Important Links
https://asheracordova.github.io/

https://asheracordova.github.io/doc/help-doc.html

https://asheracordova.github.io/playground/index.html

## Usage

**show**

List of attributes supported:

Name                				| Description
-------------       				| -------------
parentFragmentId					| Id of the Fragment from which Action Sheet is launched.
title								| Title of the Action Sheet.
titleTextColor						| Text color of title.
titleBackgroundColor				| Background color of title.
titleFontFamily						| Text font family of title.
titleTextSize						| Text size color of title.
buttonLabels						| List of action sheet buttons which needs to be created
otherTextColor						| Text color of buttonLabels.
otherBackgroundColor				| Background color of buttonLabels.
otherHighlightColor					| Highlight background color of buttonLabels.
otherFontFamily						| Text font family of buttonLabels.
otherTextSize						| Text size color of buttonLabels.
addCancelButtonWithLabel			| Cancel sheet button text.
cancelTextColor						| Text color of cancel button.
cancelBackgroundColor				| Background color of cancel button.
cancelHighlightColor				| Highlight background color of cancel button.
cancelFontFamily					| Text font family of cancel button.
cancelTextSize						| Text size of cancel button.
addDestructiveButtonWithLabel		| Destructive button text.
destructiveButtonLast				| you can choose where the destructive button is shown
destructiveTextColor				| Text color of destructive button.
destructiveBackgroundColor			| Background color of destructive button.
destructiveHighlightColor			| Highlight background color of destructive button.
destructiveFontFamily"				| Text font family of destructive button.
destructiveTextSize					| Text size of destructive button.


Example of show function below:

```
 var callback = function(buttonIndex) {
    setTimeout(function() {
      // like other Cordova plugins (prompt, confirm) the buttonIndex is 1-based (first button is index 1)
      alert('button index clicked: ' + buttonIndex);
    });
  };

  function testShareSheet(event) {
    var options = {
	     parentFragmentId: event["fragmentId"],
        title: 'What do you want with this image?',
        buttonLabels: ['Share via Facebook', 'Share via Twitter'],
        addCancelButtonWithLabel: 'Cancel',
        addDestructiveButtonWithLabel : 'Delete it',
        destructiveButtonLast: true // you can choose where the destructive button is shown
    };
    // Depending on the buttonIndex, you can now call shareViaFacebook or shareViaTwitter
    // of the SocialSharing plugin (https://github.com/EddyVerbruggen/SocialSharing-PhoneGap-Plugin)
    window.plugins.actionsheet.show(options, callback);
  };

  function testDeleteSheet(event) {
    var options = {
		  'parentFragmentId': event["fragmentId"],
        'addCancelButtonWithLabel': 'Cancel',
        'addDestructiveButtonWithLabel' : 'Delete note'
    };
    window.plugins.actionsheet.show(options, callback);
  };

  function testLogoutSheet(event) {
    var options = {
    	  'parentFragmentId': event["fragmentId"],
        'buttonLabels': ['Log out'],
        'androidEnableCancelButton' : true, // default false
        'winphoneEnableCancelButton' : true, // default false
        'addCancelButtonWithLabel': 'Cancel'
    };
    window.plugins.actionsheet.show(options, callback);
  };
  function testCustomizeSheet(event) {
	var options = {
		parentFragmentId: event["fragmentId"],
    	title: 'What do you want with this image?',
    	buttonLabels: ['Share via Facebook', 'Share via Twitter'],
   		addCancelButtonWithLabel: 'Cancel',
    	addDestructiveButtonWithLabel : 'Delete it',
		destructiveTextColor: "#ff0000",
		destructiveBackgroundColor: "#000000",
		destructiveHighlightColor: "#ffffff",
		titleFontFamily: "@font/quicksand",
    	destructiveButtonLast: true // you can choose where the destructive button is shown
	};
	window.plugins.actionsheet.show(options, callback);
  };
  
```  

var exec = require('cordova/exec');
function NotificationManager() { console.log("NotificationManager.js: is created");
}
NotificationManager.prototype.showToast = function(aString){ 
	console.log("NotificationManager.js: notifyManager"); 
	exec(function(result){ /*alert("OK" + reply);*/ }, function(result){ /*alert("Error" + reply);*/ },"NotificationManager",aString,[]);
};
var notificationManager = new NotificationManager(); module.exports = notificationManager;
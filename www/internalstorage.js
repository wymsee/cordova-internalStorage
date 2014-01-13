/*
 * An Internal Storage Plugin for PhoneGap.
*/

//var FileError = require('./FileError');
var FileSystem = require('org.apache.cordova.file.FileSystem');
var exec = require('cordova/exec');

var internalStorage = {};

internalStorage.getDir = function(successCallback, errorCallback) {
	var fail = function(message) {
		if (errorCallback) {
			errorCallback(message);
		}
	};
	var success = function(file_system) {
		if (file_system) {
			if (successCallback) {
				// grab the name and root from the file system object
				var result = new FileSystem(file_system.name, file_system.root);
				successCallback(result);
			}
		} else {
			// no FileSystem object returned
			fail("no fileSystem object returned");
		}
	};
  return exec(success, fail, "InternalStorage", "getDir", []);
};

window.internalStorage = internalStorage;
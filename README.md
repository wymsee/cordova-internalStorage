Cordova InternalStorage Plugin
===============

Cordova/Phonegap Android plugin to retrieve the internal storage directory.

This plugin is based heavily on the cordova File plugin and depends on it.

Installation:

    //cordova
    cordova plugin add https://github.com/TheKiteEatingTree/cordova-internalStorage.git
    
    //phonegap
    phonegap local plugin add https://github.com/TheKiteEatingTree/cordova-internalStorage.git


The plugin registers an `internalStorage` global.  It has only 1 function: `getDir`.  `getDir` returns a cordova/phonegap [DirectoryEntry](http://docs.phonegap.com/en/3.3.0/cordova_file_file.md.html#DirectoryEntry).

Example:

    internalStorage.getDir(function(fileSystem) {
		//success callback
		console.log(fileSystem.root);
	}, function() {
		//error callback
		console.warn("Could not open internal storage");
	});

It was developed by Wymsee for [SyncOnset](http://synconset.com)

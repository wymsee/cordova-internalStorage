/**
 * An Internal Storage Plugin for Cordova/PhoneGap.
 */
package com.synconset;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class InternalStorage extends CordovaPlugin {

	public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
		if (action.equals("getDir")) {
			cordova.getThreadPool().execute(new Runnable() {
				public void run() {
					try {
						JSONObject obj = requestFileSystem();
						callbackContext.success(obj);
					} catch (Exception e) {
						e.printStackTrace();
						callbackContext.error(e.getMessage());
					}
				}
			});
		} else {
			return false;
		}
		return true;
	}
	
	private JSONObject requestFileSystem() throws IOException, JSONException {
		JSONObject fs = new JSONObject();
		fs.put("name", "internal");
		fs.put("root", getEntry(cordova.getActivity().getFilesDir()));
		//fs.put("root", getEntry("/data/data/" + cordova.getActivity().getPackageName()));

		return fs;
	}
	
	/**
     * Returns a JSON object representing the given File.
     *
     * @param file the File to convert
     * @return a JSON representation of the given File
     * @throws JSONException
     */
    public static JSONObject getEntry(File file) throws JSONException {
        JSONObject entry = new JSONObject();

        entry.put("isFile", file.isFile());
        entry.put("isDirectory", file.isDirectory());
        entry.put("name", file.getName());
        entry.put("fullPath", "file://" + file.getAbsolutePath());
        // The file system can't be specified, as it would lead to an infinite loop.
        // entry.put("filesystem", null);

        return entry;
    }
}

package synergy.ps.vansalesapp.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;


import java.io.File;

import static android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;

public class DeviceUtil {
	
	
	public static int convertFromDpToPx(Context context, int dp) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		int px = Math.round(dp * displayMetrics.density);
		return px;
	}

	public static int convertFromPxToDp(Context context, int px) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		int dp = Math.round(px / displayMetrics.density);
		return dp;
	}
	public static int getLocalImageHeight(File file){
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(file.getAbsolutePath(), options);
		int imageHeight = options.outHeight;
		int imageWidth = options.outWidth;
		return imageHeight;
	}
	public static boolean isBrokenDatePickerDevice() {
		return (isBetweenAndroidVersions(
				Build.VERSION_CODES.LOLLIPOP,
				Build.VERSION_CODES.LOLLIPOP_MR1));
	}
	public static boolean isBetweenAndroidVersions(int min, int max) {
		return Build.VERSION.SDK_INT >= min && Build.VERSION.SDK_INT <= max;
	}

	public static boolean isConnectedToInternet(Context context) {
		boolean isConnected = false;
		if(context != null) {
			ConnectivityManager cm = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
			if (null != activeNetwork && activeNetwork.isConnected()) {
				isConnected = true;
			} 
		}
		
		return isConnected;
		
	}
	public static int getScreenHeight() {
		return Resources.getSystem().getDisplayMetrics().heightPixels;
	}
	public static Boolean isCurrentVersionLessThanJellayBean() {
		int sdk = Build.VERSION.SDK_INT;
		Boolean isLessThan = false;
		if(sdk < Build.VERSION_CODES.JELLY_BEAN) {
			isLessThan = true;
		}
		return isLessThan;
	}
	public static Boolean isCurrentVersionLessThanJellayBeanMR2() {
		int sdk = Build.VERSION.SDK_INT;
		Boolean isLessThan = false;
		if(sdk < Build.VERSION_CODES.JELLY_BEAN_MR2) {
			isLessThan = true;
		} 
		return isLessThan;
	}
	public static boolean isDeviceSupportCamera (Context context) {
		if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			// this device has a camera
			return true;
		} else {
			return false;
		}
	}
	public static String getDeviceName() {
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;
		if (model.startsWith(manufacturer)) {
			return capitalize(model).replaceAll("\\s+","");
		} else {
			return (capitalize(manufacturer) + " " + model).replaceAll("\\s+","");
		}
	}
	private static String capitalize(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		char first = s.charAt(0);
		if (Character.isUpperCase(first)) {
			return s;
		} else {
			return Character.toUpperCase(first) + s.substring(1);
		}
	}

	public static String getDeviceID() {
		String id = Build.ID;
		return id;
	}
	public static boolean isDeviceSupportFlash(Context context){
		Boolean isFlashAvailable = false;
		PackageManager packageManager = context.getPackageManager();
		// if device support flash?
		if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
			//yes
			isFlashAvailable = true;
		}else{
			//no
			isFlashAvailable = false;
		}
		return isFlashAvailable;
	}
	public static boolean isFrontAvailable(){
		Boolean isFrontAvailable = false;
		int numCameras= Camera.getNumberOfCameras();
		for(int i=0;i<numCameras;i++){
			Camera.CameraInfo info = new Camera.CameraInfo();
			Camera.getCameraInfo(i, info);
			if(CAMERA_FACING_FRONT == info.facing){
				isFrontAvailable =  true;
			}
		}
		return isFrontAvailable;
	}

}
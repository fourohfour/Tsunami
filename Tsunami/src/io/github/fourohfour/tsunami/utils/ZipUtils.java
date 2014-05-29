package io.github.fourohfour.tsunami.utils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ZipUtils {
	public static boolean unzip(String source, String destination){
	    try {
	         ZipFile zipFile = new ZipFile(source);
	         zipFile.extractAll(destination);
	    } catch (ZipException e) {
	        e.printStackTrace();
	        return false;
	    }
	    return true;
	}
	
	public static boolean unzipEncrypted(String source, String destination, String password){
	    try {
	         ZipFile zipFile = new ZipFile(source);
	         if (zipFile.isEncrypted()) {
	            zipFile.setPassword(password);
	         }
	         zipFile.extractAll(destination);
	    } catch (ZipException e) {
	        e.printStackTrace();
	        return false;
	    }
	    return true;
	}
}

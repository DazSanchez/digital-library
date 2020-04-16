package me.hsanchez.digital_library.helpers;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class ImageEncoderHelper {
	public static String toBase64(File file) throws IOException {
		byte[] fileContents = FileUtils.readFileToByteArray(file);
		String encodedString = Base64.getEncoder().encodeToString(fileContents);
		
		return encodedString;
	}
}

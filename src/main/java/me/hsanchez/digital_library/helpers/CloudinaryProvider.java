package me.hsanchez.digital_library.helpers;

import com.cloudinary.Cloudinary;

public class CloudinaryProvider {
	private static Cloudinary cloudinary;
	
	public static Cloudinary getInstance() {
		if(cloudinary == null) {
			cloudinary = new Cloudinary(System.getenv("CLOUDINARY_URL"));
		}
		
		return cloudinary;
	}
}

package com.hoaithidev.bookstore.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class CloudinaryUtil {
    private static final Log log = LogFactory.getLog(CloudinaryUtil.class);
    private static Cloudinary cloudinary;

    static {
        try {
            Properties props = new Properties();
            props.load(CloudinaryUtil.class.getClassLoader().getResourceAsStream("cloudinary.properties"));
            if (props.getProperty("cloud_name") == null ||
                props.getProperty("api_key") == null ||
                props.getProperty("api_secret") == null) {
                throw new IllegalArgumentException("Cloudinary properties are not set correctly.");
            }
            log.info("Initializing Cloudinary with provided properties.");
            cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", props.getProperty("cloud_name"),
                    "api_key", props.getProperty("api_key"),
                    "api_secret", props.getProperty("api_secret")
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String uploadFile(File file) throws Exception {
        Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.asMap(
                "resource_type", "auto" // Tự động xác định loại tài nguyên (ảnh, video, v.v.)
        ));
        return uploadResult.get("secure_url").toString(); // Trả về URL ảnh
    }
}

package com.ramostear.xbuilder.util;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.BucketManager.BatchOperations;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class QiniuUtil {
	
	static PropertiesUtil propUtil = PropertiesUtil.getInstance();
	
	static Properties props = propUtil.getProperties("qiniu");
	
	private static  String path = props.getProperty("path");
	
	private static String qiniuAccess = props.getProperty("access");
	
	private static String qiniuKey = props.getProperty("key");
	
	private static String bucketName = props.getProperty("bucketName");
	
	
	public static String upload(MultipartFile file){
		String fileName = "", extName = "",  filePath = "";
		if(null != file && !file.isEmpty()){
			extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			fileName = UUID.randomUUID() + extName;
			Configuration cfg = new Configuration(Zone.zone2());
			UploadManager uploadManager = new UploadManager(cfg);
			Auth auth = Auth.create(qiniuAccess, qiniuKey);
			String token = auth.uploadToken(bucketName);
			Response r ;
			try {
				r = uploadManager.put(file.getBytes(), fileName, token);
				if(r.isOK()){
					filePath = path+fileName;
				}
			} catch (Exception e) {
			}
		}
		return filePath;
	}
	
	public static boolean deleteQiniuFile(String url){
		Auth auth = Auth.create(qiniuAccess, qiniuKey);
		Configuration cfg = new Configuration(Zone.zone2());
		BucketManager bucketManager = new BucketManager(auth, cfg);
		String key = url.replace(path, "");
		try {
			bucketManager.delete(bucketName, key);
			return true;
		} catch (QiniuException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean deleteQiniuFileBatch(List<String> urls){
		Auth auth = Auth.create(qiniuAccess, qiniuKey);
		Configuration cfg = new Configuration(Zone.zone2());
		BucketManager bucketManager = new BucketManager(auth, cfg);
		BucketManager.BatchOperations operations = new BatchOperations();
		for(String url:urls){
			String key = url.replace(path, "");
			operations.addDeleteOp(bucketName,key);
		}
		try {
			bucketManager.batch(operations);
			return true;
		} catch (QiniuException e) {
			e.printStackTrace();
			return false;
		}
	}

}

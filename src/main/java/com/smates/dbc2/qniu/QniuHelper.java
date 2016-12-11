package com.smates.dbc2.qniu;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.smates.dbc2.utils.SysConst;

/**
 * 七牛云实现文件的简单上传和存储 账号：liuxq2015@lzu.edu.cn 密码： qing1026
 * 
 * @author 刘晓庆
 *
 */
@Service
public class QniuHelper {

	private Logger logger = Logger.getLogger(QniuHelper.class);
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	String ACCESS_KEY = "-3tLXkwsEQ9Q0UU3GORKwMliq0NzOWIXOdIOk-d_";
	String SECRET_KEY = "SSWDULytjBX1lgwi35qyftpCVp7OZtifdWlBh5cw";
	// 要上传的空间
	String bucketname = "liuxiaoqing";
	// 密钥配置
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	// 创建上传对象
	UploadManager uploadManager = new UploadManager();

	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public String getUpToken() {
		return auth.uploadToken(bucketname);
	}

	/**
	 * 将文件上传到七牛云
	 * 
	 * @param image
	 *            文件二进制流
	 * @param fileName
	 *            文件名
	 * @throws QiniuException
	 * @throws IOException
	 */
	public void uploadFile(MultipartFile file, String fileName) {
		try {
			// 调用put方法上传
			Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
			// 打印返回的信息
			logger.info("七牛云:" + res.bodyString());
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			try {
				logger.info(r.bodyString());
			} catch (QiniuException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 删除七牛云上的文件
	 * 
	 * @param fileName
	 *            文件名
	 */
	public void deleteFile(String fileName) {
		// 实例化一个BucketManager对象
		BucketManager bucketManager = new BucketManager(auth);
		try {
			// 调用delete方法移动文件
			bucketManager.delete(bucketname, fileName);
			logger.info("文件" + fileName + "已删除");
		} catch (QiniuException e) {
			// 捕获异常信息
			Response r = e.response;
			logger.info(r.toString());
		}
	}
	
	/**
	 * 给用户的头像图片加格式,缩放 100px*80px
	 * @param imageName 图片文件名
	 * @return
	 */
	public  String formateUserHeadIcon(String imageName){
		return imageName+SysConst.QNIUYUNSTYLE;
	}
}

package com.genpact.logistics.common.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.assertj.core.util.Lists;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class FileUtil {
	
	public List<String> upload(HttpServletRequest request, HttpServletResponse response,BusinessDir businessDir)  {
		List<String> list = Lists.newArrayList();
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						System.out.println(myFileName);
						// 重命名上传后的文件名
						String[] nameArr = myFileName.split(".");
						if (ArrayUtils.isNotEmpty(nameArr)) {
							String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + nameArr[nameArr.length - 1];
							// 定义上传路径
							Properties properties;
							try {
								properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("application.properties"));
								String path = request.getServletContext().getRealPath(properties.getProperty("site.uploadPath")) 
										+ "/" 
										+ businessDir.toString()
										+ "/"
										//+ ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd")).get().format(new Date()) 
										+ "/" + fileName;
								File localFile = new File(path);
								if(!localFile.getParentFile().exists()){
									localFile.getParentFile().mkdirs();
								}
								file.transferTo(localFile);
								list.add(path);
							} catch (IOException e) {
								e.printStackTrace();
							}
							
						}

					} 
				}
			}

		}
		return list;
	}
	
	public enum BusinessDir{
		AVATAR
	}
}



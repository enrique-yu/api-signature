package com.icoolkj.api.wrap.client.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.io.*;

/**
 * 图片处理工具类 ImageUtils
 *
 * @author: haiwei.yu01
 */
public class ImageUtils
{
    private static final Log log = LogFactory.get(ImageUtils.class);

    /**
     * 远程图片URL转Base64编码
     *
     * @param imageUrl 图片URL
     * @return Base64编码
     */
    public static String imageUrlToBase64(String imageUrl) {
        if (ObjectUtil.isEmpty(imageUrl)) {
            log.error("Image URL cannot be empty or null.");
            return null;
        }

        try (HttpResponse res = HttpRequest.get(imageUrl).execute();
             InputStream is = res.bodyStream();
             ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            return Base64.encode(outStream.toByteArray());
        } catch (IOException e) {
            log.error("Failed to convert image to Base64: {}", e.getMessage());
            return null;
        }
    }

    /**
     * 本地图片转Base64编码
     *
     * @param imagePath 本地图片路径
     * @return Base64编码
     */
    public static String localImageToBase64(String imagePath) {
        if (ObjectUtil.isEmpty(imagePath)) {
            log.error("Image URL cannot be empty or null.");
            return null;
        }

        File file = new File(imagePath);
        if (file.exists() && file.isFile()) {
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                byte[] bytes = new byte[(int) file.length()];
                fileInputStream.read(bytes);
                return Base64.encode(bytes);
            } catch (IOException e) {
                log.error("Failed to convert image to Base64: {}", e.getMessage());
                return null;
            }
        }
        return null;
    }

}

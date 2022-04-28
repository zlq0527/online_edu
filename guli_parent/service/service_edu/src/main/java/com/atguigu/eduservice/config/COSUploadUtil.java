package com.atguigu.eduservice.config;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class COSUploadUtil {
    // 初始化用户身份信息
    String secretId = "AKIDlLgkY7PP8bmhKgmb9PP8qIUWHlyvb1A2";
    String secretKey = "cx1pMcbJlwqUt1sRkmX1EBqFNFq9BxSg";
    // 地域
    String bucketRegion = "ap-beijing";
    // bucket名称
    String bucketName = "abc-1307724118";
    //根据需要设置，参考官方文档
    String basicPath = "";

    public String upLoadFile2COS(Long fileSize, String filename, MultipartFile file) throws IOException {
        // 创建cos客户端

        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(bucketRegion);
        ClientConfig clientConfig = new ClientConfig(region);
        COSClient cosClient = new COSClient(cred, clientConfig);
        // 获取输入流
        InputStream inputStream =  new BufferedInputStream(file.getInputStream());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        // 设置输入流长度为500
        // 这里要强调一下，因为腾讯云支持本地文件上传和文件流上传，为了不必要的麻烦所以选择文件流上传，根据官方文档，为了避免oom，必须要设置元数据并告知输入流长度
        objectMetadata.setContentLength(fileSize);
        // 具体用法参考官方文档
        String uuid= UUID.randomUUID().toString().replaceAll("-","");
        filename=uuid+filename;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, basicPath  + "/" + filename, inputStream, objectMetadata);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        // 完成上传之后，关闭连接
        destory(cosClient);
        // 通过回调函数判断是否上传成功，有etag信息则表示上传成功，否则上传失败
        String url = "https://"+bucketName+".cos.ap-beijing.myqcloud.com"+"/"+filename;

        if (putObjectResult.getETag() != null)
            return url;
        else
            return "";
    }

    // 关闭连接
    public void destory(COSClient cosClient) {
        cosClient.shutdown();
    }
}

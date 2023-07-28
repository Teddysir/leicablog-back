package leica.blog.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3FilUploadService {

    private final AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

//    private String defaultUrl = "https://s3.amazonaws.com/";

    public String uploadFile(MultipartFile file) throws IOException{
        String fileName = generateFileName(file);
        try {
            s3Client.putObject(bucketName, fileName, file.getInputStream(), getObjectMetadata(file));
            return fileName;
        } catch (SdkClientException e) {
            throw new IOException("S3에 파일 업로드를 실패하였습니다.",e);
        }
    }

    private ObjectMetadata getObjectMetadata(MultipartFile file){
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        return objectMetadata;
    }

    private String generateFileName(MultipartFile file){
        return UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
    }
}

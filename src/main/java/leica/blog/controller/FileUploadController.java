package leica.blog.controller;

import com.amazonaws.Response;
import leica.blog.service.S3FilUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/admin/upload")
@RequiredArgsConstructor
public class FileUploadController {

    private final S3FilUploadService s3FilUploadService;

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestPart("file")MultipartFile file) throws IOException {
        String key = s3FilUploadService.uploadFile(file);
        return ResponseEntity.ok(key); // key값 리턴해줄거임

        // postman으로 form-data 형식으로 key는 "file"로 해주고 이미지 파일 선택해서 post로 데이터 전송하면 key-"파일명.type" 형식으로 반환될거임

    }
}
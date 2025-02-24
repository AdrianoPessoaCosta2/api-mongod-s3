package com.apimongods3.service;

import com.apimongods3.model.FileRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private S3Client s3Client;

    @Autowired
    private MongoRepository mongoRepoitory;

    public String uploadFileToS3(MultipartFile file) {
        String fileName = UUID.randomUUID() + ".csv";
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket("bucket_s3")
                    .key(fileName)
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
            return s3Client.utilities().getUrl(b -> b.bucket("bucket_s3").key(fileName)).toString();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao fazer upload para S3", e);
        }
    }

    public void processFile(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);

        ResponseInputStream<GetObjectResponse> s3Response = s3Client.getObject(GetObjectRequest.builder()
                .bucket("bucket_s3") // Substitua pelo nome do seu bucket
                .key(fileName)
                .build());

        BufferedReader reader = new BufferedReader(new InputStreamReader(s3Response));

        reader.lines().forEach(line -> {
            String[] fields = line.split(",");
            if (fields.length >= 2) {
                FileRecord record = new FileRecord(fields[0], fields[1]);
                mongoRepoitory.save(record);  // Salvando no MongoDB
            }
        });
        try {
            reader.close();
            s3Response.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

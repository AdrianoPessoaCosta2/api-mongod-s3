package com.apimongods3.repository;

import com.apimongods3.model.FileRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends MongoRepository<FileRecord, String> {
}

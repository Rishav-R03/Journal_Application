package com.example.project1.repository;

import com.example.project1.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRespository extends MongoRepository<JournalEntry, ObjectId> {
    //pojo and the entity we are using
}

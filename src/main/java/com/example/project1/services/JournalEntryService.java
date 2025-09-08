package com.example.project1.services;

import com.example.project1.entity.JournalEntry;
import com.example.project1.repository.JournalEntryRespository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.*;
@Component // to make sure that fields are not null when used later.
public class JournalEntryService  {
    @Autowired
    private JournalEntryRespository journalEntryRespository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRespository.save(journalEntry);
    }
    public List<JournalEntry> getAllJournal(){
        return journalEntryRespository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId jId){
        return journalEntryRespository.findById(jId);
    }
    public void deleteById(ObjectId jId){
        journalEntryRespository.deleteById(jId);
    }
}

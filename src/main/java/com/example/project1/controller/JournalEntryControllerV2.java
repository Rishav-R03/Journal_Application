package com.example.project1.controller;

import org.springframework.web.bind.annotation.*;
import com.example.project1.entity.JournalEntry;
import com.example.project1.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAllEntries(){
        return journalEntryService.getAllJournal();
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry newJournal){
        newJournal.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(newJournal);
        return true;
    }
    @DeleteMapping("/{jId}")
    public boolean deleteById(@PathVariable String jId){
        journalEntryService.deleteById(new ObjectId(jId));
        return true;
    }
    @PutMapping("/{jId}")
    public ResponseEntity<JournalEntry> updateById(@PathVariable String jId, @RequestBody JournalEntry newJournal) {
        Optional<JournalEntry> optionalOldJournal = journalEntryService.findById(new ObjectId(jId));
        if (optionalOldJournal.isPresent()) {
            JournalEntry oldJournal = getJournalEntry(newJournal, optionalOldJournal);

            journalEntryService.saveEntry(oldJournal);
            return ResponseEntity.ok(oldJournal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private static JournalEntry getJournalEntry(JournalEntry newJournal, Optional<JournalEntry> optionalOldJournal) {
        JournalEntry oldJournal = optionalOldJournal.get();

        // Update title if the new title is not null and not empty
        if (newJournal.getTitle() != null && !newJournal.getTitle().isEmpty()) {
            oldJournal.setTitle(newJournal.getTitle());
        }

        // Update content if the new content is not null and not empty
        if (newJournal.getContent() != null && !newJournal.getContent().isEmpty()) {
            oldJournal.setContent(newJournal.getContent());
        }
        return oldJournal;
    }

    @GetMapping ("/{jId}")
    public Optional<JournalEntry> findById(@PathVariable String jId){
        return Optional.ofNullable(journalEntryService.findById(new ObjectId(jId)).orElse(null));
    }

}

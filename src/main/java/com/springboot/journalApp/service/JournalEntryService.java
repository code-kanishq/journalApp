package com.springboot.journalApp.service;

import com.springboot.journalApp.entity.JournalEntry;
import com.springboot.journalApp.entity.User;
import com.springboot.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService
{
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username)
    {
        try
        {
            User user = userService.findByUserName(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        }
        catch (Exception e)
        {
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry)
    {
        try
        {
            journalEntryRepo.save(journalEntry);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public List<JournalEntry> getAllEntries()
    {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findByID(ObjectId id)
    {
        return journalEntryRepo.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String username)
    {
        boolean removed = false;
        try
        {
            User user = userService.findByUserName(username);
            removed = user.getJournalEntries().removeIf(j -> j.getId().equals(id));
            if (removed)
            {
                userService.saveEntry(user);
                journalEntryRepo.deleteById(id);
            }
            return removed;
        }
        catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the entry.", e);
        }
    }
}
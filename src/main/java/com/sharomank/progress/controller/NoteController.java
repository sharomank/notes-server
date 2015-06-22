package com.sharomank.progress.controller;

import com.sharomank.progress.model.Note;
import com.sharomank.progress.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NoteRepository repository;

    @RequestMapping("")
    public List<Note> getAllNotes() {
        return repository.findAll();
    }
}

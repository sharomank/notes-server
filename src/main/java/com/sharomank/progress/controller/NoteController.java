package com.sharomank.progress.controller;

import com.sharomank.progress.model.Note;
import com.sharomank.progress.repository.NoteRepository;
import com.sharomank.progress.util.JavaBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NoteRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Note> getNotes() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Note getNote(@PathVariable String id) {
        return repository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Note createNote(Note note) {
        note.setCreated(new Date());
        return repository.insert(note);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public Note updateNote(@PathVariable String id,
                           @RequestBody Note updateNote) {
        Note currentNote = repository.findOne(id);
        Assert.notNull(currentNote);
        JavaBeanUtils.copyNotNullProperties(updateNote, currentNote);
        return repository.save(currentNote);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteNote(@PathVariable String id) {
        repository.delete(id);
    }
}

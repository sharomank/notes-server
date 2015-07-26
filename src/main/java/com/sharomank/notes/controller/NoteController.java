package com.sharomank.notes.controller;

import com.sharomank.notes.model.Note;
import com.sharomank.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(NoteController.URI_PATH)
public class NoteController extends AbstractController<Note> {
    public static final String URI_PATH = "/notes";

    @Autowired
    public NoteController(NoteRepository repository) {
        super(repository);
    }

    @Override
    protected String getUriPath() {
        return URI_PATH;
    }
}

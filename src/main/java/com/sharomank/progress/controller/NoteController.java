package com.sharomank.progress.controller;

import com.sharomank.progress.model.Note;
import com.sharomank.progress.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(NoteController.URI_PATH)
public class NoteController extends AbstractController<Note> {
    public static final String URI_PATH = "/notes";

    private NoteRepository repository;

    @Autowired
    public NoteController(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    protected MongoRepository<Note, String> getRepository() {
        return repository;
    }

    @Override
    protected String getUriPath() {
        return URI_PATH;
    }
}

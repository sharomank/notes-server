package com.sharomank.notes.controller;

import com.sharomank.notes.model.Note;
import com.sharomank.notes.repository.NoteRepository;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class NoteControllerTest extends AbstractControllerTest<Note> {
    private static final List<String> TEST_NOTES = Arrays.asList(
            "Buy foods",
            "Check tasks",
            "Call to customer");

    @Autowired
    private NoteRepository noteRepository;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        TEST_NOTES.stream()
                .map(this::createNote)
                .map(noteRepository::insert)
                .map(testItems::add)
                .count();
    }

    @Override
    protected String getUriPath() {
        return NoteController.URI_PATH;
    }

    @Override
    protected Note getTestItemForInsert() {
        return createNote("NEW NOTE");
    }

    private Note createNote(String noteName) {
        Note note = new Note();
        note.setName(noteName);
        note.setDescription(MessageFormat.format("Description for ''{0}''", noteName));
        note.setCreated(LocalDateTime.now());
        return note;
    }
}

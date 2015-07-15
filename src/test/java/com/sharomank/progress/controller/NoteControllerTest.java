package com.sharomank.progress.controller;

import com.sharomank.progress.model.Note;
import com.sharomank.progress.repository.NoteRepository;
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

        noteRepository.deleteAll();

        TEST_NOTES.stream().forEach(testName -> {
            Note note = new Note();
            note.setName(testName);
            note.setDescription(MessageFormat.format("Description for ''{0}''", testName));
            note.setCreated(LocalDateTime.now());
            testItems.add(noteRepository.insert(note));
        });
    }

    @Override
    protected String getUriPath() {
        return NoteController.URI_PATH;
    }

    @Override
    protected Note getTestItemForInsert() {
        return new Note("NEW NOTE", "SOME INFO", LocalDateTime.now(), null, null);
    }
}

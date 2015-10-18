package com.sharomank.notes.controller;

import com.sharomank.notes.model.Label;
import com.sharomank.notes.repository.LabelRepository;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class LabelControllerTest extends AbstractControllerTest<Label> {
    private static final List<String> TEST_LABELS = Arrays.asList(
            "Work",
            "Family",
            "Frogs");

    @Autowired
    private LabelRepository labelRepository;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        TEST_LABELS.stream()
                .map(this::createLabel)
                .map(labelRepository::insert)
                .map(testItems::add)
                .count();
    }

    @Override
    protected String getUriPath() {
        return LabelController.URI_PATH;
    }

    @Override
    protected Label getTestItemForInsert() {
        return createLabel("NEW LABEL");
    }

    private Label createLabel(String name) {
        Label lbl = new Label();
        lbl.setName(name);
        return lbl;
    }
}

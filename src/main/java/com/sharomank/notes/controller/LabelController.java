package com.sharomank.notes.controller;

import com.sharomank.notes.model.Label;
import com.sharomank.notes.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(LabelController.URI_PATH)
public class LabelController extends AbstractController<Label> {
    public static final String URI_PATH = "/labels";

    @Autowired
    public LabelController(LabelRepository repository) {
        super(repository);
    }

    @Override
    protected String getUriPath() {
        return URI_PATH;
    }
}
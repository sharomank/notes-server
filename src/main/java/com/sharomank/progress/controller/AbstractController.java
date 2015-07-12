package com.sharomank.progress.controller;

import com.sharomank.progress.model.BaseModel;
import com.sharomank.progress.util.JavaBeanUtils;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

public abstract class AbstractController<E extends BaseModel> {
    private static final String ID_PATH_VAR = "/{id}";
    private static final String SLASH = "/";

    protected abstract String getUriPath();

    protected abstract MongoRepository<E, String> getRepository();

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<E>> list() {
        return ResponseEntity.ok(getRepository().findAll());
    }

    @RequestMapping(value = ID_PATH_VAR, method = RequestMethod.GET)
    public ResponseEntity<E> get(@PathVariable String id) {
        E result = getRepository().findOne(id);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<E> create(E item) {
        item.setCreated(LocalDateTime.now());
        item = getRepository().insert(item);
        return ResponseEntity.created(URI.create(getUriPath() + SLASH + item.getId())).body(item);
    }

    @RequestMapping(value = ID_PATH_VAR, method = RequestMethod.PUT)
    public ResponseEntity<E> update(@PathVariable String id, @RequestBody E updateItem) {
        E current = getRepository().findOne(id);
        if (current == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        JavaBeanUtils.copyNotNullProperties(updateItem, current);
        return ResponseEntity.ok().body(getRepository().save(current));
    }

    @RequestMapping(value = ID_PATH_VAR, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        boolean exists = getRepository().exists(id);
        if (!exists) {
            ResponseEntity.notFound();
        }
        getRepository().delete(id);
    }
}


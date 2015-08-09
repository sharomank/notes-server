package com.sharomank.notes.controller;

import com.sharomank.notes.model.BaseModel;
import com.sharomank.notes.util.Constant;
import com.sharomank.notes.util.JavaBeanUtils;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;
import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

public abstract class AbstractController<E extends BaseModel> {
    private final MongoRepository<E, String> repository;

    public AbstractController(MongoRepository<E, String> repository) {
        this.repository = repository;
    }

    protected abstract String getUriPath();

    protected MongoRepository<E, String> getRepository() {
        return repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<E>> list() {
        return ok(getRepository().findAll());
    }

    @RequestMapping(value = Constant.PATH_VARIABLE_ID, method = RequestMethod.GET)
    public ResponseEntity<E> get(@PathVariable String id) {
        E result = getRepository().findOne(id);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ok(result);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<E> create(E item) {
        item.setCreated(LocalDateTime.now());
        item = getRepository().insert(item);
        URI uri = URI.create(getUriPath() + Constant.SLASH + item.getId());
        return ResponseEntity.created(uri).body(item);
    }

    @RequestMapping(value = Constant.PATH_VARIABLE_ID, method = RequestMethod.PUT)
    public ResponseEntity<E> update(@PathVariable String id, @RequestBody E updateItem) {
        E current = getRepository().findOne(id);
        if (current == null || current.isDeleted()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        JavaBeanUtils.copyNotNullProperties(updateItem, current);
        current.setUpdated(LocalDateTime.now());
        return ok(getRepository().save(current));
    }

    @RequestMapping(value = Constant.PATH_VARIABLE_ID, method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        E item = getRepository().findOne(id);
        if (item == null) {
            return notFound().build();
        }
        item.setUpdated(LocalDateTime.now());
        item.setDeleted(true);
        getRepository().save(item);
        return ok().build();
    }
}
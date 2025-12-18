package com.example.demo.actionitem;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionItemService {
    private final ActionItemRepository repository;

    public ActionItemService(ActionItemRepository repository) {

        this.repository = repository;
    }

    public List<ActionItem> saveAll(List<ActionItem> items) {

        return repository.saveAll(items);
    }
}


package com.example.demo.actionitem;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryActionItemRepository implements ActionItemRepository {

    private final List<ActionItem> store = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public List<ActionItem> saveAll(List<ActionItem> items) {
        for (ActionItem item : items) {
            item.setId(idGenerator.getAndIncrement());
            store.add(item);
        }
        return items;
    }
}

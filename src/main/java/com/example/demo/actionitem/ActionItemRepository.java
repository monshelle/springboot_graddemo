package com.example.demo.actionitem;

import java.util.List;

public interface ActionItemRepository{
    List<ActionItem> saveAll(List<ActionItem> items);
}

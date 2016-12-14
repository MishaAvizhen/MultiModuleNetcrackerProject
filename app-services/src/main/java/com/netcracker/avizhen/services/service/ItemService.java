package com.netcracker.avizhen.services.service;

import com.netcracker.avizhen.persistence.entity.Item;

import java.util.List;

/**
 * Created by Александр on 13.12.2016.
 */
public interface ItemService {
    List<Item> findAllItems();

    Item findItemById(int itemId);

    Item addItem(Item item);

    void removeItemById(int itemId);
}

package com.netcracker.avizhen.services.service.impl;

import com.netcracker.avizhen.persistence.entity.Item;
import com.netcracker.avizhen.persistence.repository.ItemRepository;
import com.netcracker.avizhen.services.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Александр on 13.12.2016.
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> findAllItems() {
        return itemRepository.getAll();
    }

    @Override
    public Item findItemById(int itemId) {
        return itemRepository.findOne(itemId);
    }

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void removeItemById(int itemId) {
        itemRepository.delete(itemId);
    }
}

package com.smates.app.dao;

import com.smates.app.pojo.Item;

public interface ItemMapper {
    int deleteByPrimaryKey(String itemId);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(String itemId);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);
}
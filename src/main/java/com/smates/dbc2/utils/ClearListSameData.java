package com.smates.dbc2.utils;

import java.util.HashSet;
import java.util.List;

/**
 * 去除某List里相同的元素
 * 
 * @author March
 *
 */
public class ClearListSameData {
	public static List removeDuplicate(List list) {
        HashSet h  =   new  HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }
}

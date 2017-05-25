package com.kerry.controller;

import com.kerry.client.DictTypeClient;
import com.kerry.core.SearchParams;
import com.kerry.system.model.DictionaryTypeModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 数据字典类型
 * Created by wangshen on 2017/4/21.
 */
@RestController
@RequestMapping("/api/sys/dict/type")
public class DictTypeController {

    @Autowired
    private DictTypeClient dictTypeClient;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(SearchParams params){
        return dictTypeClient.findByPage(params);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody DictionaryTypeModel dictionaryTypeModel){
        return dictTypeClient.save(dictionaryTypeModel);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody DictionaryTypeModel dictionaryTypeModel){
        return dictTypeClient.update(dictionaryTypeModel);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id){
        return dictTypeClient.delete(id);
    }
}

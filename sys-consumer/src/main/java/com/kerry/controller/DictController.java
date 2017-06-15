package com.kerry.controller;

import com.kerry.client.DictClient;
import com.kerry.core.SearchParams;
import com.kerry.system.model.DictionaryModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 数据字典明细
 * Created by wangshen on 2017/4/21.
 */
@RestController
@RequestMapping("/api/sys/dict")
public class DictController {

    @Autowired
    private DictClient dictClient;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(SearchParams params){
        return dictClient.findByPage(params);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody DictionaryModel dictionaryModel){
        return dictClient.save(dictionaryModel);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody DictionaryModel dictionaryModel){
        return dictClient.update(dictionaryModel);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id){
        return dictClient.delete(id);
    }

}

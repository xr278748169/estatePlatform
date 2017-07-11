package com.kerry.estate.base.controller;

import com.kerry.core.SearchParams;
import com.kerry.estate.base.client.PropertyCoClient;
import com.kerry.estate.base.model.PropertyCoModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 物业公司管理
 * Created by wangshen on 2017/7/4.
 */
@RestController
@RequestMapping("/api/estate/property-co")
public class PropertyCoController {

    @Autowired
    private PropertyCoClient propertyCoClient;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(HttpServletRequest request, SearchParams params){
        String code = request.getAttribute("code").toString();
        return propertyCoClient.findByPage(params, code);
    }

    /**
     * 保存
     * @param propertyCoModel
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String insert(HttpServletRequest request, @RequestBody PropertyCoModel propertyCoModel){
        String code = request.getAttribute("code").toString();
        propertyCoModel.setCreateUser(request.getAttribute("userId").toString());
        return propertyCoClient.insert(propertyCoModel, code);
    }

    /**
     * 修改
     * @param propertyCoModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(HttpServletRequest request, @RequestBody PropertyCoModel propertyCoModel){
        propertyCoModel.setUpdateUser(request.getAttribute("userId").toString());
        return propertyCoClient.update(propertyCoModel);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id){
        return propertyCoClient.delete(id);
    }

    /**
     * 主键查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PropertyCoModel selectById(@PathVariable("id") String id){
        return propertyCoClient.selectById(id);
    }

    /**
     * 查询全部
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<PropertyCoModel> findAll(){
        return propertyCoClient.findAll();
    }
}

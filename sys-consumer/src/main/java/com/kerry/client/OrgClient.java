package com.kerry.client;

import com.kerry.core.SearchParams;
import com.kerry.system.model.OrgModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 组织机构客户端
 * Created by wangshen on 2017/6/5.
 */
@FeignClient(name = "sys-provider")
public interface OrgClient {

    @RequestMapping(value = "/org/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params);

    @RequestMapping(value = "/org/save", method = RequestMethod.POST)
    String insert(@RequestBody OrgModel orgModel);

    @RequestMapping(value = "/org/update", method = RequestMethod.POST)
    String update(@RequestBody OrgModel orgModel);

    @RequestMapping(value = "/org/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable("id") String id);

    @RequestMapping(value = "/org/select/{id}", method = RequestMethod.GET)
    OrgModel selectById(@PathVariable("id") String id);

    @RequestMapping(value = "/org/list/tree", method = RequestMethod.GET)
    List<OrgModel> findTreeList();
}

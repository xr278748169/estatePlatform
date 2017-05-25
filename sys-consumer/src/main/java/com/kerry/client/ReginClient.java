package com.kerry.client;

import com.kerry.core.SearchParams;
import com.kerry.system.model.RegionModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 地区代码
 * Created by wangshen on 2017/4/21.
 */
@FeignClient(name = "sys-provider")
public interface ReginClient {

    @RequestMapping(value = "/regin/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params);

    @RequestMapping(value = "/regin/find/{level}/level", method = RequestMethod.GET)
    List<RegionModel> findByLevel(@PathVariable("level") String level);
}

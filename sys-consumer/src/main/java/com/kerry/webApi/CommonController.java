package com.kerry.webApi;

import com.kerry.client.DictClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangshen on 2017/6/14.
 */
@RestController
@RequestMapping("/api/sys/common")
public class CommonController {

    @Autowired
    private DictClient dictClient;

    @RequestMapping(value = "/dict/json", method = RequestMethod.GET)
    @ResponseBody
    public String findDictJson() throws Exception {
        return dictClient.findDictJson();
    }
}

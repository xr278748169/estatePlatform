package com.kerry.estate.dto;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by wangshen on 2017/8/10.
 */
public class RepairsDto {

    private String tuId;

    private String ownId;

    private String title;

    private String content;

    private List<JSONObject> imgList;

    public String getTuId() {
        return tuId;
    }

    public void setTuId(String tuId) {
        this.tuId = tuId;
    }

    public String getOwnId() {
        return ownId;
    }

    public void setOwnId(String ownId) {
        this.ownId = ownId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<JSONObject> getImgList() {
        return imgList;
    }

    public void setImgList(List<JSONObject> imgList) {
        this.imgList = imgList;
    }

    @Override
    public String toString() {
        return "RepairsDto{" +
                "tuId='" + tuId + '\'' +
                ", ownId='" + ownId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imgList=" + imgList +
                '}';
    }
}

package com.kerry.utils;

import java.lang.reflect.Field;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 处理微信消息返回封装
 * Created by wangshen on 2017/6/22.
 */
public class XmlUtils {

    /**
     * 封装消息返回
     * @param object
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static String msgEncapsulation(Object object){
        try {
            if(object == null){
                return "";
            }
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("xml");
            Field[] fields =  object.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);//设置访问权限
                Element fieldEle = fieldEleEncp(object, root, fields, i);
                //处理list集合
                if(fields[i].getGenericType().toString().contains("List")){
                    List list = (List) fields[i].get(object);
                    for (Object obj : list) {
                        itemEncp(obj,fieldEle).asXML();
                    }
                }
            }
            return document.asXML();
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * 封装通用属性
     * @param object
     * @param root
     * @param fields
     * @param i
     * @return
     * @throws IllegalAccessException
     */
    private static Element fieldEleEncp(Object object, Element root,Field[] fields, int i) throws IllegalAccessException {
        Element fieldEle = root.addElement(fields[i].getName());
        if(fields[i].getGenericType().toString().contains("String")){
            fieldEle.addCDATA(fields[i].get(object).toString());
        }
        if(fields[i].getGenericType().toString().contains("Long")){
            fieldEle.addText(fields[i].get(object).toString());
        }
        return fieldEle;
    }
    /**
     * 处理集合封装
     * @param object
     * @param root
     * @return
     */
    private static Element itemEncp(Object object,Element root){
        Element ele = root.addElement("item");
        try {
            Field[] fields =  object.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);//设置访问权限
                fieldEleEncp(object, ele, fields, i);
            }
        } catch (Exception e) {
        }
        return ele;
    }
}

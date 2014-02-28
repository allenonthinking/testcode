package com.alibaba.fastjson.parser;

import java.lang.reflect.Type;

public class ParseContext {

    private Object             object;
    private final ParseContext parent;
    private final Object       fieldName;
    private Type               type;
    private String path;

    public ParseContext(ParseContext parent, Object object, Object fieldName){
        super();
        this.parent = parent;
        this.object = object;
        this.fieldName = fieldName;
        StringBuilder sb = new StringBuilder();
        if (parent == null) {
        	sb.append("$");
        } else {
            if (fieldName instanceof Integer) {
            	sb.append(parent.getPath());
            	sb.append("[");
            	sb.append(fieldName);
            	sb.append("]");
            } else {
            	sb.append(parent.getPath());
            	sb.append(".");
            	sb.append(fieldName);
            }
        }
        path = sb.toString();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public ParseContext getParentContext() {
        return parent;
    }

    public String getPath() {
    	return path;
        
    }

    public String toString() {
        return this.getPath();
    }
}

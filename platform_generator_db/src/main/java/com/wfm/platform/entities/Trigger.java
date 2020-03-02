package com.wfm.platform.entities;

import java.io.Serializable;

/**
 * @author Weifengming
 * @description 触发器
 * @date 2020/2/27
 */
public class Trigger implements Serializable {
    /**
     * 触发器名称
     */
    private String name;

    /**
     * 触发器类型
     */
    private String triggerType;

    /**
     * 触发器事件类型
     */
    private String eventType;

    /**
     * 触发器事件描述
     */
    private String description;

    /**
     * 触发器事件内容定义
     */
    private String definition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}

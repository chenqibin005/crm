package com.liko.crm.settings.domain;

/**
 * @author hangzhi1063
 * @date 2020/9/10 19:44
 */
public class DicType {
    private String code;
    private String name;
    private String description;

    @Override
    public String toString() {
        return "dicType{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

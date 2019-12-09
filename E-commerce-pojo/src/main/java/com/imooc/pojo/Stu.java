package com.imooc.pojo;

import javax.persistence.Id;

public class Stu {
    /**
     * 测试实体类Id 
     */
    @Id
    private Integer id;

    /**
     * 测试实体类姓名
     */
    private String name;

    /**
     * 测试实体类年龄
     */
    private Integer age;

    /**
     * 获取测试实体类Id 
     *
     * @return id - 测试实体类Id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置测试实体类Id 
     *
     * @param id 测试实体类Id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取测试实体类姓名
     *
     * @return name - 测试实体类姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置测试实体类姓名
     *
     * @param name 测试实体类姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取测试实体类年龄
     *
     * @return age - 测试实体类年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置测试实体类年龄
     *
     * @param age 测试实体类年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}
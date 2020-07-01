package com.dixon.descapi.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.01
 * Functional desc: 单条题目描述数据 包括Topic和Method注解以及Method注解的代码
 */
public class DescData implements Serializable {

    private int index;
    private String topic;
    private String example;
    private List<Method> explanations;

    public DescData() {
    }

    public DescData(int index, String topic, String example, List<Method> explanations) {
        this.index = index;
        this.topic = topic;
        this.example = example;
        this.explanations = explanations;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public List<Method> getExplanations() {
        return explanations;
    }

    public void setExplanations(List<Method> explanations) {
        this.explanations = explanations;
    }

    // 单条解题方法
    public static final class Method implements Serializable {

        // 解法序号
        private int index;
        // 说明
        private String explanation;
        // 时间复杂度
        private String timeComplexity;
        // 空间复杂度
        private String spaceComplexity;
        // 代码
        private String code;

        public Method() {
        }

        public Method(int index, String explanation, String timeComplexity, String spaceComplexity, String code) {
            this.index = index;
            this.explanation = explanation;
            this.timeComplexity = timeComplexity;
            this.spaceComplexity = spaceComplexity;
            this.code = code;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getExplanation() {
            return explanation;
        }

        public void setExplanation(String explanation) {
            this.explanation = explanation;
        }

        public String getTimeComplexity() {
            return timeComplexity;
        }

        public void setTimeComplexity(String timeComplexity) {
            this.timeComplexity = timeComplexity;
        }

        public String getSpaceComplexity() {
            return spaceComplexity;
        }

        public void setSpaceComplexity(String spaceComplexity) {
            this.spaceComplexity = spaceComplexity;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return "Method{" +
                    "index=" + index +
                    ", explanation='" + explanation + '\'' +
                    ", timeComplexity='" + timeComplexity + '\'' +
                    ", spaceComplexity='" + spaceComplexity + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DescData{" +
                "index=" + index +
                ", topic='" + topic + '\'' +
                ", example='" + example + '\'' +
                ", explanations=" + explanations +
                '}';
    }
}

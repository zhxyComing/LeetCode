package com.dixon.descprocessor;

import com.dixon.descapi.Method;
import com.dixon.descapi.Topic;
import com.dixon.descapi.bean.DescData;
import com.dixon.descprocessor.util.FileUtil;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.01
 * Functional desc: 用于生成包含题目详细信息的JSON文件
 */
public class DescProcessor extends DescAbstractProcessor {

    private static final String ASSETS_PATH_SUFFIX = "/src/main/assets";
    private static final String CODE_PATH_SUFFIX = "/src/main/java/";

    private String mModuleName;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mModuleName = processingEnvironment.getOptions().get("moduleName");
        if (mModuleName == null || "".equals(mModuleName)) {
            throw new IllegalArgumentException("Please set annotationProcessorOptions -【moduleName】on your build.gradle first");
        }
    }

    // 返回要支持的注解 必须重写 固定写法
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> supportTypes = new LinkedHashSet<>();
        supportTypes.add(Topic.class.getCanonicalName()); //获取注解类名
        return supportTypes;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (set != null && set.size() != 0) {
            // 处理所有被Topic和Method注解的元素（这里是类）
            processDesc(roundEnvironment.getElementsAnnotatedWith(Topic.class), roundEnvironment.getElementsAnnotatedWith(Method.class));
        }
        return false;
    }

    private void processDesc(Set<? extends Element> topics, Set<? extends Element> methods) {
        try {
            Map<String, DescData> descDataMap = parseTopicAnnotation(topics);
            parseMethodAnnotation(descDataMap, methods);
            generateCode(descDataMap);
            // success
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 将topic解析为DescData并保存到map中
    private Map<String, DescData> parseTopicAnnotation(Set<? extends Element> topics) {
        Map<String, DescData> descDataMap = new HashMap<>();
        for (Element element : topics) {
            // 强转为类或接口程序元素
            TypeElement typeElement = (TypeElement) element;
            String className = typeElement.getQualifiedName().toString();
            Topic topicAnnotation = typeElement.getAnnotation(Topic.class);
            DescData descData = new DescData();
            descData.setIndex(topicAnnotation.index());
            descData.setExample(topicAnnotation.example());
            descData.setTopic(topicAnnotation.topic());
            descDataMap.put(className, descData);
        }
        return descDataMap;
    }

    // 将method解析到对应的descData里
    private void parseMethodAnnotation(Map<String, DescData> descDataMap, Set<? extends Element> methods) {
        for (Element element : methods) {
            // 强转为类或接口程序元素
            ExecutableElement executableElement = (ExecutableElement) element;
            String className = ((TypeElement) executableElement.getEnclosingElement()).getQualifiedName().toString();
            Method methodAnnotation = executableElement.getAnnotation(Method.class);
            DescData descData = descDataMap.get(className);
            if (descData != null) {
                List<DescData.Method> explanations = descData.getExplanations();
                if (explanations == null) {
                    explanations = new ArrayList<>();
                    descData.setExplanations(explanations);
                }
                DescData.Method method = new DescData.Method();
                method.setExplanation(methodAnnotation.explanation());
                method.setSpaceComplexity(methodAnnotation.spaceComplexity());
                method.setTimeComplexity(methodAnnotation.timeComplexity());
                method.setIndex(methodAnnotation.index());
                method.setCode(getMethodCode(className, methodAnnotation));
                explanations.add(method);
            }
        }
    }

    /**
     * 获取Class中被@Method注解的代码
     * <p>
     * 涉及大量IO操作 比较耗时 不过编译阶段 也没多大工程 无所谓了-_-!
     */
    private String getMethodCode(String className, Method method) {
        // Class的Java文件全路径
        String filePath = mModuleName + CODE_PATH_SUFFIX + className.replace(".", "/") + ".java";
        try {
            // 创建文件
            File file = new File(filePath);
            if (!file.exists()) {
                return null;
            }
            // 读取文件
            StringBuilder result = new StringBuilder();
            StringBuilder methodCode = new StringBuilder();

            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            boolean isAppendMethodCode = false; // 用于截取@Method注解完整内容
            boolean isAppendResCode = false; // 用于截取目标方法函数代码
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                if (isAppendResCode) {
                    // 删除前四个空格 因为这里截取的是方法代码 方法代码一般都会有前四个空格 影响阅读观感
                    if (s.startsWith("    ")) {
                        s = s.substring("    ".length());
                    }
                    result.append(s).append(System.lineSeparator());
                    // 截取的是完整方法
                    // 实际有优化空间 现在是每加一行 就从头执行一次现有字符串是否满足成对括号判断
                    // 优化方式是 每加一行累计括号 直到括号成对 相当于是 O(n)
                    if (isMatch(result.toString())) {
                        result.deleteCharAt(result.length() - 1);
                        return result.toString();
                    }
                    continue;
                }
                if (isAppendMethodCode) {
                    methodCode.append(s);
                }
                if (s.contains("@Method")) {
                    // 开始截取完整的@Method内容 因为可能换行 所以要完整截取
                    methodCode.append(s);
                    isAppendMethodCode = true;
                }
                if (!isEmpty(methodCode) && isMatch(methodCode.toString())) {
                    // 清空temp数据
                    isAppendMethodCode = false;
                    // @Method代码完整代码不包含空格
                    String methodCodeString = methodCode.toString().replace(" ", "");
                    methodCode.delete(0, methodCode.length());
                    String indexString = "index=" + method.index();
                    // 包含index=target，说明是目标方法 开始截取目标方法的代码
                    if (methodCodeString.contains(indexString)) {
                        isAppendResCode = true;
                    }
                }
            }
            br.close();
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断括号是否成对出现
     *
     * @param s
     * @return
     */
    public boolean isMatch(String s) {
        Stack<Character> sc = new Stack<Character>();
        char[] c = s.toCharArray();

        for (int i = 0; i < c.length; i++) {
            if ((c[i] == '(') || (c[i] == '{')) {
                sc.push(c[i]);
            } else if (c[i] == ')') {
                if (sc.isEmpty()) {
                    return false;
                } else {
                    if (sc.peek() == '(') {
                        sc.pop();
                    }
                }
            } else if (c[i] == '}') {
                if (sc.isEmpty()) {
                    return false;
                } else {
                    if (sc.peek() == '{') {
                        sc.pop();
                    }
                }
            }
        }
        return sc.empty();
    }

    private boolean isEmpty(StringBuilder stringBuilder) {
        if (stringBuilder.length() > 0
                && !"null".equals(stringBuilder.toString())
                && !"".equals(stringBuilder.toString())) {
            return false;
        }
        return true;
    }

    /**
     * 将所有题目信息添加到一个json中
     */
    private void generateCode(Map<String, DescData> descDataMap) throws IOException {
        String dirPath = mModuleName + ASSETS_PATH_SUFFIX;
        String fileName = "topics.txt";
        List<DescData> descDataList = new ArrayList<>(descDataMap.values());
        FileUtil.write(dirPath, fileName, new Gson().toJson(descDataList));
    }
}

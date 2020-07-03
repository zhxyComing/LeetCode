# 说明

## 为什么创建这个项目

练习 LeetCode 一般分为学习和复习俩个步骤，其中学习指的是代码实操，复习指的是把之前练习的题目、代码保存起来，随时阅读。

通常复习方式会选择写博客、上传云文档等等，但是本着随时阅读的原则，将学习资料保存在 App 里随时阅读，是一个不错的选择。

然而问题在于，学习时我们通常会把解题思路、步骤、甚至题目以注释的形式写在包含解题代码的 java 文件里，为了在 App 里能随时阅读，又需要把题目、解题思路、以及解题代码等一大堆数据拷贝一份成单独文件，以供 App 加载。

手动拷贝的过程是繁琐且不安全的，不应当打断学习的思路来做这些事情。

所以 LeetCode 项目提供了这样的功能：
以注解的形式，代替注释，来说明题目、解题思路，标注解题代码等等。
在运行时，编译器会自动把注释的信息、连同解题代码打包到 json 文件里。
App 启动时，会加载 json 文件，以展示题目和解题信息。

也就是说，LeetCode 完成了从编写到阅读的闭环，你学习时编写的代码，就是你复习时阅读的数据。

## 怎么用

先来看个 Demo：

```java
@Topic(index = 1, topic = TopicOne.TOPIC, example = TopicOne.EXAMPLE)
public class TopicOne {

    public static final String TOPIC = "给定一个整数数组nums和一个目标值target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。\n" +
            "你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。";

    public static final String EXAMPLE = "给定 nums = [2, 7, 11, 15], target = 9\n" +
            "因为 nums[0] + nums[1] = 2 + 7 = 9\n" +
            "所以返回 [0, 1]";


    @Method(index = 1, explanation = "遍历", timeComplexity = "n^2", spaceComplexity = "1")
    public static int[] methodA(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    @Method(index = 2,
            explanation = "一遍哈希表 \n 在表中进行的每次查找只花费 O(1) 的时间",
            timeComplexity = "n",
            spaceComplexity = "n")
    public static int[] methodB(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
```

这个 Demo 对应 LeetCode 的一个题目。每个 LeetCode 题目对应一个 java 文件。

@Topic 注解主要用来描述题目。使用该注释以规范题目信息。它的参数如下：

```
index 表示题目序号；
topic 表示题目描述；
example 表示题目例子；
```

@Method 注解用来描述解题。被@Method注解的方法会被认为是解题方法，其[源代码]会一并打包。

```
index 表示解题序号；
explanation 表示解题描述；
timeComplexity 表示时间复杂度；
spaceComplexity 表示空间复杂度；
```

最终，上述 Demo 在 App 里会如下展示：

![效果图](img/效果图.png)

所有数据存放在 assets/topics.txt 里，也可以根据需要自行解析。

另外，对于某些复杂的算法解析，还支持以 markdown 的方式导入。
markdown 文件需要放在 assets/md 文件夹下；
如果 md 文件需要加载图片，需要将图片放至 assets/images 文件夹下，并如下使用路径：
`![xxx](file:///android_asset/images/xxx)`

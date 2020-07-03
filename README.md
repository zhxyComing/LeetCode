# 说明

LeetCode 题目代码放在 app/java/com/dixon/leetcode/leetcode 文件夹下，每道题一个 .java 文件。

.java 应当是随时可运行的，这样才能随时验证解答的正确性。

@Topic 注解用于说明题目的序号、题目描述以及举例。
@Method 注解用于说明解题的思路、时间复杂度、空间复杂度。

annotaitonProcessor 会根据这些注解，自动在 module 的 assetes 文件夹下，生成 topics.txt 文件，它包含所有的题目信息，甚至被 Method 标注的解题代码。
应用启动时，会根据此文件展示完整的题目详情列表。

项目的关注点在于无缝构建学习和阅读的过程。在 Topic.class 中的学习数据，就是 App 运行时的可视数据。
不必再花大把的力气，把题目、解题代码等数据拷贝一份以供 App 显示，你的解题代码就是 App 的数据源。

另外，对于某些复杂的算法解析，还支持以 markdown 的方式导入。
markdown 文件放在 assets/md 文件夹下；
如果 md 文件需要加载图片，需要将图片放至 assets/images 文件夹下，并如下使用路径：
`![xxx](file:///android_asset/images/xxx)`

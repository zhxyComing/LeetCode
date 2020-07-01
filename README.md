# 说明

LeetCode 代码放在 java/com/dixon/leetcode/leetcode 文件夹下，每道题一个 LeetCode 文件。

LeetCode 应当是随时可运行的，这样才能随时验证解答的正确性。

@Topic 注解用于说明题目的序号、题目描述以及举例。
@Method 注解用于说明解题的思路、时间复杂度、空间复杂度。

annotaitonProcessor 会根据这些注解，自动在 module 的 assetes 文件夹下，生成 topics.txt 文件，它包含所有的题目信息，甚至被 Method 标注的解题代码。
应用启动时，会根据此文件展示完整的题目详情列表。

本项目的目标，关注点在于无缝构建学习和阅读的过程。在 Topic.class 中的学习数据，就是 App 运行时的可阅读数据。
你不必再花大把的力气，把题目、解题代码等数据拷贝一份以供 App 显示，你的代码就是 App 的数据源。

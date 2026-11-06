# ledger-cli

一个用 Java 编写的命令行记账本，支持记录收入和支出，并按分类、按日期统计。

项目只依赖 JDK，不需要任何第三方库，适合作为 Java 基础（面向对象、集合、异常处理）的练习项目。

## 功能

- 添加收入 / 支出（金额、日期、分类、备注）
- 查看所有记录，并显示总收入和总支出
- 按记录编号删除
- 按分类查询，显示该分类的收入、支出合计
- 按日期查询，显示当天的收入、支出合计
- 查看收入总览、支出总览
- 分类统计、按日期统计（收入和支出分开计算）
- 输入非法时（金额不是数字、金额不大于 0、日期格式错误、编号不存在等）给出提示，不会中断程序

## 环境要求

- JDK 11 或更高版本（已在 JDK 21 上测试）

可以用下面的命令查看当前版本：

```bash
java -version
```

## 运行方法

### 方式一：在 IntelliJ IDEA 中运行（推荐）

1. 用 IntelliJ IDEA 打开 `ledger-cli` 文件夹（File → Open）。
2. 右键 `src/main/java`，选择 Mark Directory as → Sources Root（如果已经是蓝色文件夹图标，可以跳过）。
3. 打开 `src/main/java/ledger/Main.java`，点击 `main` 方法旁边的运行按钮。

### 方式二：命令行编译运行

**编译时必须加上 `-encoding UTF-8`**，因为源代码中包含中文，不加的话在部分系统上会编译出错或出现乱码。

macOS / Linux：

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out ledger.Main
```

Windows（命令提示符）：

```bat
dir /s /b src\*.java > sources.txt
javac -encoding UTF-8 -d out @sources.txt
java -cp out ledger.Main
```

> 如果项目路径中含有空格，`sources.txt` 方式可能失败，建议改用 IntelliJ IDEA 运行。

### 中文乱码怎么办

Windows 终端默认编码不是 UTF-8，运行时中文可能显示为乱码。可以先执行：

```bat
chcp 65001
java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -cp out ledger.Main
```

或者直接在 IntelliJ IDEA 的运行窗口中运行。

## 使用示例

```
      ========欢迎使用命令行记账本========
  ======菜单======
1. 添加收入
2. 添加支出
3. 查看所有记录
4. 删除记录
5. 查看某类的收支统计
6. 查看某天的收支统计
7. 查看收入总览
8. 查看支出总览
9. 分类统计
10. 按日期统计
0. 退出
请选择:1
请输入金额:5000
请输入日期(格式为2026-11-05,直接回车为今天):2026-11-05
请输入收入种类:工资
请输入日志:十一月工资
 ---------------------

请选择:2
请输入金额:68.5
请输入日期(格式为2026-11-05,直接回车为今天):2026-11-05
请输入支出种类:餐饮
请输入日志:和同学吃火锅
 ---------------------

请选择:3
#1,2026-11-05,收入,工资,5000.00,十一月工资
#2,2026-11-05,支出,餐饮,68.50,和同学吃火锅
总收入为:5000.00
总支出为:68.50
 ---------------------

请选择:9
工资 收入:5000.00
餐饮 支出:68.50
 ---------------------
```

## 项目结构

```
ledger-cli/
├── .gitignore
├── LICENSE
├── README.md
└── src/main/java/ledger/
    ├── Main.java                         程序入口
    ├── model/
    │   ├── Transaction.java              抽象父类，表示一笔账
    │   ├── Income.java                   收入
    │   └── Expense.java                  支出
    ├── service/
    │   └── Ledger.java                   账本：增删查、统计
    ├── exception/                        自定义异常
    │   ├── InvalidAmountException.java
    │   ├── InvalidInputException.java
    │   ├── NotFoundCategoryException.java
    │   ├── NotFoundDateException.java
    │   └── NotFoundIdException.java
    └── ui/
        └── Menu.java                     命令行菜单与输入输出
```

设计上用到了：

- **抽象类与继承**：`Income`、`Expense` 继承自 `Transaction`
- **多态**：账本中统一用 `List<Transaction>` 保存收入和支出
- **集合**：`List`、`Map` 用于存储和统计
- **异常处理**：自定义异常，在菜单层统一捕获并提示

## 开发计划

- [x] v0.1 内存版：基本记账、查询、统计
- [ ] v0.2 数据保存到文件
- [ ] v0.3 更多统计功能
- [ ] 使用 Maven 管理项目，并添加单元测试

## 许可证

本项目基于 [MIT License](LICENSE) 开源。

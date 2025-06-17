# JFreeChart 学习笔记

- [核心概念](structure.md)
- [BartChart](./charts/BarChart.md)
- [LineChart](./charts/LineChart.md)
- [个性化图表](./tutorial/custom.md)
	- [[item_label|数据标签]]
	- [标题](./tutorial/title.md)

实现以下函数图表：

https://www.desmos.com/calculator/gaa5aqyb5s?lang=zh-CN

> [!IMPORTANT]
>
> I work hard to make this document as accurate and informative as I can, but cannot guarantee
> that it is error-free.

## 特点

JFreeChart 支持饼图、条形图（常规和堆积）、折线图、散点图、时序图（移动平均线、最高最低开收图、K 线图）甘特图、仪表图（刻度盘、指南针和温度计）、符号图、风向图等。

另外：

- 导出为 PNG 和 JPEG 图像文件格式，也可以使用 Java 的 `ImageIO` 库导出为 ImageIO 支持的任何格式。
- 导出为 `Graphics2D` 实现支持的格式：
  - PDF: JfreePDF https://github.com/jfree/jfreepdf
  - SVG: JFreeSVG https://github.com/jfree/jfreesvg
- tool tips
- 交互式缩放和平移
- 鼠标事件
- 注释
- HTML image map



## 参考

- https://github.com/jfree/jfreechart/releases

# Plot

## 简介

`org.jfree.chart.plot` 包包含：

- 基类 `Plot`;
- 各种 plot 子类，如 `PiePlot`, `CategoryPlot`, `XYPlot`;
- 相关支持类和接口

## XYPlot

- `axisOffset`

数据区域和坐标轴之间的距离。例如 `plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));`。

<img src="./images/image-20250114140737302.png" alt="image-20250114140737302" style="zoom:50%;" />

设置为 0：`plot.setAxisOffset(new RectangleInsets(0.0, 0.0, 0.0, 0.0));`

<img src="./images/image-20250114140817424.png" alt="image-20250114140817424" style="zoom:50%;" />

- `domainGridlinePaint`

x 轴网格线颜色。

- `rangeGridlinePaint`

y 轴网格线颜色。

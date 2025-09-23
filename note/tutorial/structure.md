# JFreeChart 结构



## 核心类

| 类                  | 作用                                                         |
| ------------------- | ------------------------------------------------------------ |
| JFreeChart          | 图表对象，任何类型的图表都是在该对象上进行定制。JFreeChart 提供了用于创建不同类型图表的工厂方法 |
| XXXDataset          | 数据集对象，不同类型的图表对应不同类型的数据集对象           |
| XXXPlot             | 图表区域，这个对象决定图表样式，该对象包含 Axis, Renderer 以及数据集 |
| XXXAxis             | 处理坐标轴                                                   |
| XXXRenderer         | 负责渲染数据                                                 |
| XXXToolTipGenerator | 用于生成提示文本                                             |



## 数据集

| 图表             | 数据集          |
| ---------------- | --------------- |
| BarChart         | CategoryDataset |
| WaterfallChart   | CategoryDataset |
| LineChart        | CategoryDataset |
| AreaChart        | CategoryDataset |
| XY Plots         | XYDataset       |
| TimeSeries chart | XYDataset       |

## 辅助类

- `ChartPanel`

用于显示 `JFreeChart` 的 `JPanel` ，添加了缩放、设置 chart 属性等功能。

- `ChartFrame`

一个简单的 `JFrame` 扩展，在其中包含 `ChartPanel` 用于显示 `JFreeChart`。

- `ChartUtils`

提供保存图片功能（PNG 和 JPEG）。

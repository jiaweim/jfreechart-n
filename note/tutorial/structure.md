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

## 图表包含的元素



## 数据集

| 图表             | 数据集          |
| ---------------- | --------------- |
| BarChart         | CategoryDataset |
| WaterfallChart   | CategoryDataset |
| LineChart        | CategoryDataset |
| AreaChart        | CategoryDataset |
| XY Plots         | XYDataset       |
| TimeSeries chart | XYDataset       |






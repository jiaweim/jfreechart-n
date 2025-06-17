# 柱状图

2025-06-17 ⭐
@author Jiawei Mao
****

## 简介

以如下数据为例：

||Column 1|Column 2|Column 3|
|---|---|---|---|
|Row 1|1.0|5.0|3.0|
|Row 2|2.0|3.0|2.0|

在 JFreeChart 中，表格称为数据集，column 标题称为 *category*，row 称为 *series*。

JFreeChart 将来自每一列（column，对应类别）的数据分组在一起用不同颜色显示。

- 数据集：`CategoryDataset`
- `Plot`: `CategoryPlot`
- `Render`: `BarRenderer`

### 创建 Dataset

创建柱状图的第一步是创建数据集：

- `CategoryDataset` 接口定义柱状图数据
- `DefaultCategoryDataset` 提供默认数据

定义表格数据对应的数据集：

```java
DefaultCategoryDataset dataset = new DefaultCategoryDataset();
String series1 = "Males";
String series2 = "Females";

String category1 = "18 to 39";
String category2 = "40 - 59";
String category3 = "60 and over";

dataset.addValue(5.5, series1, category1);
dataset.addValue(10.3, series2, category1);
dataset.addValue(8.4, series1, category2);
dataset.addValue(20.1, series2, category2);
dataset.addValue(12.8, series1, category3);
dataset.addValue(24.3, series2, category3);
```

### 创建柱状图

```java
JFreeChart chart = ChartFactory.createBarChart(
        "Antidepressant Medication Usage",
        "Age Category",
        "Percent",
        dataset);
LegendTitle legend = chart.getLegend();
chart.removeLegend();
chart.addSubtitle(new TextTitle("Percentage of adults aged 18 and over who used antidepressant medication over past 30 days, by age and sex: United States, 2015-2018"));

TextTitle source = new TextTitle("Source: https://www.cdc.gov/nchs/products/databriefs/db377.htm");
source.setFont(new Font("SansSerif", Font.PLAIN, 10));
source.setPosition(RectangleEdge.BOTTOM);
source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
chart.addSubtitle(source);
chart.addSubtitle(legend);

CategoryPlot plot = (CategoryPlot) chart.getPlot();
plot.setDomainGridlinesVisible(true);
plot.setRangeCrosshairVisible(true);
plot.setRangeCrosshairPaint(Color.BLUE);
plot.getDomainAxis().setCategoryMargin(0.2);

NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

BarRenderer renderer = (BarRenderer) plot.getRenderer();
renderer.setDrawBarOutline(false);
renderer.setBarPainter(new StandardBarPainter());
renderer.setItemMargin(0.06);
renderer.setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator("Tooltip: {0}"));
```

- 条形图的方向 `PlotOrientation` 有水平和垂直两个方向
- tooltip 控制是否显示提示文本
- url，仅在用 HTML 图像 maps 创建报告时使用

[完整代码](../../src/main/java/note/jfreechart/barchart/category/BarChartDemo1.java)。效果如下：

<img src="./images/image-20250617165553271.png" alt="image-20250617165553271" style="zoom:50%;" />

## ChartFactory

下面看看 `ChartFactory.createBarChart()` 方法的源码：

```java
CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
ValueAxis valueAxis = new NumberAxis(valueAxisLabel);
BarRenderer renderer = new BarRenderer();
...

CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
plot.setOrientation(orientation);
JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
```

说明：

- 柱状图有两个轴，一个显示数据集类别（`CategoryAxis`），另一个显示数据（`NumberAxis`）
- `BarRenderer` 用于绘制 bar，renderer 处理大部分的绘图工作。
- `dataset`, `axes` 和 `renderer` 一起由 `CategoryPlot` 管理，它协调这些组件之间的交互。在自定义图表时，通常需要获得 plot 引用，从而可以继续访问 axes, renderer 和 dataset
- 最后，将 plot 包装到 `JFreeChart` 实例中，并指定标题

## 个性化

通过调用 `JFreeChart` 和 `CategoryPlot` 的方法，可以实现简单的定制。例如，

- 修改背景颜色

```java
chart.setBackgroundPaint(Color.white); // 背景色
CategoryPlot plot = (CategoryPlot) chart.getPlot();
plot.setBackgroundPaint(Color.lightGray); // plot 的背景色
plot.setRangeGridlinePaint(Color.white); // plot 的网格线颜色
```

这里需要将 `Plot` 强制转换为 `CategoryPlot`，因为知道柱状图是该类型，所以该转换不会出错。

## 自定义 Renderer

对柱状图，其 renderer 类型为 `BarRenderer`。从 `CategoryPlot` 获取该 renderer 后，可以修改很多选项。

### Bar 颜色

修改每个 Series 的颜色：

```java
BarRenderer renderer = (BarRenderer) plot.getRenderer();
renderer.setSeriesPaint(0, Color.GRAY);
renderer.setSeriesPaint(1, Color.ORANGE);
renderer.setDrawBarOutline(false);
```

<img src="images/2023-12-26-10-11-57.png" width="500"/>

### Bar Spacing

renderer 通过 `itemMargin` 属性设置不同 category 的 bar 之间的间距。删除间距：

```java
renderer.setItemMargin(0.0);
```

<img src="images/2023-12-26-10-15-16.png" width="500"/>

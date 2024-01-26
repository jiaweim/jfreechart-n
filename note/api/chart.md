## org.jfree.chart

- [org.jfree.chart](#orgjfreechart)
- [简介](#简介)
- [ChartColor](#chartcolor)
- [ChartFactory](#chartfactory)
  - [Chart Theme](#chart-theme)
  - [BarChart](#barchart)

2023-12-26, 13:54
***

## 简介

包含主要的类和接口。

## ChartColor

定义了一些标准颜色。

- `Paint` 数组，用作大部分图表的默认 series 颜色。

```java
public static Paint[] createDefaultPaintArray();
```

## ChartFactory

该类包含许多创建标准图表类型的工厂方法。从 JFreeChart 1.0.11 开始，`ChartFactory` 多了一个 theme 属性，用于指定主题。

### Chart Theme

- 查看当前主题

主题应用于 ChartFactory 工厂方法创建的新图表。

```java
public static ChartTheme getChartTheme();
```

- 设置主题

```java
public static void setChartTheme(ChartTheme theme);
```

如果 theme 为 `null`，抛出 `IllegalArgumentException`。

`ChartUtils.applyCurrentTheme(JFreeChart chart)` 将当前主题应用于指定表格。

### BarChart

- 创建 BarChart

```java
public static JFreeChart createBarChart(
    String title, 
    String categoryAxisLabel,
    String valueAxisLabel, 
    CategoryDataset dataset, 
    PlotOrientation orientation,
    boolean legend, 
    boolean tooltips, 
    boolean urls);
```

根据指定 `CategoryDataset` 创建水平或垂直条形图。

- 创建 3D 效果的条形图

```java
public static JFreeChart createBarChart3D(
    String title, 
    String categoryAxisLabel,
    String valueAxisLabel, 
    CategoryDataset dataset, 
    PlotOrientation orientation,
    boolean legend, 
    boolean tooltips, 
    boolean urls);
```

- 创建堆叠条形图

```java
public static JFreeChart createStackedBarChart(
    String title, 
    String categoryAxisLabel,
    String valueAxisLabel, 
    CategoryDataset data, 
    PlotOrientation orientation,
    boolean legend, 
    boolean tooltips, 
    boolean urls);
```

- 创建 3D 堆叠条形图

```java
public static JFreeChart createStackedBarChart3D(
    String title, 
    String categoryAxisLabel,
    String valueAxisLabel, 
    CategoryDataset data, 
    PlotOrientation orientation,
    boolean legend, 
    boolean tooltips, 
    boolean urls);
```

- 使用 `IntervalXYDataset` 创建条形图（可以使用 `XYBarDataset` 将 `XYDataset` 转换为所需类型）

```java
public static JFreeChart createXYBarChart(
    String title, 
    String xAxisLabel,
    boolean dateAxis, 
    String yAxisLabel, 
    IntervalXYDataset dataset, 
    PlotOrientation orientation,
    boolean legend, 
    boolean tooltips, 
    boolean urls);
```

这类图表使用 `XYPlot` 和 `XYBarRenderer` 创建。


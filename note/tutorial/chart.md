# JFreeChart

2023-12-26
***

## 简介

通过 `JFreeChart` 类可以定制如下内容：

- chart border
- chart title and sub-titles
- background color and/or image
- rendering hints, 绘制图表的渲染提示，包括是否使用 anti-aliasing

## 边框

chart 的外边框：

- `setBorderVisible()`，设置边框可见，不可见
- `setBorderPaint()`，设置边框颜色
- `setBorderStroke()`，边框样式

如果在 `ChartPanel` 中显示图表，则用 Swing 提供的 border 工具更灵活。

## 背景色

设置背景色：

```java
chart.setBackgroundPaint(Color.blue);
```

可以使用任何 `Paint` 接口实现，包括 `Color`, `GradientPaint` 和 `TexturePaint`：

```java
Paint p = new GradientPaint(0, 0, Color.white, 1000, 0, Color.green));
chart.setBackgroundPaint(p);
```

如果指定了背景图片，建议将背景色设为 `null`。

## 背景图片

使用 setBackgroundImage() 设置背景图片：

```java
chart.setBackgroundImage(JFreeChart.INFO.getLogo());
```

默认缩放图像缩以填充 chart 区域，也可以使用` setBackgroundImageAlignment()` 修改默认行为：

```java
chart.setBackgroundImageAlignment(Align.TOP LEFT);
```

使用 `setBackgroundImageAlpha()` 可以设置背景图片的 alpha 透明度。

> 如果希望图像只填充数据区域（坐标轴内部区域），则需要向 `Plot` 添加背景图片。

## 渲染提示

`JFreeChart` 使用 Java2D API 绘制图表。在该 API 中，可以指定渲染提示来设置渲染引擎。

`JFreeChart` 通过 `setRenderingHints()` 方法设置渲染提示。

另有一个开启或关闭 anti-aliasing 的便捷方法：

```java
// turn on antialiasing...
chart.setAntiAlias(true);
```


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


# org.jfree.chart.renderer.xy

## 简介

包含用于 XYPlot 类渲染的 renderers。

## XYBarRenderer

适用数据集：`IntervalXYDataset`
图表类型： `XYPlot`

**构造函数**

- 创建 renderer，默认 margin 为 0.0

```java
public XYBarRenderer();
```

- 指定 margin 的 renderer，数值为百分比，如 0.10 表示 10 %

```java
public XYBarRenderer(double margin);
```

**一般属性**

- 设置 margin

margin 值为 bar 宽度的百分比。

```java
public double getMargin();
public void setMargin(double margin);
```

设置 margin 时，会同时向注册的 listeners 发送 `RendererChangeEvent`。


### XYBarPainter

renderer 有一个 `XYBarPainter` 对象，负责绘制单个 bar。JFreeChart 提供了两个实现：

- `StandardXYBarPainter`
- `GradientXYBarPainter`

## XYLineAndShapeRenderer

行为：

- 在每个点 (x, y) 之间绘制一条线
- 在每个点 (x, y) 绘制一个形状

可以对每个 series 可以设置：

- 数据点之间是否连接线
- 是否绘制每个点的形状
- 形状是否填充

该 renderer 
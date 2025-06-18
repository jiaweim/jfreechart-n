# JFreeChart-FX

## 简介

JFreeChart-FX 对 JFreeChart 进行扩展，使得能够在 JavaFX 中使用 JFreeChart。

## FXGraphics2D

JavaFX Canvas 可以添加到 Scene 中显示高质量的文本和图像。开发人员将程序从 Swing 迁移到 JavaFX 会遇到一个问题，JavaFX Canvas API 与 Java 2D API 不同，并且没有提供兼容层。

JFreeChart 使用 Java 2D API 进行渲染，遇到同样的问题。为了解决该问题，创建了 FXGraphics2D 项目，提供从 Java 2D 到 JavaFX 的兼容层。

FXGraphics2D 是一个很小的开源项目，只包含 FXGraphics2D.java 一个类，该类扩展 Java 2D API 的 `Graphics2D` 类。

目前存在的问题：

- `AlphaComposite` 的合成规则无法 100% 映射到 JavaFX 
- font-metrices 为近似值
- 带裁剪的 image 会出现奇怪的灰色效果
- 不支持 `TexturePaint` 

### Java 2D API

Java 2D API 是矢量图形 API，自 1999 年 JDK 1.2 发布以来，它一直是 Java 标准库的一部分。此外，它还是 Java Swing 的图形引擎。

符号 Java 2D API 的图形可以通过 Java Image I/O API 轻松导出为 PNG, JPEG 和其它图形格式。借用第三方库（如 Batik, iText 和 JFreeSVG），还可以导出为 SVG、PDF 等格式。

在过去是过年，已经有大量使用 Java 2D API 的代码，如开源的 JFreeChart, JGraphX, JLaTeXMath 等。

### 渲染模式

渲染模式可以分为**保留模式**（retained-mode）和**即时模式**（immediate-mode）。在 JavaFX 中，graphics-model 是基于 scene 的，即将对象添加到 scene，然后由 JavaFX runtime 渲染。可以随时更新对象，Scene 会自动再次渲染，无需程序员干预。

JavaFX 有时被称为 retained-mode。而 Java 2D 则是 immediate-mode grpahics API，它在渲染 shape 或其属性（颜色、线条样式）后不会保留任何状态信息。在渲染包含大量元素的复杂图形时，immedidate-mode 所需内存较小。JavaFX Canvas 对需要 immediate-mode 的应用引入了 immedidate 渲染选项。

### 示例

这里用开源的 JLaTeXMath 来显示 FXGraphics2D，其主要用途是显示用 LaTeX 编写的数学公式。JLaTeXMath 通过 Java 2D API 渲染。



## 参考

- https://github.com/jfree/jfreechart-fx
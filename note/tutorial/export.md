# 导出

2025-09-17⭐
@author Jiawei Mao
***
JFreeChart 在 `org.jfree.chart.util.ExportUtils` 类中提供了导出功能，支持格式：

- svg
- pdf
- jpeg
- png

导出函数签名也类似：

```java
public static void writeAsJPEG(Drawable drawable, int w, int h, 
        File file);
public static void writeAsPNG(Drawable drawable, int w, int h, 
        File file);
public static void writeAsPDF(Drawable drawable, int w, int h,
        File file);
public static void writeAsSVG(Drawable drawable, int w, int h, 
        File file);
```

其中 JPEG 和 PNG 可以直接导出，PDF 和 SVG 则需要添加额外依赖项，

**pdf**

JFreeChart 支持通过 OrsonPDF 或 JFreePDF 将 `JFreeChart` 导出为 pdf 格式，JFreePDF 是 OrsonPDF 的模块化版本，需要 Java 11+。添加依赖项：

```xml
<dependency>
    <groupId>com.orsonpdf</groupId>
    <artifactId>orsonpdf</artifactId>
    <version>1.9.1</version>
</dependency>
```

或：

```xml
<dependency>
    <groupId>org.jfree</groupId>
    <artifactId>org.jfree.pdf</artifactId>
    <version>2.0.1</version>
</dependency>
```

**svg**

导出 svg 格式需要添加 `org.jfree.svg` 依赖项：

```xml
<dependency>
    <groupId>org.jfree</groupId>
    <artifactId>org.jfree.svg</artifactId>
    <version>5.0.7</version>
</dependency>
```

`ExportUtils.isJFreeSVGAvailable()` 用于检查是否添加该依赖项，以确保是否可以导出为 svg 格式。




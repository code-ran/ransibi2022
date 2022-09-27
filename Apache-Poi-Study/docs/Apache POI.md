###                                                     Apache POI

## 1、POI操作WORD

### 文档XWPFDocument

XWPFDocument是对 `.docx` 文档操作的高级封装API。

### 1.1. 创建新文档

```java
XWPFDocument doc = new XWPFDocument();
```

### 1.2. 读取已有文档：段落、表格、图片

```java
XWPFDocument doc = new XWPFDocument(new FileInputStream("./deepoove.docx"));
```

接下来我们就可以读取文档内容，包括段落、表格、图片等：

```java
// 段落
List<XWPFParagraph> paragraphs = doc.getParagraphs();
// 表格
List<XWPFTable> tables = doc.getTables();
// 图片
List<XWPFPictureData> allPictures = doc.getAllPictures();
// 页眉
List<XWPFHeader> headerList = doc.getHeaderList();
// 页脚
List<XWPFFooter> footerList = doc.getFooterList();
```

### 1.3. 生成文档

通过传入一个OutStream，将文档写入流：

```java
try (FileOutputStream out = new FileOutputStream("simple.docx")) {
    doc.write(out);
}
```

## 2. 段落XWPFParagraph

段落是构成Word文档的一个基本单元。

### 2.1. 创建新段落

```java
XWPFParagraph p1 = doc.createParagraph();
```

### 2.2. 设置段落格式

```java
// 对齐方式
p1.setAlignment(ParagraphAlignment.CENTER);
// 边框
p1.setBorderBottom(Borders.DOUBLE);
p1.setBorderTop(Borders.DOUBLE);
p1.setBorderRight(Borders.DOUBLE);
p1.setBorderLeft(Borders.DOUBLE);
p1.setBorderBetween(Borders.SINGLE);
```

### 2.3. 基本元素XWPFRun

创建好段落后，我们就可以通过相关API处理段落内的文本和图片了。XWPFRun是段落的基本组成单元，它可以是一个文本，也可以是一张图片。

### 2.4. 段落文本

#### 2.4.1. 读取段落内容

```java
// 获取文字
String text = paragraph.getText();

// 获取段落内所有XWPFRun
List<XWPFRun> runs = paragraph.getRuns();
```

#### 2.4.2. 创建XWPFRun文本

```java
// 段落末尾创建XWPFRun
XWPFRun run = paragraph.createRun();
run.setText("为这个段落追加文本");
```

#### 2.4.3. 插入XWPFRun文本

```java
// 段落起始插入XWPFRun
XWPFRun insertNewRun = paragraph.insertNewRun(0);
insertNewRun.setText("在段落起始位置插入这段文本");
```

#### 2.4.4. 修改XWPFRun文本

```java
List<XWPFRun> runs = paragraph.getRuns();
// setText默认为追加文本，参数0表示设置第0个位置的文本，覆盖上一次设置
runs.get(0).setText("追加文本", 0);
runs.get(0).setText("修改文本", 0);
```

#### 2.4.5. 样式：颜色、字体

```java
// 颜色
run.setColor("00ff00");
// 斜体
run.setItalic(true);
// 粗体
run.setBold(true);
// 字体
run.setFontFamily("Courier");
// 下划线
run.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
```

#### 2.4.6. 文本换行

```java
run.addCarriageReturn();
```

### 2.5. 段落图片

#### 2.5.1. 提取图片XWPFPicture

```java
List<XWPFPictureData> allPictures = doc.getAllPictures();
XWPFPicture pciture = allPictures.get(0);
byte[] data = pciture.getPictureData().getData();
// 接下来就可以将图片字节数组写入输出流
```

#### 2.5.2. 创建XWPFRun图片

```java
import org.apache.poi.util.Units;

InputStream stream = new FileInputStream("./sayi.png");
XWPFRun run = paragraph.createRun();
run.addPicture(stream, XWPFDocument.PICTURE_TYPE_PNG, "Generated", Units.toEMU(256), Units.toEMU(256));
```

## 3. 表格XWPFTable

表格是构成Word文档的另一个重要基本元素。

### 3.1. 创建新表格

创建一个三行三列的表格：

```java
XWPFTable table = doc.createTable(3, 3);
```

### 3.2. 设置单元格文本

表格是由表格行XWPFRow构成，每行是由单元格XWPFCell构成，每个单元格内部又是由许多XWPFParagraph段落构成。

```java
table.getRow(1).getCell(1).setText("EXAMPLE OF TABLE");
```

上面这一段代码和下面这一段代码是等价的：

```java
XWPFParagraph p1 = table.getRow(0).getCell(0).addParagraph();
XWPFRun r1 = p1.createRun();
r1.setText("EXAMPLE OF TABLE");
```

### 3.3. 设置单元格图片

图片操作其实就是获取段落，然后等同操作段落中的图片。

```java
XWPFParagraph p1 = table.getRow(0).getCell(0).addParagraph();
XWPFRun r1 = p1.createRun();
// 同段落图片
```

### 3.4. 设置单元格样式：背景色、对齐方式

```java
// 背景色
cell.setColor(cellStyle.getBackgroundColor());

// 获取单元格段落后设置对齐方式
XWPFParagraph addParagraph = cell.addParagraph();
addParagraph.setAlignment(ParagraphAlignment.CENTER);
```

## 4. poi-tl：Word模板引擎

poi-tl（poi template language）是基于Apache POI的Word模板引擎，完整的文档参见[poi-tl官方文档](http://deepoove.com/poi-tl)，本小节不打算介绍模板引擎，主要讲解如何使用poi-tl里面对poi的增强API。

### 4.1. TableTools

`TableTools` 提供了若干操作表格的方法。

#### 4.1.1. 同一行单元格合并某几列

```java
// 合并第一行的第0列到第8列单元格
TableTools.mergeCellsHorizonal(table, 1, 0, 8);
```

#### 4.1.2. 同一列单元格合并某几行

```java
// 合并第0列的第一行到第九行的单元格
TableTools.mergeCellsVertically(table, 0, 1, 9);
```

#### 4.1.3. 表格宽度

```java
// 设置表格宽度为A4纸最大宽度
TableTools.widthTable(table, MiniTableRenderData.WIDTH_A4_FULL, 10);
```

#### 4.1.4. 表格样式

```java
// 设置表格居中
TableStyle style = new TableStyle();
style.setAlign(STJc.CENTER);
TableTools.styleTable(table, style);
```

### 4.2. NiceXWPFDocument

`NiceXWPFDocument` 是对原生 `XWPFDocument` 的增强。

#### 4.2.1. Word合并

```java
NiceXWPFDocument main = new NiceXWPFDocument(new FileInputStream("main.docx"));

NiceXWPFDocument sub = new NiceXWPFDocument(new FileInputStream("sub.docx"));

// 合并两个文档
NiceXWPFDocument newDoc = main.merge(sub);

// 生成新文档
FileOutputStream out = new FileOutputStream("new_doc.docx");
doc.write(out);
doc.close();
out.close();
```

### 4.3. XWPFParagraphWrapper

`XWPFParagraphWrapper` 是对原生 `XWPFParagraph` 的增强。

#### 4.3.1. 插入超链接

```java
XWPFParagraphWrapper wrapper = new XWPFParagraphWrapper(paragraph);
XWPFRun hyperRun = wrapper.insertNewHyperLinkRun(0, "http:deepoove.com");
hyperRun.setText("Deepoove");
```

#### 4.3.2. 插入书签

```java
XWPFParagraphWrapper wrapper = new XWPFParagraphWrapper(paragraph);
wrapper.insertNewBookmark(run);
```
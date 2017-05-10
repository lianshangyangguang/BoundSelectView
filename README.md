# BoundSelectView #  

[![](https://jitpack.io/v/lianshangyangguang/BoundSelectView.svg)](https://jitpack.io/#lianshangyangguang/BoundSelectView)  

使用：<br>
配置文件中：  
``` groovy
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }                                          
    加入依赖
    dependencies {
        compile 'com.github.lianshangyangguang:BoundSelectView:v2.8'
    }
 }
```
  
### BoundSelectView  
布局中使用如下：

```xml

 <com.gwell.view.library.SelectView
        android:id="@+id/selectview"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">

    </com.gwell.view.library.SelectView>
```
代码中使用如下：

```java
  
        SelectView selectView = (SelectView) findViewById(R.id.selectview);
        SelectView.OnItemClickListener onItemClickListener = new SelectView.OnItemClickListener() {
            @Override
            public void onItemClick(BaseSelectItem baseSelectItem, View v, boolean isCurentItem) {
                Toast.makeText(MainActivity.this, baseSelectItem.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        ArrayList<BaseSelectItem> arrays = new ArrayList();
        BaseSelectItem item1 = new BaseSelectItem("a1b", "gghh");
        BaseSelectItem currentT = item1;
        arrays.add(item1);
        arrays.add(new BaseSelectItem("a2b", 22));
        arrays.add(new BaseSelectItem("a3b", 33.3));
        arrays.add(new BaseSelectItem("a4b", 44));
        arrays.add(new BaseSelectItem("a5b", 55));

        //设置控件的参数(包括item高度，item宽度，item选中颜色，item默认颜色（未选中），item字体大小
        //item背景,分割线高度，分割线背景，两控件间距，底部控件高度，底部控件字体大小，底部控件字体颜色)
        Attribute attr =new Attribute.AttributeBuilder().rootTextColor(Color.rgb(0xff,0x00,0x00)).build();
        selectView.setAttrs(attr);

        //参数分别为：选项的监听事件，选项集合，默认元素
        selectView.setSelectView(onItemClickListener, arrays, currentT);

  ```

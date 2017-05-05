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
        compile 'com.github.lianshangyangguang:BoundSelectView:v2.4'
    }
 }
```
  
### BoundSelectView  
布局中使用如下：

```xml

 <com.gwell.view.gwellviewlibrary.BoundSelectView
        android:id="@+id/mybtn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">

    </com.gwell.view.gwellviewlibrary.BoundSelectView>
```
代码中使用如下：

```java
  
   BoundSelectView myBtn = (BoundSelectView) findViewById(R.id.mybtn);
        BoundSelectView.ItemOnClickListener itemOnClickListener = new BoundSelectView.ItemOnClickListener() {
            @Override
            public void onItemClick(BaseSelectItem baseSelectItem, View view) {
                Toast.makeText(MainActivity.this, baseSelectItem.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        ArrayList<BaseSelectItem> names = new ArrayList();
        names.add(new BaseSelectItem("1",1));
        names.add(new BaseSelectItem("2",2));
        names.add(new BaseSelectItem("3",3));

        //参数分别为：选项的监听事件，选项名称，默认显示
        myBtn.setBoundButton(itemOnClickListener, names, "2");
  
  ```

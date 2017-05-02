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
    }                                          
    加入依赖
    dependencies {
    compile 'com.github.lianshangyangguang:BoundSelectView:v2.0'
    }
```
  
### BoundSelectView  
布局中使用如下：

```xml
xmlns:app="http://schemas.android.com/apk/res-auto"

 <com.gwell.view.gwellviewlibrary.BoundSelectView
        android:id="@+id/mybtn"
        app:subCount="3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">

    </com.gwell.view.gwellviewlibrary.BoundSelectView>
```
代码中使用如下：

```java
        BoundSelectView myBtn = (BoundSelectView)findViewById(R.id.mybtn);
        BoundSelectView.ItemOnClickListener itemOnClickListener =new BoundSelectView.ItemOnClickListener() {
            @Override
            public void onItemClick(int i, View v) {
                Toast.makeText(MainActivity.this, "itemOnClickListeners "+i, Toast.LENGTH_SHORT).show();
            }
        };

        ArrayList<String> names = new ArrayList();
        names.add("select1");
        names.add("select2");
        names.add("select3");
        //参数分别为：选项的监听事件，选项名称，默认显示
       myBtn.setBoundButton(itemOnClickListener,names,"select2");
  
  ```

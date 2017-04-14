# ViewLibrary #  

[![](https://jitpack.io/v/lianshangyangguang/ViewLibrary.svg)](https://jitpack.io/#lianshangyangguang/ViewLibrary)  

使用：<br>
配置文件中：  
``` 
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
     }
    }                                          
    加入依赖
    dependencies {
    compile 'com.github.lianshangyangguang:ViewLibrary:v1.6'
    }
```
### ArcAngleView  
布局中使用如下：

```
<com.gwell.view.gwellviewlibrary.ArcAngleView
        android:id="@+id/view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
         />
```
代码中使用如下：

```
ArcAngleView view = (ArcAngleView)findViewById(R.id.view);
view.setAngle(0.7f);//参数为0-1 float类型小数，即可根据百分比转到相应角度
  
  ```
  
### BoundSelectView  
布局中使用如下：

```
xmlns:app="http://schemas.android.com/apk/res-auto"

 <com.gwell.view.gwellviewlibrary.BoundSelectView
        android:id="@+id/mybtn"
        app:subCount="3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">

    </com.gwell.view.gwellviewlibrary.BoundSelectView>
```
代码中使用如下：

```
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

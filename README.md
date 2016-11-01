# PullTextView
徐徐展开的TextView
Android中实现的渐渐展开显示的TextView。场景：展示部分文字，当点击查看更多时，展开全部内容。部分文字的行数可自行定制。
使用方式：
（1）将项目中的PullTextView 和attrs下的属性文件copy到你的项目工程中。
（2）在XML文件中创建如下：
   #  <com.song.test.PullTextView
       android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:textColor="@android:color/white"
        android:background="@android:color/holo_orange_dark"
        app:initLines= "1"//默认显示行数
        app:animationTime="1500"//展开时间
        android:text="详细内容..."
        />
 （3）在展开的地方调用：
      tv.startShow();
 （4）停止展开：
      tv.stopShow();
 （5）隐藏：
      tv.startHide();
 （6）停止隐藏：
      tv.stopHide();

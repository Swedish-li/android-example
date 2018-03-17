# Android 学习

### 日志记录
使用android monitor device 可以监控日志，控制台输出等

```java

public class MainActivity {
    // 在当前类生成 tag : logt + tab 自动生成
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // verbose,logv
        Log.v(TAG, "打印琐碎的，意义最小的日志信息");
        
        // debug,logd + tab 自动生成
        //Log.d(TAG, "onClick: ");
        Log.d(TAG, "调试级别日志");
        
        // info,logi
        Log.i(TAG, "比较重要的数据，进行用户行为分析");
        
        // warn,logw
        Log.w(TAG, "打印一些用户警告信息");
        
        // error,loge
        Log.e(TAG, "打印一些程序中的错误信息");
    }
}

```
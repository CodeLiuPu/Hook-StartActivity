# Hook StartActivity - ActivityThread - mInstrumentation


## TIPS

    Hook过程中,HookHelper类 使用书中代码,一直不起效,
    阅读源码发现 ActivityThread # currentActivityThread 方法在28 中为static方法,换个方法获取就可以了
    具体逻辑详见 HookHelper # attachContext() 方法
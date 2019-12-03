# Android URL Scheme- mock OAuth2 login
![image](https://github.com/KisCode/Scheme/blob/master/wechat.png)
# Android URL scheme 定义
scheme是android中的一种页面内跳转协议，方便app页面的内的跳转

# 原理
客户端应用可以向操作系统注册一个 URL scheme，该 scheme 用于从浏览器或其他应用中启动本应用。通过指定的 URL 字段，可以让应用在被调起后直接打开app 内的某个页面。综上URL Scheme使用场景大致分以下几种：
- 服务器下发跳转路径，客户端根据服务器下发跳转路径跳转相应的页面
- H5页面点击锚点，根据锚点具体跳转路径APP端跳转具体的页面
- APP端收到服务器端下发的PUSH通知栏消息，根据消息的点击跳转路径跳转相关页面
- APP根据URL跳转到另外一个APP指定页面

# 使用
## 客户端注册
```xml
<intent-filter>
    <action android:name="android.intent.action.VIEW"/>
    <category android:name="android.intent.category.DEFAULT"/>
    <category android:name="android.intent.category.BROWSABLE"/>
    <data
        android:host="keno.android.oauthapp"
        android:path="/login"
        android:port="2202"
        android:scheme="oauth">
    </data>
</intent-filter>
```

- scheme 代表该Schema 协议名称
- host 代表Schema作用于哪个地址域
- port 代表该路径的端口号
- path 代表Schema指定的页面
- param 代表传递的参数



## 客户端调用

```
private void startOAuth() {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    StringBuilder urlSb = new StringBuilder();
    String scheme = "oauth";
    String host = "keno.android.oauthapp";
    String path = "/login";
    int port = 2202;

    //携带参数
    String accountParam = "keno@gmail.com";
    int idParam = 9527;
    urlSb.append(scheme).append("://").append(host).append(":").append(port).append(path)
            .append("?account=").append(accountParam)
            .append("&id=").append(idParam);
    intent.setData(Uri.parse(urlSb.toString()));
    startActivity(intent);
}
```

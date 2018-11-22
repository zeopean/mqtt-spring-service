## 项目环境

- 开发环境是

```
mac + activateMQ + springboot + redis

```

- 测试工具

```
谷歌插件 mqttBox
```

- 环境构建

```
使用 maven 进行构建，所需要的包在 pom.xml

```

- 实现思路

```
使用 springboot 的定时任务，对 mqtt 请求进行监听，暂时用 redis 保存 topic，并在 topic 更新时，重连服务，保证服务实时性
```


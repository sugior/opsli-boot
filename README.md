# OPSLI 快速开发平台 (已开源)
<div align="center">
 <img width="500" src="https://gitee.com/hiparker/opsli-ui/raw/master/repository-images/logo.png"/>
 <br/> <br/>

[![AUR](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg)](https://github.com/hiparker/opsli-boot/blob/master/LICENSE) [![spring-boot](https://img.shields.io/badge/spring--boot-2.3.3.RELEASE-green.svg)](http://spring.io/projects/spring-boot) [![mybatis-plus](https://img.shields.io/badge/mybatis--plus-3.4.0-blue.svg)](http://mp.baomidou.com) [![hutool](https://img.shields.io/badge/hutool-5.6.3-blue.svg)](https://www.hutool.cn) [![Stars](https://img.shields.io/github/stars/hiparker/opsli-boot?style=flat-square&label=Stars&logo=github)](https://github.com/hiparker/opsli-boot) [![Forks](https://img.shields.io/github/forks/hiparker/opsli-boot?style=flat-square&label=Forks&logo=github)](https://github.com/hiparker/opsli-boot)
</div>

## 关于

> OPSLI 是一款的低代码快速平台，零代码开发，致力于做更简洁的后台管理系统！

> OPSLI 快速开发平台基于springboot、vue、element-ui ，项目采用前后端分离架构，热插拔式业务模块与插件扩展性高 ,代码简洁，功能丰富，开箱即用，帮助Java项目解决70%的重复工作，让开发更关注业务逻辑，既能快速提高效率，节省研发成本，同时又不失灵活性！ 

## 地址

- 官方网站: <a href="https://opsli.com" target="_blank">https://opsli.com</a>
- 演示地址: <a href="https://demo.opsli.bedebug.com" target="_blank">https://demo.opsli.bedebug.com</a>
- 文档地址: <a href="https://wiki.opsli.bedebug.com" target="_blank">https://wiki.opsli.bedebug.com</a>
- 作者博客: <a href="https://www.bedebug.com" target="_blank">https://www.bedebug.com</a>
- 问题反馈: <a href="https://github.com/hiparker/opsli-boot/issues" target="_blank">https://github.com/hiparker/opsli-boot/issues</a>
- 交流Q群: 724850675 (1群)

## 技术选型

### 前端

- vue-admin-beautiful

### 后端版本

> 单机版

| 名称             | 版本号  |      | 名称           | 版本号         |
| ---------------- | ------- | ---- | -------------- | -------------- |
| jdk版本          | ^1.8    |      | springboot版本 | ^2.3.3.RELEASE |
| mybatis-plus版本 | ^3.4.0  |      | pagehelper版本 | ^1.1.0         |
| druid版本        | ^1.1.17 |      | dynamic版本    | ^2.5.4         |
| shiro-redis版本  | ^3.3.1  |      | jwt版本        | ^3.10.3        |
| ehcache版本      | ^3.9.0  |      | easyexcel版本  | ^2.2.6         |
| kaptcha版本      | ^0.0.9  |      | guava版本      | ^29.0-jre      |
| enjoy版本        | ^4.9.03 |      | hutool版本     | ^5.6.3         |

## 在线演示

> - 地址：<a href="https://demo.opsli.bedebug.com" target="_blank">https://demo.opsli.bedebug.com</a>
> - 账号：demo
> - 密码：Aa123456

--------------------------------------------

> -  数据库监控/系统接口
> -  账号: admin
> -  密码: 123456

## 系统预览
![opsli-1](https://gitee.com/hiparker/opsli-ui/raw/master/repository-images/opsli-1.jpg)

![opsli-2](https://gitee.com/hiparker/opsli-ui/raw/master/repository-images/opsli-2.jpg)

![opsli-3](https://gitee.com/hiparker/opsli-ui/raw/master/repository-images/opsli-3.jpg)

![opsli-4](https://gitee.com/hiparker/opsli-ui/raw/master/repository-images/opsli-4.jpg)

![opsli-5](https://gitee.com/hiparker/opsli-ui/raw/master/repository-images/opsli-5.jpg)

![opsli-7](https://gitee.com/hiparker/opsli-ui/raw/master/repository-images/opsli-7.jpg)

![opsli-8](https://gitee.com/hiparker/opsli-ui/raw/master/repository-images/opsli-8.jpg)

![opsli-9](https://gitee.com/hiparker/opsli-ui/raw/master/repository-images/opsli-9.jpg)

## 代码结构
```
.
├── opsli-api                                               对外API 控制中心
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── org
│   │   │   │       └── opsli
│   │   │   │           └── api
│   │   │   │               ├── base                          API 基础
│   │   │   │               ├── web                           API Web层
│   │   │   │               └── warpper                       API 封装对象
│   │   │   │
├── opsli-base-support                                      基础模块
│   ├── opsli-common                                          基础 - 公共模块
│   │   ├── src
│   │   │   ├── main
│   │   │   │   ├── java
│   │   │   │   │   └── org
│   │   │   │   │       └── opsli
│   │   │   │   │           └── common
│   │   │   │   │               ├── annotation                  公共模块 - 注解类
│   │   │   │   │               ├── api                         公共模块 - Result内部文件(暂时无用)
│   │   │   │   │               ├── base                        公共模块 - 基础类
│   │   │   │   │               ├── constants                   公共模块 - 常量
│   │   │   │   │               ├── enums                       公共模块 - Enums
│   │   │   │   │               ├── exception                   公共模块 - 异常类
│   │   │   │   │               ├── msg                         公共模块 - 信息
│   │   │   │   │               ├── thread                      公共模块 - 线程相关
│   │   │   │   │               │   ├── refuse                      拒绝策略线程池
│   │   │   │   │               │   └── wait                        等待线程池
│   │   │   │   │               └── utils                       公共模块 - 工具类
│   │   │   │   │
│   └── opsli-core                                          基础 - 核心模块
│       ├── src
│       │   ├── main
│       │   │   ├── java
│       │   │   │   └── org
│       │   │   │       └── opsli
│       │   │   │           └── core
│       │   │   │               ├── api                         核心模块 - API接口相关（登录Token缓存）
│       │   │   │               ├── autoconfigure               核心模块 - 自动配置
│       │   │   │               │   ├── conf                      自动装配
│       │   │   │               │   └── properties                配置文件注入
│       │   │   │               ├── base                        核心模块 - 基础类 Entity Service
│       │   │   │               ├── cache                       核心模块 - 缓存处理
│       │   │   │               ├── filters                     核心模块 - 过滤器
│       │   │   │               │   ├── aspect                     AOP切面
│       │   │   │               │   └── interceptor                Spring拦截器
│       │   │   │               ├── conf                        核心模块 - 全局统一自动装配
│       │   │   │               ├── general                     核心模块 - 打印信息
│       │   │   │               ├── handler                     核心模块 - 异常拦截处理
│       │   │   │               ├── listener                    核心模块 - 系统监听器
│       │   │   │               ├── msg                         核心模块 - 信息
│       │   │   │               ├── persistence                 核心模块 - 查询条件构造器
│       │   │   │               │   └── querybuilder
│       │   │   │               │       └── chain               核心模块 - 查询条件构造器 - 责任链(例: 租户处理)
│       │   │   │               ├── security                    核心模块 - 权限验证
│       │   │   │               │   └── shiro                       Shiro权限校验
│       │   │   │               └── utils                       核心模块 - 工具类
│       │   │   │
├── opsli-modulars                                        业务
│   ├── opsli-modulars-generator                              代码生成器
│   │   ├── src
│   │   │   ├── main
│   │   │   │   ├── java
│   │   │   │   │   └── org
│   │   │   │   │       └── opsli
│   │   │   │   │           ├──core                           代码生成器 - 核心包
│   │   │   │   │           │   └── generator
│   │   │   │   │           │       ├── enums                   代码生成器 - 枚举类
│   │   │   │   │           │       ├── exception               代码生成器 - 异常类
│   │   │   │   │           │       ├── msg                     代码生成器 - 信息类
│   │   │   │   │           │       ├── strategy                代码生成器 - 策略类
│   │   │   │   │           │       └── utils                   代码生成器 - 工具包
│   │   │   │   │           │
│   │   │   │   │           └── modulars                      代码生成器 - 业务包
│   │   │   │   │               └── generator
│   │   │   │   │                   ├── column                  代码生成器 - 表结构
│   │   │   │   │                   ├── createrlogs             代码生成器 - 生成记录
│   │   │   │   │                   ├── general                 代码生成器 - 同步数据库执行器
│   │   │   │   │                   ├── importable              代码生成器 - 数据库导入
│   │   │   │   │                   └── table                   代码生成器 - 表管理
│   │   │   │   │
│   └── opsli-modulars-system                               系统模块
│       ├── src
│       │   ├── main
│       │   │   ├── java
│       │   │   │   └── org
│       │   │   │       └── opsli
│       │   │   │           └── modulars
│       │   │   │               ├── system                    系统模块 - 系统配置
│       │   │   │               │   ├── area                    系统模块 - 地域
│       │   │   │               │   ├── dict                    系统模块 - 字典
│       │   │   │               │   ├── login                   系统模块 - 登录
│       │   │   │               │   ├── logs                    系统模块 - 日志
│       │   │   │               │   ├── menu                    系统模块 - 菜单
│       │   │   │               │   ├── monitor                 系统模块 - 系统监控
│       │   │   │               │   ├── options                 系统模块 - 参数配置
│       │   │   │               │   ├── org                     系统模块 - 组织机构
│       │   │   │               │   ├── role                    系统模块 - 角色
│       │   │   │               │   ├── tenant                  系统模块 - 租户
│       │   │   │               │   └── user                    系统模块 - 用户
│       │   │   │               │
│       │   │   │               └── tools                     工具包
│       │   │   │                   ├── searchhis               工具包 - 搜索历史
│       │   │   │                   └── email                   工具包 - 邮件包
│       │   │   │
├── opsli-plugins                                         插件
│   ├── opsli-plugins-ehcache                               Ehcache缓存插件 (二级缓存)
│   │   ├── src
│   │   │   ├── main
│   │   │   │   ├── java
│   │   │   │   │   └── org
│   │   │   │   │       └── opsli
│   │   │   │   │           └── plugins
│   │   │   │   │               └── cache
│   │   │   │   │                   ├── conf                  Ehcache缓存插件 - 自动装配
│   │   │   │   │                   ├── msg                   Ehcache缓存插件 - 信息
│   │   │   │   │                   └── service               Ehcache缓存插件 - 服务
│   │   │   │   │
│   ├── opsli-plugins-excel                                 Excel插件
│   │   ├── src
│   │   │   ├── main
│   │   │   │   ├── java
│   │   │   │   │   └── org
│   │   │   │   │       └── opsli
│   │   │   │   │           └── plugins
│   │   │   │   │               └── excel
│   │   │   │   │                   ├── annotation            Excel插件 - 注解
│   │   │   │   │                   ├── exception             Excel插件 - 异常类
│   │   │   │   │                   ├── factory               Excel插件 - 工厂
│   │   │   │   │                   ├── listener              Excel插件 - 监听器
│   │   │   │   │                   └── msg                   Excel插件 - 信息
│   │   │   │   │
│   └── opsli-plugins-redis                                 Redis缓存插件(一级缓存)
│   │   ├── src
│   │   │   ├── main
│   │   │   │   ├── java
│   │   │   │   │   └── org
│   │   │   │   │       └── opsli
│   │   │   │   │           └── plugins
│   │   │   │   │               └── redis
│   │   │   │   │                   ├── conf                  Redis缓存插件 - 自动装配
│   │   │   │   │                   ├── exception             Redis缓存插件 - 异常类
│   │   │   │   │                   ├── lock                  Redis缓存插件 - 分布式锁
│   │   │   │   │                   ├── msg                   Redis缓存插件 - 信息
│   │   │   │   │                   ├── pushsub               Redis缓存插件 - 消息订阅
│   │   │   │   │                   └── scripts               Redis缓存插件 - 脚本处理
│   │   │   │   └── resources
│   │   │   │   │    └── lua                                  Redis缓存插件 - Lua脚本
│   │   │   │   │
│   └── opsli-plugins-redisson                                Redisson分布式锁
│   │   ├── src
│   │   │   ├── main
│   │   │   │   ├── java
│   │   │   │   │   └── org
│   │   │   │   │       └── opsli
│   │   │   │   │           └── plugins
│   │   │   │   │               └── redis
│   │   │   │   │                   ├── annotation            Redisson分布式锁 - 注解
│   │   │   │   │                   ├── conf                  Redisson分布式锁 - 自动装配
│   │   │   │   │                   ├── constant              Redisson分布式锁 - 常量
│   │   │   │   │                   ├── enums                 Redisson分布式锁 - 枚举类
│   │   │   │   │                   ├── properties            Redisson分布式锁 - 配置类
│   │   │   │   │                   └── strategy              Redisson分布式锁 - 策略
│   │   │   │   │
│   └── opsli-plugins-waf                                     Waf软防火墙
│       ├── src
│       │   ├── main
│       │   │   ├── java
│       │   │   │   └── org
│       │   │   │       └── opsli
│       │   │   │           └── plugins
│       │   │   │               └── redis
│       │   │   │                   ├── conf                  Waf软防火墙 - 自动装配
│       │   │   │                   ├── filter                Waf软防火墙 - 拦截器
│       │   │   │                   ├── msg                   Waf软防火墙 - 信息
│       │   │   │                   ├── properties            Waf软防火墙 - 配置类
│       │   │   │                   ├── servlet               Waf软防火墙 - Servlet处理器
│       │   │   │                   └── util                  Waf软防火墙 - 工具包
│       │   │   │
└── opsli-starter                                        启动类
    └── src
        └── main
            ├── java
            │   └── org
            │       └── opsli                              启动类 - 启动器
            └── resources                                  启动类 - 配置
                └── config                                 启动类 - 其他配置
```

## 授权协议声明

------------

1. 已开源的代码，授权协议采用 Apache Licence v2 进行发行。
2. 您可以免费使用、修改和衍生代码，但不允许修改后和衍生的代码做为闭源软件发布。
3. 修改后和衍生的代码必须也按照AGPL协议进行流通，对修改后和衍生的代码必须向社会公开。
4. 如果您修改了代码，需要在被修改的文件中进行说明，并遵守代码格式规范，帮助他人更好的理解您的用意。
5. 在延伸的代码中（修改和有源代码衍生的代码中）需要带有原来代码中的协议、版权声明和其他原作者规定 需要包含的说明（请尊重原作者的著作权，不要删除或修改文件中的@author信息）。
6. 您可以应用于商业软件，但必须遵循以上条款原则（请协助改进本作品）。
7. 您若套用本平台的一些代码或功能参考，需要在您的软件介绍明显位置说明出处。

------------

1. The open source code, the license agreement adopts Apache Licence v2 for distribution.
2. You can use, modify and derive the code for free, but it is not allowed to release the modified and derived code as closed source software.
3. The modified and derived code must also be circulated in accordance with the AGPL agreement, and the modified and derived code must be disclosed to the public.
4. If you modify the code, you need to explain it in the modified file and follow the code format specification to help others better understand your intentions.
5. In the extended code (modified and derived code from the source code), the agreement, copyright notice and other instructions required by the original author must be included in the original code (please respect the original author’s copyright and do not delete or modify @Author information in the file).
6. You can apply to commercial software, but you must follow the principles of the above terms (please help improve this work).
7. If you apply some codes or function references of this platform, you need to explain the source in an obvious place in your software introduction.

## 作者寄语

感谢Star，感恩相遇，愿世间美好与我们环环相扣，加油！屏幕前的我们，打破桎梏，坚守初心。其实人生改变命运的机会并没有太多，我们并不是不优秀，我们也并不是一无是处，我们也希望驻足山巅被众人仰望，也许我们缺少的只是一个机会，缺少的只是生命中的导师，我希望这个框架帮助到更多的人，希望有一天，我们面试的时候不再胆怯，希望有一天别人看到的不仅仅是你的努力，还有你的功成名就，出人头地。

## 鸣谢

- <a href="https://www.jetbrains.com/?from=opsli-boot" rel="nofollow">感谢 JetBrains 提供的免费开源 License:</a>

    <a href="https://www.jetbrains.com/?from=opsli-boot" rel="nofollow"><img src="https://camo.githubusercontent.com/a4b533abbf1bd277a3943956fa8bed240b02184ccc6b5e9f751ae2f5afd7cfa6/687474703a2f2f7374617469632e786b636f64696e672e636f6d2f737072696e672d626f6f742d64656d6f2f3036343331322e6a7067" width="100px" alt="jetbrains"  style="max-width:100%;"></a>

## 贡献者列表

[![contributors](https://whnb.wang/contributors/hiparker/opsli-boot)](https://github.com/hiparker/opsli-boot)

## Stars 趋势

### Gitee
[![Stargazers over time](https://whnb.wang/stars/hiparker/opsli-boot)](https://github.com/hiparker/opsli-boot)

### Github
[![Stargazers over time](https://starchart.cc/hiparker/opsli-boot.svg)](https://github.com/hiparker/opsli-boot)


## 支持

> 谢谢您愿意支持开源

<div align="center">
<img width="200" src="https://gitee.com/hiparker/opsli-ui/raw/master/repository-images/vx.png"/>
<img width="200" src="https://gitee.com/hiparker/opsli-ui/raw/master/repository-images/zfb.png"/>
</div>


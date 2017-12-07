# 大白网盘

> 小型网盘存储系统
> >## **Mr.Da**
> <img src="http://i.imgur.com/zinCKRK.png" width="" height="35"/>
>

## 初步设想

整个项目采用前后端分离的方式进行布局。

### 使用技术

1. 后台语言：JAVA
2. 前台使用：node.js+icop平台组件库
3. 包管理：Maven
4. 数据库：MongoDB（Oracle）
5. 框架：SpringMVC+Spring

考虑数据库是否使用MongoDb如果使用MongoDB则不使用mybatis框架，如果使用Oracle数据库则采用SSM框架。

### 主要功能

1. 模拟仿照百度网盘，实现用户管理和文件管理。
2. 用户登录注册找回密码，个人信息管理
3. 用户文件上传下载分享保存
4. 管理员管理用户（待定是否需要管理员）

### logo设计

![](https://i.imgur.com/43KmWoO.png)

## 项目目录
	
	——yylib-sample  --前端
	——dabai --后端
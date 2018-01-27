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

### 数据库设计

![](https://i.imgur.com/cWkGHE1.png)

暂定为这样，有不少地方后续进行修改。

### 权限设想

初步设想

| 权限 | 普通用户| VIP用户|
| :--: | :--: | :--: |
| 存储容量 | 500G | 2T |
| 单文件上传限制 | 2G | 3G |
| 转存文件限制 | <1000 | <3000|
| 批量文件上传限制 | <100 | < 500 |
| 回收站有效期 | 10天 | 15天 |
| 云解压功能 | 否 | 500M以内|
| 云视频播放 | 否 | 是|
| 文档在线编辑 | 否 | 是|
| 全文检索 | 否 | 否|
| 急速下载 | 否 | 是|
| 身份标识 | 否 | Ⅵ |
| 垃圾文件清除 | 否 | Md5扫描相同文件 |
| 建群 | 否 | 可以 |

**注意：**VIP用户功能不采用充值方式实现。不能实现实时充值到账，支付宝、微信等支付不能申请接口。


## 项目目录

<a href="https://github.com/dazhang12138/JAVA-Web/blob/master/DaBaiNetworkStorage/DBCatalog.md">项目目录DBCatalog.md</a>
	
# 功能设想

> 这里只是简单的对系统功能的设想，具体实现在实现记录中记录。

## 主要功能

1. 用户登录注册
2. 用户上传资源到网盘
3. 用户下载网盘资源到本地
4. 用户分享资源（私有分享、用户公共分享）

## 更新日志

<a href="https://github.com/dazhang12138/JAVA-Web/blob/Mr.Da/DaBaiNetworkStorage/DBLog.md">日志文件DBLog.md</a>

## BUG记录

<a href="https://github.com/dazhang12138/JAVA-Web/blob/master/DaBaiNetworkStorage/DBBug.md">BUG文件DBBug.md</a>

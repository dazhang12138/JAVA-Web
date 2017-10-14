# Photography

> 摄影网站：
> 
> 实现：java+jsp+oracle

## 网站理念

Photography摄影网站是一个关于摄影图片的社区平台。

在社区里可以上传下载图片、赚取积分等最基本的功能。

我创立这个网站，为所有的摄影爱好者提供最好的摄影平台.致力于摄影协同发展.无论战争、无论和平.

在这里你可以自由的评论我们，也可以上传自己的作品供人欣赏.平台还在成长中，需要我们共同维护这里，如果你有好的建议或意见，可以联系我们.

## 项目环境


- <b>编译环境 ： Eclipce</b>
- <b>系统环境 ： Windows 10</b>
- <b>使用语言 ： JAVA+HTML+CSS+JavaScript</b>
- <b>数据库 ： Oracle</b>
- <b>涉及关键技术 ： Ajax+Jstl+Log4j+smartupload+mail</b>

## 主要实现功能

> 网站主要目的为摄影展示
> 
> 网站主要人员关系为用户和管理员（不同的管理）
> 
> 网站图片的关系为专辑和图片（专辑内包含图片）

### 用户相关

- 登录、注册、邮箱找回密码（需要连通网络并能连接上163邮箱服务；源码中去除了发送邮箱的账号密码）
- 查看专辑、查看图片（图片处理防盗图系统，页面显示为防盗图片，显示专辑的时候没有完全实现无限加载瀑布流样式）
- 下载图片、专辑（扣除相应积分并给专辑创建者增加相应积分）
- 每日签到赚取积分
- 管理自己的专辑、图片（均支持增、删、改、查）
- 修改个人信息、申请管理员
- 查看个人积分动态
- 充值（由于个人项目各种接口不好实现，采用扫码支付管理员审批方案；）
- 积分兑换（网站积分与人民币兑换比例：1元兑换100积分）


### 管理员相关

> 支持所有普通用户操作，并增加以下权限

- 管理所有专辑、图片、用户（专辑、图片均支持删、改、查；用户只支持删、查）
- 查看所有积分动态
- 审批用户申请管理员（通过、拒绝发送邮件提醒）
- 审批用户充值

## 未实现功能

- 管理时可以多条件查询（一个必要的功能，但是个人觉得不必要）
- 注册改为手机号注册、邮箱注册、多第三方接口登录
- 专辑评论系统
- 主页面显示专辑完全实现无限瀑布流样式
- 各大社交网站分析

## 网站关键截图

> 主页面：
![](https://i.imgur.com/H08zyw8.jpg)

![](https://i.imgur.com/wit8gxh.jpg)

![](https://i.imgur.com/v0Guujw.jpg)

![](https://i.imgur.com/jXyllgL.jpg)

![](https://i.imgur.com/tds8vFa.png)

![](https://i.imgur.com/6mW0vt5.png)

![](https://i.imgur.com/VWGhkzk.png)

![](https://i.imgur.com/CaHe8te.png)

![](https://i.imgur.com/RbviAR3.png)

> 管理页面

![](https://i.imgur.com/Pq33WNN.png)

![](https://i.imgur.com/jUV0GZ1.png)

![](https://i.imgur.com/8QJYIT5.png)

![](https://i.imgur.com/rfsvCjI.png)

![](https://i.imgur.com/HdEVcbF.png)

![](https://i.imgur.com/W5PRLeS.png)

![](https://i.imgur.com/DnhUMeo.png)

![](https://i.imgur.com/CA9DWCW.png)

![](https://i.imgur.com/SOKLJ1U.png)

![](https://i.imgur.com/mZQQY26.png)

![](https://i.imgur.com/M2nHode.png)

![](https://i.imgur.com/osJjPQO.png)
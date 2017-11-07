# Photography struts2+Hibernate+Sping版本

> 将Struts2版本项目进行修改成Struts2+Hibernate+Sping版本。为SSH框架版本的项目。

> 2017-11-3

开始着手Sping的项目整合计划。

> 2017-11-6

进行了Sping框架和Struts2框架的整合，并写了Hibernate与Sping框架整合的xml文件已经一些BIz层和Dao层的改写。

但是Hibernate处运行产生空指针异常，还需要进行修改。

另外对Biz层进行了接口的封装修改。将biz包分开了biz和bizImpl包。

> 2017-11-7

进行了Sping和Hibernate的最后整合，虽然整合成功，

但是出现呢了新的问题，Sping会消除Hibernate的懒加载机制。于是在页面用到的懒加载机制都开始报错。



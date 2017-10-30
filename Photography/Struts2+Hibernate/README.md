# Photography struts2+Hibernate版本

> 将Struts2版本项目进行修改成Struts2+Hibernate项目，并逐渐修改
> 最后修改为SSH项目


> 更新Hibernate框架，修改一部分（Down处）的Dao文件。

由于使用之前的项目改到Hibernate项目，将采用大量的SQL语句的Hibernate查询，部分采用HQL语句查询。


> 2017-10-28：

这几天对项目进行了Hibernate的整合，并已经做出了搭建环境以及两个Dao层的修改，但是之前作出决定大量采用SQL语句的Hibernate进行修改，因为实体类之间的（页面Biz层以及Action中的调用）冲突问题。但通过更改这几个Dao的效果，发现代码量任然很多，需要一直在实体类之间转换，所以进行大改动，一个个功能的修改。大量采用Hibernate自动创建的实体类。

> 2017-10-30

更新Hibernate框架的Dao层代码。

还剩下几个功能的Dao层没有写。下载功能、修改一些功能。

还没有进行Hibernate的资源关闭。有些Action不能进行关闭。.否则页面不能延迟加载.

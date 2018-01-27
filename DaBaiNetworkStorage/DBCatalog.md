# 项目目录

> 小型网盘存储系统
> >## **Mr.Da**
> <img src="http://i.imgur.com/zinCKRK.png" width="" height="35"/>
>

## 前端项目

	——yylib-sample  --前端
	|	——.idea --WebStorm配置文件
	|	——apps --主要
		|	——SampleApp  --项目包
			|	——actions 
			|	——components
			|	——images  --图片包
			|	——modules  --业务模块
				|	——EnterThe  --登录登出页面
				|	——main  --主页
				|	——message  --个人信息页面
				|	——sample  --遗留页面
			|	——pages
			|	——reducers
			|	——routes  --路由
			|	——store
			|	——echarts.min.js  --Echars图表依赖js文件
			|	——index.html  --页面
			|	——manifest-vendor.json  --依赖包
			|	——vendor.dll.js
	|	——node_modules --node依赖文件包
	|	——config.SampleApp.dll.js 
	|	——config.SampleApp.js
	|	——index-SampleApp.js
	|	——npm-debug.log --node日志文件
	|	——package.json --node依赖

## 后端项目

	——icop-db --后端
	|   ——.settings  --Eclipse配置文件
	|   ——src  --实现代码
		|	——main  --主要
			|	——java  --代码
				|	——com.yyjz.icop
					|	——controller  --访问入口
						|	——FilesController.java  --文件与文件夹相关访问入口
						|	——UserController.java  --用户相关访问入口
					|	——dao  --访问数据库
						|	——impl  --接口实现
							|	——FilesDaoImpl.java  --文件与文件夹信息访问数据库方法实现
							|	——UserDaoImpl.java  --用户信息访问数据库方法实现
						|	——FilesDao.java  --文件与文件夹信息访问数据库接口
						|	——UserDao.java  --用户信息访问数据库接口
					|	——filter  --过滤器
						|	——AccessControlFilter.java  --异步访问数据过滤器
					|	——service  --逻辑实现
						|	——impl  --接口实现
							|	——FilesServiceImpl.java  --文件与文件夹信息逻辑处理方法实现
							|	——UserServiceImpl.java  --用户信息逻辑处理方法实现
						|	——FilesService.java  --文件与文件夹信息逻辑处理方法实现
						|	——UserService.java  --用户信息逻辑处理接口					
					|	——util  --公共方法
						|	——DateUtils.java  --时间类型转换类
						|	——FileType.java  --文件类型枚举类
						|	——FileUtils.java  --文件处理辅助类
						|	——GetFileType.java  --文件类型匹配类
						|	——MongoDBUtil.java  --mongoDB数据库辅助类
					|	——vo  --页面对象
						|	——DelFilesVo.java  --删除文件对象
						|	——FilesVo.java  --文件对象
						|	——QueryFilesVo.java  --文件搜索对象
						|	——UserUpdatePwdVo.java  --用户修改密码对象
						|	——UserUpdateVo.java  --用户更新信息对象
						|	——UserVo.java  --用户对象
			|	——resoureces  --配置
				|	——applicationContext.xml  --Spring配置文件
				|	——logback.xml  --日志配置文件
				|	——mongodb.properties  --mongoDB数据库连接数据
				|	——spring-mvc.xml  --SpringMVC配置文件
			|	——webapp  --web文件
				|	——WEB_INF
					|	——web.xml  --web项目配置文件
				|	——index.jsp
		|   ——test  --测试
	|   ——target
	|	——.classpath
	|	——.gitignore
	|	——.project
	|	——pom.xml  --Maven配置文件

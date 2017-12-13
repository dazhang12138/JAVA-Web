# 大白网盘 （后端）

> ## **Mr.Da**
> <img src="http://i.imgur.com/zinCKRK.png" width="" height="35"/>
>

## 数据库字段设计

### dbUser 用户表

> 专门存储用户的账户信息


**dbUser表**

| key | 介绍 | type | 说明 |
| :--: | :--: | :--: | :--: |
| _id | 账户编号 | ObjectId | 不用处理创建row时自动创建 |
| userName | 用户登录名 | String | 至少6个字符 |
| userPwd | 用户登录密码 | String | 至少6个字符 |
| name | 账户昵称 | String | 至少为2个英文字符或两个汉字 |
| userEmail | 账户预留邮箱 | String | 符合邮箱格式 |
| userStartTime | 账户注册时间 | Date | |
| fileSize | 网络硬盘使用大小 | String | 单位为B |
| maxFileSize | 网络硬盘容量 | String | 单位为B |
| uploadLimit | 单文件上传大小限制 | String | 单位为B |
| archivedLimit | 转存数量限制 | Int32 | 单次转存数量不能超过这个限制，转存为存储别人的分享文件 |
| bulkUploadLimit | 批量上传限制 | Int32 | 单次上传数量不能超过限制 |
| garbageLimit | 回收站存储限制 | Int32 | 回收站存储时间限制，单位为天 |
| limits | 账户限制 | Int32 | 账户权限（1：普通用户、2：VIP用户） |
| files | 文件夹集合 | 存储格式详见FILES表 | |


**FILES表**

| key | 介绍 | type | 说明 |
| :--: | :--: | :--: | :--: |
| filesId | 文件夹编号 | ObjectId | 需要与dbFiles表的的_id字段相对于存储 |
| files | 此文件夹下的文件夹集合 | Array | 存储格式为此表，循环嵌套 |
| file | 此文件夹下的文件集合 | Array | 存储dbFile表的_id字段 |

**dbFiles表**

| key | 介绍 | type | 说明 |
| :--: | :--: | :--: | :--: |
| _id | 文件夹编号 | ObjectId | 不用处理创建row时自动创建 |
| userId | 用户编号 | ObjectId | 需要与dbUser表_id字段对应，表明此文件夹的归属 |
| foldersName | 文件夹名称 | String | 如果是用户根目录文件夹则与账户昵称相同 |
| foldersStartTime | 文件夹创建时间 | Date | |
| foldersEndTime | 文件夹最后修改时间 | Date | |
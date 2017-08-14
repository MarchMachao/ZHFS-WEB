# 智慧服饰管理系统
## 项目介绍
本项目为一个穿戴式老年人行为健康服务平台Web端软件,该系统为“穿戴式老人行为健康服务平台”提供管理、展示的软件应用，该软件的系统功能定义：

1. 登录功能
2. 用户基础信息
3. 房间基本信息
4. 老年人的房间分布图
4. 行为数据处理
5. 综合数据分析和分类

## 项目实现简介
1. Spring做容器
2. SpringMVC做MVC控制
3. Shiro实现项目的安全管理,拦截器,角色管理等
4. Mybatis作为Orm对数据库进行操作
5. EasyUI和Bootstrap框架作为前台UI框架
6. Echarts和jQuery图表插件来作为图表工具
7. 使用七牛云作为文件服务器来存储项目中的图片,文本等文件
8. log4j做日志输出
9. 版本控制工具Git (http://www-cs-students.stanford.edu/~blynn/gitmagic/intl/zh_cn/index.html)
10. 数据库服务器: Mysql, Eclipse;   
    HTTP服务器: Apache;  
    Servlet容器: Tomcat;
11. 用Ajax作为与服务器交换数据并更新部分网页
## Shiro
#### Shiro配置
```xml
<!-- 配置缓存管理器 -->
<bean id="cacheManager" class="org.apache.shiro.cache.ehcache.EhCacheManager">
    <property name="cacheManagerConfigFile" value="classpath:ehcache.xml" />
</bean>
<!-- 授权和认证的realm -->
<bean id="myrealm" class="com.smates.dbc2.support.shiro.MyRealm"></bean>
<!-- shiro securityManager -->
<bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
    <property name="cacheManager" ref="cacheManager" />
    <property name="realm" ref="myrealm" />
</bean>
<!-- 后置处理器，会自动调用和spring整合后的组件的生命周期方法 -->
<bean id="lifecycleBeanPostProcessor" class="org.apache.shiro.spring.LifecycleBeanPostProcessor" />
<!-- shiroFilter,id和web.xml一致 -->
<bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
    <property name="securityManager" ref="securityManager" />
    <property name="loginUrl" value="/login.do" />
    <property name="successUrl" value="/home.do" />
    <property name="unauthorizedUrl" value="/logout.do" />
    <property name="filterChainDefinitions">
        <value>
            /admin/*.do = roles[1]  <!-- 角色权限限制 -->
            /logout.do = anon       <!-- 不要登录认证 -->
            /submit.do = anon       <!-- 不要登录认证 -->
            /*.do= authc            <!-- 需要登录认证 -->
        </value>
    </property>
</bean>
```
#### 项目目前权限说明
|权限值|权限名|
|----|----|
|0|普通用户|
|1|管理员|
## 七牛云文件服务器
文档地址：https://developer.qiniu.com/kodo/sdk/1662/java-sdk-6#2
#### 配置(pom.xml)
```xml
<!-- 七牛云文件存储 -->
<dependency>
    <groupId>com.qiniu</groupId>
    <artifactId>qiniu-java-sdk</artifactId>
    <version>[7.0.0, 7.0.99]</version>
</dependency>
<!-- 上传文件组件 -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>servlet-api</artifactId>
    <version>3.0-alpha-1</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.2.2</version>
</dependency>
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-io</artifactId>
    <version>1.3.2</version>
</dependency>
```
#### Demo
##### 前台html
```html
<form action="qniu.do" method="post" enctype="multipart/form-data">
    <input type="file" name="image"/>
    <input type="submit" />
</form>
```
##### 上传图片
```java
@RequestMapping(value = "admin/saveUser", method = RequestMethod.POST)
@ResponseBody
public BaseMsg createUser(MultipartFile image) {
    if (!StringUtils.isEmpty(image.getOriginalFilename())) {
        qniuHelper.uploadFile(image, "imageName");
    }
}
```
##### 删除图片
```java
@RequestMapping(value = "admin/saveUser", method = RequestMethod.POST)
@ResponseBody
public BaseMsg createUser(MultipartFile image) {
    if (!StringUtils.isEmpty(image.getOriginalFilename())) {
        qniuHelper.deleteFile("imageName");
    }
}
```
## 资源共享平台
### 功能模块

#### 模块一：成员管理（只开放查找功能）

共包括两个模块：人员信息 人员分布
##### 1. 人员信息
  功能：展现所有人员的信息（编号，姓名，性别，年龄，科室，当前位置，身体状况，图片），点击名片则显示各个房间的停留分布图，包括饼形图，条形图，网格等。其中，详细的信息目前具体到数据只有一天-------2016-09-16；
  
  主要技术：Ajax前端开发，Echarts插件和Bootstrap。




##### 2. 人员分布
  
  功能：在房间的架构图中，总共分为4个房间区域，进行分析每个房间的人数。
  
  主要技术： HTML、CSS、JavaScript和Ajax进行Web前端开发，后台主要是查询（多表联查）。


#### 模块二：系统管理（增删改查）
主要表格采用EasyUI的UI控件。

##### 数据表机构
+ 用户的数据：
+ 
|列名|属性|说明|
|------|-------|------|
|Id|int(10)|主键（ID号）|
|TagNum|varchar(40)|标签号|
|StartDate|date|开始日期|
|StartTime|varchar(50)|开始时间|
|RssiToReader|varchar(50)|读写器RSSI值|
|RssiToAntenna|varchar(40)|天线值|
|Cpid|varchar(40)|读写器编号|
|WakeupNumber|varchar(40)|激励器编号|

+ 用户时间的数据：
+ 
|列名|属性|说明|
|------|-------|------|
|Id|int(10)|主键（ID号）|
|TagNum|varchar(40)|标签号|
|StartDate|date|开始日期|
|StartTime|varchar(50)|开始时间|
|EndDate|date|结束日期|
|EndTime|varchar(50)|结束时间|
|Rssi|varchar(50)|读写器RSSI|
|Cpid|varchar(40)|读写器编号|
|WakeupNumber|varchar(40)|激励器编号|
+ 人员管理：
+ 
|列名|属性|说明|
|------|-------|------|
|TagNum|varchar(40)|主键（标签号）|
|Name|varchar（50）|姓名|
|Age|int(5)|年龄|
|Sex|varchar(20)|性别|
|MaritalStatus|varchar(40)|婚姻状况|
|health|varchar(100)|健康状况|
|image|varchar(100)|用户图片|

+ 房间管理：
+ 
|列名|属性|说明|
|------|-------|------|
|RoomId|int(10)|主键（ID号）|
|RoomNum|varchar（50）|房间编号|
|RoomName|varchar(50)|房间名字|
|Cpid|varchar(20)|读写器编号|
|WakeupNum|varchar(40)|激励器编号|


+ 菜单管理：
+ 
|列名|属性|说明|
|------|-------|------|
|MenuId|varchar(40)|主键（菜单号）|
|MenuName|varchar(40)|菜单名称|
|ParentID|varchar（50）|父菜单|
|MenuUrl|varchar(50)|菜单连接|
|OrderNo|int(10)|菜单编号|
|Permition|varchar(40)|菜单权限|

+ 用户管理：
+ 
|列名|属性|说明|
|------|-------|------|
|Id|int(10)|主键（ID号）|
|AccountNumber|varchar(40)|用户账号|
|NickName|varchar（50）|用户名称|
|Password|varchar(50)|用户密码|
|Role|int(10)|用户权限|
|CreateDate|varchar(40)|创建时间|
|E-mail|varchar(40)|用户邮箱|

## 总结

#### 菜单结构
一级菜单：成员管理 / 系统管理  
二级菜单：人员信息/ 人员分布/ 人员管理/ 房间管理/ 菜单管理/ 用户管理
#### 权限说明
人员信息/ 人员分布                       （查询）  
人员管理/ 房间管理/ 菜单管理/ 用户管理   （增删改查）



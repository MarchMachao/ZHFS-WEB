#dbc2
###项目介绍
本项目为一个控制台程序模板,可以对数据库中的内容进行基本的维护
###项目实现简介
1. spring做容器
1. springMVC做MVC控制
1. shiro实现项目的安全管理,拦截器,角色管理等
1. mybatis作为orm对数据库进行操作
1. memcache做缓存管理,加快查询较多的操作,从而提高并发,aop实现
1. mongo和morphia用来收集用户的行为数据并记录,aop实现
1. easyUI作为前台框架
1. 使用七牛云作为文件服务器来存储项目中的图片,文本等文件
1. log4j做日志输出
###memcache使用(在service层使用)
####配置缓存,缓存服务器的ip和端口
```xml
<aop:aspectj-autoproxy proxy-target-class="true" />
<bean class="com.smates.dbc2.memcache.config.MemcacheConfig" id="memcachedConfig">
    <property name="ips">
        <list>
            <value>xxx.xxx.xxx.xxx</value>
        </list>
    </property>
    <property name="ports">
        <list>
            <value>xxxxx</value>
        </list>
    </property>
    <property name="connectionPoolSize" value="10">
    </property>
</bean>
<bean class="com.smates.dbc2.memcache.CacheManager" init-method="init">
    <property name="cacheConfig" ref="memcachedConfig"></property>
</bean>
```
####读缓存
先去内存读缓存,若缓存中存在则从缓存中取到直接返回不再访问数据库,若缓存中不存在,则先去数据中读取,读到后在缓存中存放一份,然后返回给用户
#####主键生成策略
cachePrefix_para_para_para
#####demo
```java
@Override
@CacheRead(nameSpace="menu",cachePrefix="getAll")
public List<Menu> getAllMenu(@CacheKey int pageNo, @CacheKey String menuName, @CacheKey String permition, @CacheKey int pageSize) {
    CostumMenu costumMenu = new CostumMenu();
    costumMenu.setStartCount((pageNo-1)*pageSize);
    costumMenu.setMenuName(menuName);
    costumMenu.setPermition(permition);
    costumMenu.setPageSize(pageSize);
    return menuDao.getAllMenu(costumMenu);
}
```
####清缓存
清除掉namespace下对应的缓存空间
#####demo
```java
@Override
@CacheClear(nameSpace="menu")
public void deleteMenuById(String menuId) {
    menuDao.deleteMenuById(menuId);
}
```
###用户行为记录的使用（controller层使用）
####mongo数据库配置
```xml
db_ip=xxx.xxx.xxx.xxx
db_port=xxxxx
db_maper_package=com.smates.dbc2.po.UserLog
db_dbname=smatefamily
```
####demo @PersonalLog("addMenu")
```java
@RequestMapping(value = "saveMenu", method = RequestMethod.POST)
@ResponseBody
@PersonalLog("addMenu")
public BaseMsg addMenu(String menuId, String menuName, String menuUrl, String parentId, Integer order, String permition) {
    if(StringUtils.isEmpty(menuId)){
        logger.info("添加菜单项");
        menuService.addMenu(menuName, parentId, menuUrl, order, permition);
        return new BaseMsg(true, "菜单添加成功");
    }else{
        logger.info("更新菜单项");
        menuService.updateMenu(menuId, menuName, menuUrl, parentId, order, permition);
        return new BaseMsg(true, "菜单更新成功");
    }
    
}
```
###shiro
####shiro配置
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
####项目目前权限说明
|权限值|权限名|
|----|----|
|0|普通用户|
|1|管理员|
###七牛云文件服务器
####配置(pom.xml)
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
####demo
#####前台html
```html
<form action="qniu.do" method="post" enctype="multipart/form-data">
    <input type="file" name="image"/>
    <input type="submit" />
</form>
```
#####上传图片
```java
@RequestMapping(value = "admin/saveUser", method = RequestMethod.POST)
@ResponseBody
public BaseMsg createUser(MultipartFile image) {
    if (!StringUtils.isEmpty(image.getOriginalFilename())) {
        qniuHelper.uploadFile(image, "imageName");
    }
}
```
#####删除图片
```java
@RequestMapping(value = "admin/saveUser", method = RequestMethod.POST)
@ResponseBody
public BaseMsg createUser(MultipartFile image) {
    if (!StringUtils.isEmpty(image.getOriginalFilename())) {
        qniuHelper.deleteFile("imageName");
    }
}
```
###资源共享平台
####功能模块
模块一：寻找资源（只开放查找功能）
模块二：我的资源（增删改查）
####数据表机构
+ 数据库公共字段：
|列名|属性|说明|
|------|-------|------|
|id|vchar(40)|主键（资源id）|
|type|int（5）|类别|
|name|vchar(50)|资源名称|
|content|text|资源内容|
|describe|text|资源描述|
|owner|vchar(50)|资源所有人|
|createTime|date|资源创建时间|

+ 学习资源：
|列名|属性|说明|
|------|-------|------|
|url|vchar(100)|附件地址|

+ 游戏资源（新表）：
|列名|属性|说明|
|------|-------|------|
|id|vchar(40)|资源id|
|userId|text|拥有使用权限的用户|

+ vip账号：
无新字段

####菜单结构
一级菜单：资源共享
二级菜单：学习资源，游戏账号,视频网站VIP，我的资源
####权限说明
学习资源，游戏账号,视频网站VIP （查询）
我的资源                       （增删改查）
####分工
学习资源     游戏资源    负责人：汤士龙           
vip账号     我的资源    负责人：白江伟
# 智慧服饰
### 项目介绍
本项目为一个穿戴式老年人行为健康服务平台Web端软件,该系统为“穿戴式老人行为健康服务平台”提供管理、展示的软件应用，该软件的系统功能定义：

1. 登录功能
2. 用户基础信息
3. 房间基本信息
4. 老年人的房间分布图
4. 行为数据处理
5. 综合数据分析和分类

### 项目实现简介
1. Spring做容器
2. SpringMVC做MVC控制
3. Shiro实现项目的安全管理,拦截器,角色管理等
4. Mybatis作为Orm对数据库进行操作
5. EasyUI和Bootstrap框架作为前台UI框架
6. Echart和jQuery图表插件来作为图表工具
7. 使用七牛云作为文件服务器来存储项目中的图片,文本等文件
8. log4j做日志输出
9. 版本控制工具Git
10. 数据库服务器: Mysql;   
    HTTP服务器: Apache;  
    Servlet容器: Tomcat;
11. 用Ajax作为与服务器交换数据并更新部分网页
### memcache使用(在service层使用)
#### 配置缓存,缓存服务器的ip和端口
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
#### 读缓存
先去内存读缓存,若缓存中存在则从缓存中取到直接返回不再访问数据库,若缓存中不存在,则先去数据中读取,读到后在缓存中存放一份,然后返回给用户
##### 主键生成策略
cachePrefix_para_para_para
##### demo
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
#### 清缓存
清除掉namespace下对应的缓存空间
##### demo
```java
@Override
@CacheClear(nameSpace="menu")
public void deleteMenuById(String menuId) {
    menuDao.deleteMenuById(menuId);
}
```
### 用户行为记录的使用（controller层使用）
#### mongo数据库配置
```xml
db_ip=xxx.xxx.xxx.xxx
db_port=xxxxx
db_maper_package=com.smates.dbc2.po.UserLog
db_dbname=smatefamily
```
#### demo @PersonalLog("addMenu")
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
### shiro
#### shiro配置
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
### 七牛云文件服务器
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
#### demo
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
## 简介
spring-boot-ssm 是一个基于Spring Boot & Spring & Spring MVC & MyBatis的简单通用的项目，用于快速构建中小型API的后端服务系统. 可以做为一个种子项目,进行改造升级.

## 特征
- [x] Spring Boot 2                                                                 
- [x] Spring MVC 		 		
- [x] Mybatis                
- [x] Spring Boot Test
- [x] 集成通用Mapper插件、PageHelper分页插件,实现单表业务的基本操作.
- [x] 统一的Response封装,统一的异常处理,基础方法的抽象封装.
- [x] JWT(Json Web Token)安全认证
- [x] 代码自动生成工具
- [x] Druid 集成阿里Druid数据库连接池以及API和数据库等监控            		
- [x] Log日志管理
- [ ] 缓存框架

## 快速启动
1. 克隆项目
```
    git clone https://github.com/caoxile/spring-boot-ssm.git
```
2. 创建数据库

执行bin/init_db.sql脚本,初始化数据库

3. 自动生成代码
- 修改对```test```包内的代码生成器```CodeGenerator```的数据库链接等配置
```
    //JDBC配置
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project";//数据库链接
    private static final String JDBC_USERNAME = "tom";//数据库用户名
    private static final String JDBC_PASSWORD = "hellotom";//数据库密码
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";//数据库驱动类型

```
- 在```CodeGenerator.main```方法中,输入表名,然后运行.(即可在src下生成基础代码)
```
    public static void main(String[] args) {
//        genCode("auth","auth_user"); // 参数1:模块名 参数2:表名
        genCodeByCustomModelName("auth","auth_user","User"); // 参数1:模块名 参数2:表名 参数3:自定义Model名
    }
```
5. 对开发环境配置文件```application-dev.properties```进行配置(数据库).
``` 
    spring.datasource.url=jdbc:mysql://localhost:3306/project?characterEncoding=utf-8&useSSL=false
    spring.datasource.username=tom
    spring.datasource.password=hellotom
```
5. 启动
```
    //在IDE中启动,或者直接执行下面命令
    mvn spring-boot:run
```
6. 使用Postman等工具测试API
- 首先登录(/auth/login) 获取Token
- 在Header中添加Authorization,值就是登录获取的Token,再测试其他API

## 技术文档
- Spring Boot（[Spring Boot官方文档](https://spring.io/projects/spring-boot)）
- Spring MVC ([Spring MVC官方文档](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html))
- MyBatis（[官方中文文档](http://www.mybatis.org/mybatis-3/zh/index.html)）
- MyBatis 通用Mapper插件（[官方中文文档](https://mapperhelper.github.io/docs/)）
- MyBatis PageHelper分页插件（[官方中文文档](https://github.com/pagehelper/Mybatis-PageHelper/blob/master/README_zh.md)）
- Druid （[官方中文文档](https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98)）
- Druid监控（[监控配置和使用说明](https://www.caoxile.com/blog/2018/07/spring-boot-1-druid%E7%9B%91%E6%8E%A7)）
- Fastjson（[官方中文文档](https://github.com/alibaba/fastjson/wiki/Quick-Start-CN)）
- JWT(JSON Web Token) ([JWT介绍](https://www.caoxile.com/blog/2018/07/json-web-token))

## 其他说明
该项目来源于[spring-boot-api-project-seed](https://github.com/lihengming/spring-boot-api-project-seed).因为原项目没有继续维护升级,我受益于该项目,本人对项目进行了改造升级,并计划持续的改进.所以就有了现在的这个项目.这里要感谢原项目作者的开源分享.

## License
MIT



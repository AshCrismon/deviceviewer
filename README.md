Simple spring-boot project structure.

$ mvn clean spring-boot:run  # 通过内置tomcat运行spring-boot项目

# application.properties配置server.context-path=/deviceviewer为项目的访问路径
# 静态资源文件放在classpath:resources/static下面，可以直接通过/deviceviewer/*/*访问
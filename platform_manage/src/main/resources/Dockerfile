#基础镜像
FROM ubuntu:16.04
MAINTAINER wfm
ENV REFRESHED_AT 2018-03-10

#切换镜像目录，进入/usr目录
WORKDIR /usr
#在/usr/下创建jdk目录,用来存放jdk文件
RUN mkdir jdk
#在/usr/下创建tomcat目录，用来存放tomcat
RUN mkdir tomcat

#将宿主机的jdk目录下的文件拷至镜像的/usr/jdk目录下
ADD jdk1.8.0_221 /usr/jdk/
#将宿主机的tomcat目录下的文件拷至镜像的/usr/tomcat目录下
ADD apache-tomcat-8.5.46 /usr/tomcat/

#设置环境变量
ENV JAVA_HOME=/usr/jdk
ENV JRE_HOME=$JAVA_HOME/jre
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib:$CLASSPATH
ENV PATH=/sbin:$JAVA_HOME/bin:$PATH

#公开端口
EXPOSE 8080
#设置启动命令
ENTRYPOINT ["/usr/tomcat/bin/catalina.sh","run"]
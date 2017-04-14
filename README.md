Build Development Environment
========================================
1. Download JDK: [JDK]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
2. Install JDK: (for example: jdk-8u121-linux-x64.tar.gz)

    `sudo tar -zxf jdk-8u121-linux-x64.tar.gz -C /usr/lib/jvm`
3. Update the environment variables:

    `sudo vi /etc/profile`
    
   Add following lines:
   
        export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_121
        export JRE_HOME=${JAVA_HOME}/jre
        export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
        export PATH=${JAVA_HOME}/bin:$PATH
4. Refresh the environment variables:

    `source /etc/profile`
5. Switch symbols in Ubuntu:

    `sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/jdk1.8.0_121/bin/java 300`
    
    `sudo update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/jdk1.8.0_121/bin/javac 300`
    
    `sudo update-alternatives --install /usr/bin/jar jar /usr/lib/jvm/jdk1.8.0_121/bin/jar 300`
    
    `sudo update-alternatives --install /usr/bin/javah javah /usr/lib/jvm/jdk1.8.0_121/bin/javah 300`
    
    `sudo update-alternatives --install /usr/bin/javap javap /usr/lib/jvm/jdk1.8.0_121/bin/javap 300`
    
    `sudo update-alternatives --config java`
6. Install Tomcat and MySQL:

    `sudo apt-get install -yqq mysql-server tomcat7`
    
    `sudo service mysql start`
7. Install Git and pull source from GitHub: (workspace for example: /home/ubuntu/workspace/webchat)

    `sudo apt-get install git`
    
    `git clone https://github.com/webchatgroup/webchat.git`
8. Get into workspace and build the sources

    `mvn package -DskipTests`
9. Configure Tomcat

    `sudo vi /var/lib/tomcat7/conf/server.xml`
    
   Add following lines between `<Host>` and `</Host>`
   
    `<Context path="/api" docBase="/home/ubuntu/workspace/webchat/target/WeChatApp-0.0.1-SNAPSHOT.war" debug="0" reloadable="true" crossContext="true"/>`
10. Run Tomcat *DON'T RUN TOMCAT OR ANY SERVICE WITH ROOT ON PRODUCTION SERVER*

    `sudo /usr/share/tomcat7/bin/catalina.sh run`
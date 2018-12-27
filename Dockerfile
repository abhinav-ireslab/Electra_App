#FROM java:8  
#RUN whoami
#RUN setfacl -m g:docker /usr/share/tomcat8/.jenkins/workspace/Sendx/target
#COPY /usr/share/tomcat8/.jenkins/workspace/Sendx/target/sendx-0.0.1-SNAPSHOT.jar /usr/share/tomcat8/.jenkins/workspace/Sendx/sendx-0.0.1-SNAPSHOT.jar
#WORKDIR /usr/share/tomcat8/.jenkins/workspace/Sendx
#CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]


FROM java:8  
COPY /target/sendx-0.0.1-SNAPSHOT.jar /target/sendx-0.0.1-SNAPSHOT.jar
WORKDIR server/target
CMD ["java","-jar","/target/sendx-0.0.1-SNAPSHOT.jar"]

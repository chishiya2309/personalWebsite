FROM maven:3.9-eclipse-temurin-21 AS build

# Tạo thư mục làm việc
WORKDIR /app

# Copy file pom.xml và thư mục source code vào Docker
COPY pom.xml .
COPY src ./src

# Chạy lệnh build (bỏ qua test để build nhanh hơn và tránh lỗi môi trường)
RUN mvn clean package -DskipTests

FROM tomcat:9.0-jdk21

# Xoá các ứng dụng mặc định của Tomcat để nhẹ và an toàn
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy file .war từ Stage 1 sang thư mục webapps của Tomcat
# Đổi tên thành ROOT.war để app chạy ngay tại đường dẫn gốc (/)
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Mở port 8080
EXPOSE 8080

# Chạy Tomcat
CMD ["catalina.sh", "run"]
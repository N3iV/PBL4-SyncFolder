# SyncFolderPBL4: BackEnd
# Information

 - **Back-end language**: Java
 - **ORM Framework**: JPA/Hibernate
 - **RESTful API**: Servlet, Gson (library)

# Config project

 - B1: Fetch Code 
 - B2: Open code with Eclipse
 - B3: Set up maven project: Right Click on the project -> Select Maven -> Update Project -> OK
 - B4: Set up tomcat server
 - B5: Create Schema name is **pbl4** in MySQL Server
 - B6: Open folder database( in Project ) -> **create_db.sql** -> Run it in MySQL Server
 - B7:  Run App : Right Click on the project -> Debug As -> Debug On Server ( select tomcat server)

# Initial Data
- B1: Create folder **StoreLocation** in 
PBL4\PBL4-SyncFolder\BackendPBL4\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SyncFolderPBL4
- B2: Copy all folder from **StoreLocation** in Project -> Data\StoreLocation to folder **StoreLocation** at address above
 
# Current Feature
**User**:
- Register
- Login
- Read Folder
- Read File (pdf, txt)

```
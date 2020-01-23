# contract

добавить в файл \conf\Catalina\localhost\context.xml.default
 <Context>  
  <Resource name="jdbc/Postgres" auth="Container"
				  type="javax.sql.DataSource" username="{your username}" password="{your password}"
				  driverClassName="org.postgresql.Driver"
				  url="{your url}"
				  maxActive="36" maxIdle="8"/>
</Context>

когда томкат запущен сделать package 

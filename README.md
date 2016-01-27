#Java 代码生成工具
###主要针对数据库表结构生成 Java POJO，JAVA DAO,JAVA DAOImpl 
	目前支持MYSQL
		1. 支持生成基本的JAVA POJO 实体类。
		2. 支持生成基于Hibernate (注解版)实体类
		3. 基于Hibernate 的DAO接口
		4. 基于Hibernate 的DAO 接口的实现类。


####代码入口
	#com.generated.code.RunService.main(String args[])
	
	#修改数据库连接配置
	    String datebase;// 数据库名
	    String user;//账户名
	    String pwd;//密码
	    String host;//数据库IP
	    int port;//端口
	#修改生成代码的包路径
		/* POJO类的包路径 */
	  	static String POJO_PACK = "com.xxx.model";
	  	/* DAO接口的包路径 */
	  	static String DAO_PACK = "com.xxx.dao";
	  	/* DAO实现类的包路径 */
	  	static String DAO_IMPL_PACK = "ccom.xxx.dao.impl";
	#生成代码保存路径
		public static final String HOME = "/data/code";
      
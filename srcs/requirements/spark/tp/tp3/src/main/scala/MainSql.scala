import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.log4j.{Level, Logger}
import java.util.{Properties}
// import io.github.cdimascio.dotenv.Dotenv


object MainSql {
  def main(args: Array[String]): Unit = {
    // Initialize SparkSession
    // val dotenv = Dotenv.load()
    println("===================================")
    println("        USING SQL WITH SPARK       ")
    println("===================================")

    val spark = SparkSession.builder()
      .appName("Scala Spark Database Project")
      .master("local[*]")
      .getOrCreate()
    

    val url = "jdbc:mysql://localhost:3306"
    val table = "classicmodels.customers"
    val properties = new java.util.Properties()
    properties.put("user", "root")
    properties.put("password", "P@ssw0rd")

    Class.forName("com.mysql.cj.jdbc.Driver")
    val customersDF = spark.read.jdbc(url, table, properties)
    val paymentsDF = spark.read.jdbc(url, "classicmodels.payments", properties)
    // Read data from the database 
    customersDF.show()
    paymentsDF.show()

    customersDF.printSchema()
    paymentsDF.printSchema()
    customersDF.createOrReplaceTempView("customers")
    paymentsDF.createOrReplaceTempView("payments")
    val query_1 = "SELECT state, COUNT(*) AS count FROM customers GROUP BY state"
    val query_2 = "select min(amount) as min, max(amount) as max from payments"
    val customersDF1 = spark.sql(query_1)
    val paymentsDF1 = spark.sql(query_2) 
    // val customersDF2 = /spark.read.jdbc(url, query_1, properties)
    // customersDF1.show()
    // customersDF1.printSchema()
    // val paymentsDF1 = spark.sql(query_2)
    // val customersDF2 = spark.read.jdbc(url, query, properties)
    // val paymentsDF1 = spark.read.jdbc(url, query_2, properties)

    spark.stop()
  }
}
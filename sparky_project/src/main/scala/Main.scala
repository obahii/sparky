import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}
object MainApp {
  def main(args: Array[String]): Unit = {
    // Initialize SparkSession
    val spark = SparkSession.builder()
      .appName("Scala Spark Project")
      .master("local[*]")
      .getOrCreate()

    val sc = spark.sparkContext
    // Read CSV file
    val filePath = "src/main/data/empData.csv"
    val rdd = sc.textFile(filePath)
    val header = rdd.first()
    rdd.collect().foreach(line => if (line != header) println(line))
    spark.stop()
  }
}
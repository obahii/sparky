import org.apache.spark.sql.SparkSession

object MainApp {
  def main(args: Array[String]): Unit = {
    // Initialize SparkSession
    val spark = SparkSession.builder()
      .appName("Scala Spark Project")
      .master("local[*]")
      .getOrCreate()

    // Read CSV file
    val filePath = "src/main/data/empData.csv"
    val df = spark.read.option("header", "true").csv(filePath)

    // Display data without header
    println("Data without header:")
    df.show(5, false)

    // Create a new DataFrame with specific fields
    val selectedFieldsDF = df.select("ename", "designation", "salary")

    // Display selected fields
    println("Selected fields (ename, designation, salary):")
    selectedFieldsDF.show(5, false)

    // Stop SparkSession
    spark.stop()
  }
}
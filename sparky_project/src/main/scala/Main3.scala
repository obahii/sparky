import org.apache.spark.sql.{SparkSession, Row}
import org.apache.spark.sql.types.{StructType, StructField, IntegerType, StringType}

object MainApp3 {
  def main(args: Array[String]): Unit = {
    // Initialiser SparkSession
    val spark = SparkSession.builder()
      .appName("Scala Spark Project")
      .master("local[*]")
      .getOrCreate()

    // Lire le fichier CSV et créer un DataFrame
    val filePath = "src/main/data/empData.csv"
    val df = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(filePath)

    // Afficher le schéma et le contenu du DataFrame
    df.printSchema()
    df.show()

    // Créer un nouveau DataFrame affichant le nombre des employées par département
    df.createOrReplaceTempView("employees")
    val deptCountDF = spark.sql("SELECT department, COUNT(*) as count FROM employees GROUP BY department")

    // Afficher le DataFrame avec le nombre des employées par département
    deptCountDF.show()

    // Afficher les employées du département 30
    val deptITDF = spark.sql("SELECT * FROM employees WHERE department = 'IT'")
    deptITDF.show()

    // Arrêter SparkSession
    spark.stop()
  }
}
import org.apache.spark.sql.{SparkSession, Row}
import org.apache.spark.sql.types.{StructType, StructField, IntegerType}

object MainApp2 {
  def main(args: Array[String]): Unit = {
    // Initialiser SparkSession
    val spark = SparkSession.builder()
      .appName("Scala Spark Project")
      .master("local[*]")
      .getOrCreate()

    // Créer un RDD à partir d'un tableau d'entiers
    val sc = spark.sparkContext
    val intArray = Array(1, 2, 3, 4, 5)
    val rdd = sc.parallelize(intArray)

    // Convertir chaque ligne du RDD en un objet de type Row
    val rowRDD = rdd.map(num => Row(num))

    // Créer le schéma du DataFrame
    val schema = StructType(Array(StructField("Nombres", IntegerType, nullable = false)))

    // Créer un DataFrame via la méthode createDataFrame()
    val df = spark.createDataFrame(rowRDD, schema)

    // Afficher le DataFrame créé et son schéma
    df.show()
    df.printSchema()

    // Arrêter SparkSession
    spark.stop()
  }
}
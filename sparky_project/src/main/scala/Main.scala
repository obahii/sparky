import org.apache.spark.sql.SparkSession
import org.apache.spark.rdd.RDD

object MainApp {
  def main(args: Array[String]): Unit = {
    // Initialiser SparkSession
    val spark = SparkSession.builder()
      .appName("Scala Spark Project")
      .master("local[*]")
      .getOrCreate()

    // Lecture du fichier CSV
    val filePath = "src/main/data/empData.csv"
    val rdd: RDD[String] = spark.sparkContext.textFile(filePath)

    // Supprimer l'entête
    val header = rdd.first()
    val rddWithoutHeader = rdd.filter(line => line != header)

    // Afficher les données sans entête
    println("Données sans entête :")
    rddWithoutHeader.take(5).foreach(println)

    // Créer un nouveau RDD contenant les champs spécifiques
    val selectedFieldsRDD = rddWithoutHeader.map { line =>
      val cols = line.split(",")
      (cols(1), cols(2), cols(3)) // ename, designation, salary
    }

    // Afficher les champs sélectionnés
    println("Champs sélectionnés (ename, designation, salaire) :")
    selectedFieldsRDD.take(5).foreach(println)

    // Fermer SparkSession
    spark.stop()
  }
}

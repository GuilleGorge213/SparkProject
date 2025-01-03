package org.sparkTest;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;


public class Main {
    public static void main(String[] args) {


        // Ruta del archivo CSV
        String rutaArchivoCSV = "/Users/gorge213/Desktop/Spark/SparkProject/src/main/resources/Data.csv";

        // Crear una sesión de Spark
        SparkSession spark = SparkSession.builder()
                .appName("LeerCSVConSpark")
                .master("local[*]")
                .getOrCreate();
        try {
            // Leer el archivo CSV en un DataFrame
            Dataset<Row> datos = spark.read()
                    .option("header", "true")  // Indica que el archivo CSV tiene encabezados
                    .option("inferSchema", "true")
                    .option("delimiter", ";")
                    .csv(rutaArchivoCSV);

            // Mostrar el esquema del DataFrame
            System.out.println("Esquema del DataFrame:");
            datos.printSchema();

            // Mostrar una muestra de datos
            System.out.println("Muestra de datos:");
            datos.show(10);  // Muestra las primeras 10 filas

        } catch (Exception e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        } finally {
            // Detener la sesión de Spark
            spark.stop();
        }
    }
}
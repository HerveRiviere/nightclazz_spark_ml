# nightclazz_spark_ml
Nighclazz Zenika 10 mars 2015 : Spark &amp; Machine Learning


#Create Spark-zeppelin Docker container

<pre>
docker run -d -p 8080:8080 -p 8084:8084 -p 4040:4040  -e "SPARK_WORKER_CORES=3" -e "SPARK_WORKER_MEMORY=800m" -v ~/nightclazz_spark_ml:/home/udl_spark --name=nightclazz_spark herveriviere/spark_zeppelin_standalone:v4
</pre>


# Connect to zeppelin

Inside a webbrowser : localhost:8084


# Import titanic notebook

In the Zeppelin front page (Welcome to zeppelin), click on "Import note" (on left below "Notebook")
Enter Titanic as notebook name
Click on "Add from url"
use the following url "https://raw.githubusercontent.com/hriviere/nightclazz_spark_ml/master/notebook_zeppelin/Titanic.json"








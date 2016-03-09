# nightclazz_spark_ml
Nighclazz Zenika 10 mars 2015 : Spark &amp; Machine Learning

#Prerequisite 
A docker environment
Installation guide here : https://github.com/hriviere/nightclazz_spark_ml/blob/master/installation_docker_spark_zeppelin.pdf

#Create Spark-zeppelin Docker container

<pre>
docker run -d -p 8080:8080 -p 8084:8084 -p 4040:4040  -e "SPARK_WORKER_CORES=3" -e "SPARK_WORKER_MEMORY=800m" -v ~/nightclazz_spark_ml:/opt/zeppelin/notebook --name=spark_zeppelin_standalone zenika/spark_zeppelin_standalone:latest
</pre>


# Connect to zeppelin

Inside a webbrowser : localhost:8080


# Import titanic notebook

* In the Zeppelin front page (Welcome to zeppelin), click on "Import note" (on left below "Notebook")
* Enter Titanic-1 as notebook name
* Click on "Add from url"
* use the following url https://raw.githubusercontent.com/hriviere/nightclazz_spark_ml/master/notebook_zeppelin/01%20-%20Titanic%20_%20Charger%20et%20explorer%20les%20donn%C3%A9es.json

* Do the same with Titanic 2  : https://raw.githubusercontent.com/hriviere/nightclazz_spark_ml/master/notebook_zeppelin/02%20-%20Titanic%20_%20Construire%20le%20mod%C3%A8le.json
* Titanic 3  : https://raw.githubusercontent.com/hriviere/nightclazz_spark_ml/master/notebook_zeppelin/03%20-%20Titanic%20_%20Entrainer%20et%20analyser%20le%20mod%C3%A8le.json
* Titanic 4  : https://raw.githubusercontent.com/hriviere/nightclazz_spark_ml/master/notebook_zeppelin/04%20-%20Titanic%20%20_%20Utiliser%20le%20mod%C3%A8le.json
* Titanic 5  : https://raw.githubusercontent.com/hriviere/nightclazz_spark_ml/master/notebook_zeppelin/05%20-%20Titanic%20_%20Aller%20plus%20loin.json









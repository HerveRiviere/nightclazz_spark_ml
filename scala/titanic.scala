import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{RandomForestClassificationModel, RandomForestClassifier}


//Read parquet file
sqlContext.read.parquet("/home/udl_spark/data/titanic.parquet").registerTempTable("titanic")


//Read Data
sqlContext/sql("select * from titanic").show



//Store Data as DataFrame

val df = sqlContext.sql("select Pclass, sex ,age,SibSp,Parch,Fare,embarked , Double(survived) survived from titanic where age is not null")
df.show(5)



//FEATURE EXTRACTIONS

//Convert Sex into Double 
val sexIndexer = new StringIndexer()
  .setInputCol("sex")
  .setOutputCol("indexedSex")
  .fit(df)
  
//See what transformation was made
val debugDFSexIndexed = sexIndexer.transform(df)//Debug regarder comment est transformer la variable sex
debugDFSexIndexed.select("sex","indexedSex").show(3)


//Convert embarked into Double 
val embarkedIndexer = new StringIndexer()
  .setInputCol("embarked")
  .setOutputCol("indexedEmbarked")
  .fit(df)
  
//See what transformation was made
val debugDFEmbarkedIndexed = embarkedIndexer.transform(debugDFSexIndexed)
debugDFEmbarkedIndexed.select("embarked","indexedEmbarked").show(3)

//Index label
val labelIndexer = new StringIndexer()
  .setInputCol("survived")
  .setOutputCol("survivedIndexed")
  .fit(df)


//Create features variable 
val assembler = new VectorAssembler()
  .setInputCols(Array("Pclass","indexedSex" ,"age","SibSp","Parch","Fare","indexedEmbarked"))
  .setOutputCol("features")
  
//See what transformation was made
val debugFeaturesDF =   assembler.transform(debugDFEmbarkedIndexed)
debugFeaturesDF.registerTempTable("featuresDF")
sqlContext.sql("select features, Pclass,indexedSex ,age,SibSp,Parch,Fare,indexedEmbarked from featuresDF").show(2)





//CREATE MODEL



val model = new LogisticRegression()
  .setMaxIter(40)
  .setFeaturesCol("features")
.setLabelCol("survivedIndexed")


//CREATE PIPELINE

val pipeline = new Pipeline()
  .setStages(Array(sexIndexer,embarkedIndexer,assembler,labelIndexer,  model))



//TRAIN MODEL


val Array(trainingData, testData) = df.randomSplit(Array(0.7, 0.3),15L)


// Train model.  This also runs the indexers.
val genModel = pipeline.fit(trainingData)
// Make predictions.
val predictions = genModel.transform(testData)
predictions.select("Pclass","sex","age","SibSp","embarked","survived","prediction","probability").registerTempTable("testPredictions")




sqlContext.sql("select count(*)/221 from testPredictions where survived!=prediction").show()



//Change model 

val model =  new RandomForestClassifier()
  .setFeaturesCol("features")
.setLabelCol("survivedIndexed")
.setNumTrees(50)
  

//Re-train 
val pipeline = new Pipeline()
  .setStages(Array(sexIndexer,embarkedIndexer,assembler,labelIndexer,  model))

val genModel = pipeline.fit(trainingData)
// Make predictions.
val predictions = genModel.transform(testData)
predictions.select("Pclass","sex","age","SibSp","embarked","survived","prediction","probability").registerTempTable("testPredictions")


package com.example.webapp.weka;

import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.classifiers.Evaluation;

import java.util.Random;

public class EventTrainer {
    public static void main(String[] args) throws Exception {
        DataSource source = new DataSource("src/main/webapp/WEB-INF/events_dataset.arff");
        Instances data = source.getDataSet();
        data.setClassIndex(0);

        StringToWordVector filter = new StringToWordVector();
        filter.setInputFormat(data);
        Instances filteredData = Filter.useFilter(data, filter);

        J48 j48 = new J48();
        j48.buildClassifier(filteredData);
        Evaluation evalJ48 = new Evaluation(filteredData);
        evalJ48.crossValidateModel(j48, filteredData, 10, new Random(1));
        System.out.println("\n===== J48 Evaluation =====");
        System.out.println(evalJ48.toSummaryString());
        System.out.println(evalJ48.toClassDetailsString());
        System.out.println(evalJ48.toMatrixString());
        weka.core.SerializationHelper.write("src/main/webapp/WEB-INF/event_model_j48.model", j48);

        RandomForest rf = new RandomForest();
        rf.buildClassifier(filteredData);
        Evaluation evalRF = new Evaluation(filteredData);
        evalRF.crossValidateModel(rf, filteredData, 10, new Random(1));
        System.out.println("\n===== Random Forest Evaluation =====");
        System.out.println(evalRF.toSummaryString());
        System.out.println(evalRF.toClassDetailsString());
        System.out.println(evalRF.toMatrixString());
        weka.core.SerializationHelper.write("src/main/webapp/WEB-INF/event_model_rf.model", rf);

        System.out.println("\nComparison:");
        System.out.printf("J48 Accuracy: %.2f%%\n", (1 - evalJ48.errorRate()) * 100);
        System.out.printf("Random Forest Accuracy: %.2f%%\n", (1 - evalRF.errorRate()) * 100);
    }
}

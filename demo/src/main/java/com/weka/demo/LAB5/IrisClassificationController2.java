package com.weka.demo.LAB5;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.attribute.Discretize;
import weka.filters.unsupervised.attribute.Remove;
import weka.core.converters.ConverterUtils.DataSource;

@RestController
public class IrisClassificationController2 {

    @PostMapping("/classifyIris2")
    public String classifyIris(@RequestBody IrisData irisData) {
        try {
            // Load iris dataset
            DataSource source = new DataSource("src/main/resources/iris.arff");
            Instances data = source.getDataSet();
            if (data.classIndex() == -1) {
                data.setClassIndex(data.numAttributes() - 1);
            }

            // Apply Discretize filter
            Discretize discretizeFilter = new Discretize();
            discretizeFilter.setInputFormat(data);
            Instances filteredData = Filter.useFilter(data, discretizeFilter);


            // Build classifier
            Classifier classifier = new IBk(3);
            classifier.buildClassifier(filteredData);

            // Create a new instance (example)
            Instance newInstance = new DenseInstance(data.numAttributes());
            newInstance.setDataset(filteredData);
            newInstance.setValue(0, irisData.getSepalLength());
            newInstance.setValue(1, irisData.getSepalWidth());
            newInstance.setValue(2, irisData.getPetalLength());
            newInstance.setValue(3, irisData.getPetalWidth());

            // Classify the new instance
            double classValue = classifier.classifyInstance(newInstance);

            // Return the predicted class
            String s =  data.attribute(filteredData.numAttributes() - 1).value((int) classValue);

            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error during classification.";
        }
    }
}

package com.weka.demo.LAB5;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.Remove;
import weka.core.converters.ConverterUtils.DataSource;

@RestController
public class IrisClassificationController {

    @PostMapping("/classifyIris")
    public String classifyIris(@RequestBody IrisData irisData) {
        try {
            // Load iris dataset
            DataSource source = new DataSource("src/main/resources/iris.arff");
            Instances data = source.getDataSet();
            if (data.classIndex() == -1) {
                data.setClassIndex(data.numAttributes() - 1);
            }

            // Apply Remove filter to remove attributes (for demonstration purposes)
            Remove removeFilter = new Remove();
            removeFilter.setAttributeIndices("2,3"); // Remove attributes 3 and 4
            removeFilter.setInputFormat(data);
            Instances newData = weka.filters.Filter.useFilter(data, removeFilter);

            // Build classifier
            Classifier classifier = new IBk(3);
            classifier.buildClassifier(newData);

            // Create a new instance (example)
            Instance newInstance = new DenseInstance(newData.numAttributes());
            newInstance.setDataset(newData);
            newInstance.setValue(0, irisData.getSepalLength()); // sepal length
            newInstance.setValue(1, irisData.getSepalWidth()); // sepal width

            // Classify the new instance
            double classValue = classifier.classifyInstance(newInstance);

            // Return the predicted class
            String s =  newData.attribute(newData.numAttributes() - 1).value((int) classValue);

            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error during classification.";
        }
    }
}

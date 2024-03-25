package com.weka.demo.LAB5;

import com.weka.demo.LAB4.WeatherData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import weka.classifiers.Classifier;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.NominalToBinary;
import weka.filters.unsupervised.attribute.Standardize;

@RestController
public class WeatherController2 {

    @PostMapping("/classifyWeather2")
    public String classifyWeather(@RequestBody WeatherData weatherData) {
        try {
            // Load weather dataset
            DataSource source = new DataSource("src/main/resources/weather.numeric.arff");
            Instances data = source.getDataSet();
            if (data.classIndex() == -1) {
                data.setClassIndex(data.numAttributes() - 1);
            }

            Standardize filter = new Standardize();
            filter.setInputFormat(data);
            Instances newData = weka.filters.Filter.useFilter(data, filter);

            // Build classifier
            Classifier classifier = new weka.classifiers.trees.J48(); // Example classifier, replace with desired one

            // Create and configure FilteredClassifier
            FilteredClassifier fc = new FilteredClassifier();
            fc.setFilter(filter);
            fc.setClassifier(classifier);
            fc.buildClassifier(newData);

            // Print model
            System.out.println(fc);

            Instance newInstance = new DenseInstance(data.numAttributes());
            newInstance.setDataset(data);
            newInstance.setValue(0, weatherData.getOutlook());
            newInstance.setValue(1, weatherData.getTemperature());
            newInstance.setValue(2, weatherData.getHumidity());
            newInstance.setValue(3, weatherData.getWindy());

            // Dokonaj klasyfikacji nowej instancji
            double classValue = classifier.classifyInstance(newInstance);

            // Zwróć wynik klasyfikacji
            return data.attribute(data.numAttributes() - 1).value((int) classValue);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error during classification.";
        }
    }
}

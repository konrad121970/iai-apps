package com.weka.demo.LAB4;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.FileReader;

@RestController
public class ClassificationController {

    @PostMapping("/classify")
    public String classifyWeather(@RequestBody WeatherData weatherData) {
        try {
            // Wczytaj zbiór weather.arff
            BufferedReader weather = new BufferedReader(new FileReader("src/main/resources/weather.numeric.arff"));
            Instances data = new Instances(weather);
            if (data.classIndex() == -1) {
                data.setClassIndex(data.numAttributes() - 1);
            }

            // Zbuduj klasyfikator IBk (KNN=2)
            Classifier classifier = new IBk(2);
            classifier.buildClassifier(data);

            // Utwórz nowy przykład na podstawie danych z formularza
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
            return "Błąd podczas klasyfikacji.";
        }
    }
}

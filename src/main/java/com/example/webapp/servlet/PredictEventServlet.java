package com.example.webapp.servlet;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet("/predict")
public class PredictEventServlet extends HttpServlet {

    private Classifier model;
    private Instances datasetFormat;
    private StringToWordVector filter;

    @Override
    public void init() throws ServletException {
        try {
            String modelPath = getServletContext().getRealPath("/WEB-INF/event_model_j48.model");
            model = (Classifier) weka.core.SerializationHelper.read(modelPath);

            String arffPath = getServletContext().getRealPath("/WEB-INF/events_dataset.arff");
            DataSource source = new DataSource(arffPath);
            datasetFormat = source.getDataSet();
            datasetFormat.setClassIndex(0);

            filter = new StringToWordVector();
            filter.setInputFormat(datasetFormat);
            datasetFormat = Filter.useFilter(datasetFormat, filter);

        } catch (Exception e) {
            throw new ServletException("Model initialization failed: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String location = req.getParameter("location");
            String date = req.getParameter("date");
            String description = req.getParameter("description");

            String instanceLine = "?,'" + name + "'," + location + "," + date + ",'" + description + "'";
            String arffData =
                    "@relation events\n" +
                    "@attribute EventType {Conference,Wedding,Workshop,Party}\n" +
                    "@attribute EventName string\n" +
                    "@attribute Location string\n" +
                    "@attribute Date date \"yyyy-MM-dd\"\n" +
                    "@attribute Description string\n" +
                    "@data\n" + instanceLine + "\n";

            Path tempPath = Files.createTempFile("predict", ".arff");
            Files.write(tempPath, arffData.getBytes());

            DataSource testSource = new DataSource(tempPath.toString());
            Instances testData = testSource.getDataSet();
            testData.setClassIndex(0);
            testData = Filter.useFilter(testData, filter);
            Instance testInstance = testData.instance(0);

            double result = model.classifyInstance(testInstance);
            String prediction = testData.classAttribute().value((int) result);

            req.setAttribute("prediction", prediction);
            req.getRequestDispatcher("predict.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Prediction failed: " + e.getMessage(), e);
        }
    }
}

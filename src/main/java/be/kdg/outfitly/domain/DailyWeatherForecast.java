package be.kdg.outfitly.domain;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DailyWeatherForecast extends Entity {

    private String city;
    private String countryCode;
    private double currentTemperature;
    private double currentFeelsLike;
    private int currentHumidity;
    private double currentWindSpeed;
    private double rainProbability;
    private double maxTemperature;
    private double minTemperature;
    private String description;
    private String weatherIcon;
    private LocalDateTime date;
    private ArrayList<Double> dailyTemperatures = new ArrayList<>();
    private ArrayList<Long> dailyTemperatureTimestamps = new ArrayList<>();
    private ArrayList<String> weatherAlerts = new ArrayList<>();
    private ArrayList<Long> weatherAlertTimeStamps = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(DailyWeatherForecast.class);

    public DailyWeatherForecast(String city, String countryCode, double currentTemperature, double currentFeelsLike, int currentHumidity, double currentWindSpeed, double rainProbability, double maxTemperature, double minTemperature, String description, String weatherIcon, ArrayList<Double> dailyTemperatures, ArrayList<Long> dailyTemperatureTimestamps, ArrayList<String> weatherAlerts, ArrayList<Long> weatherAlertTimeStamps) {
        this.city = city;
        this.countryCode = countryCode;
        this.currentTemperature = currentTemperature;
        this.currentFeelsLike = currentFeelsLike;
        this.currentHumidity = currentHumidity;
        this.currentWindSpeed = currentWindSpeed;
        this.rainProbability = rainProbability;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.description = description;
        this.weatherIcon = weatherIcon;
        this.dailyTemperatures = dailyTemperatures;
        this.dailyTemperatureTimestamps = dailyTemperatureTimestamps;
        this.weatherAlerts = weatherAlerts;
        this.weatherAlertTimeStamps = weatherAlertTimeStamps;
        this.date = LocalDateTime.now();
    }

    public DailyWeatherForecast() {
        this.date = LocalDateTime.now();
    }


    public static DailyWeatherForecast dailyForecast(String country, String city, double latitude, double longitude){
        DailyWeatherForecast forecast = new DailyWeatherForecast();
        JSONObject hourlyForecastData;

        try {
            hourlyForecastData = retrieveAPIData(
                    "https://api.openweathermap.org/data/2.5/onecall?lat="+latitude+"&lon="+longitude+"&exclude=minutely&units=metric&appid=ff81fe37ad2b546130b7cbcb331aa72c"
            );
        } catch (Exception e) {
            logger.error("An error occurred while data was being retrieved from the API.");
            return null;
        }

//        forecast.setDate(LocalDateTime.now());
        forecast.setCity(city);
        forecast.setCountryCode(country);
        forecast.setCurrentTemperature(Double.parseDouble(String.valueOf(hourlyForecastData.getJSONObject("current").get("temp"))));
        forecast.setCurrentFeelsLike(Double.parseDouble(String.valueOf(hourlyForecastData.getJSONObject("current").get("feels_like"))));
        forecast.setCurrentHumidity(Integer.parseInt(String.valueOf(hourlyForecastData.getJSONObject("current").get("humidity"))));
        forecast.setCurrentWindSpeed(Double.parseDouble(String.valueOf(hourlyForecastData.getJSONObject("current").get("wind_speed"))));
        forecast.setRainProbability(Double.parseDouble(String.valueOf(hourlyForecastData.getJSONArray("daily").getJSONObject(0).get("pop"))) * 100);
        forecast.setMaxTemperature(Double.parseDouble(String.valueOf(hourlyForecastData.getJSONArray("daily").getJSONObject(0).getJSONObject("temp").get("max"))));
        forecast.setMinTemperature(Double.parseDouble(String.valueOf(hourlyForecastData.getJSONArray("daily").getJSONObject(0).getJSONObject("temp").get("min"))));
        forecast.setDescription(String.valueOf(hourlyForecastData.getJSONArray("daily").getJSONObject(0).getJSONArray("weather").getJSONObject(0).get("description")));
        forecast.setWeatherIcon(String.valueOf("http://openweathermap.org/img/wn/"+hourlyForecastData.getJSONObject("current").getJSONArray("weather").getJSONObject(0).get("icon"))+"@4x.png");

        for (int i = 0; i < hourlyForecastData.getJSONArray("hourly").length(); i++) {
            forecast.dailyTemperatures.add(Double.parseDouble(String.valueOf(hourlyForecastData.getJSONArray("hourly").getJSONObject(i).get("temp"))));
            forecast.dailyTemperatureTimestamps.add(Long.parseLong(String.valueOf(hourlyForecastData.getJSONArray("hourly").getJSONObject(i).get("dt"))));
        }

        if (hourlyForecastData.has("alerts")) {
            for (int i = 0; i <hourlyForecastData.getJSONArray("alerts").length(); i++) {
                forecast.weatherAlerts.add(String.valueOf(hourlyForecastData.getJSONArray("alerts").getJSONObject(i).get("description")));
                forecast.weatherAlertTimeStamps.add(Long.parseLong(String.valueOf(hourlyForecastData.getJSONArray("alerts").getJSONObject(i).get("start"))));
            }
        }



        logger.debug("Daily weather forecast object created: ");

        return forecast;
    }

    private static JSONObject retrieveAPIData(String APILink) throws Exception{
        URIBuilder builder = new URIBuilder(APILink);
        HttpGet get = new HttpGet(builder.build());
        CloseableHttpClient httpclient = HttpClients.createDefault();
        JSONObject json;

        try (CloseableHttpResponse response = httpclient.execute(get)) {
            json = null;
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                logger.error("Bad response status code: {}.", response.getStatusLine().getStatusCode());
//                return;
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String rawResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                json = new JSONObject(rawResult);
            }

            if (json ==null) {
                logger.error("No data for the forecast has been retrieved!");
            } else {
                logger.debug("Forecast data has been successfully retrieved!");
            }
        }
        return json;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public double getCurrentFeelsLike() {
        return currentFeelsLike;
    }

    public void setCurrentFeelsLike(double currentFeelsLike) {
        this.currentFeelsLike = currentFeelsLike;
    }

    public int getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(int currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public double getCurrentWindSpeed() {
        return currentWindSpeed;
    }

    public void setCurrentWindSpeed(double currentWindSpeed) {
        this.currentWindSpeed = currentWindSpeed;
    }

    public double getRainProbability() {
        return rainProbability;
    }

    public void setRainProbability(double rainProbability) {
        this.rainProbability = rainProbability;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public ArrayList<Double> getDailyTemperatures() {
        return dailyTemperatures;
    }

    public void setDailyTemperatures(ArrayList<Double> dailyTemperatures) {
        this.dailyTemperatures = dailyTemperatures;
    }

    public ArrayList<Long> getDailyTemperatureTimestamps() {
        return dailyTemperatureTimestamps;
    }

    public void setDailyTemperatureTimestamps(ArrayList<Long> dailyTemperatureTimestamps) {
        this.dailyTemperatureTimestamps = dailyTemperatureTimestamps;
    }

    public ArrayList<String> getWeatherAlerts() {
        return weatherAlerts;
    }

    public void setWeatherAlerts(ArrayList<String> weatherAlerts) {
        this.weatherAlerts = weatherAlerts;
    }

    public ArrayList<Long> getWeatherAlertTimestamps() {
        return weatherAlertTimeStamps;
    }

    public void setWeatherAlertTimeStamps(ArrayList<Long> weatherAlertTimeStamps) {
        this.weatherAlertTimeStamps = weatherAlertTimeStamps;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

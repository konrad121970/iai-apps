import React, { useState } from 'react';
import axios from 'axios';
import './../css/WeatherForm.css'; // Importujemy plik CSS dla stylizacji

const WeatherForm2 = () => {
    const [weatherData, setWeatherData] = useState({
        outlook: '',
        temperature: 0,
        humidity: 0,
        windy: ''
    });
    const [result, setResult] = useState('');

    const handleChange = (e) => {
        setWeatherData({
            ...weatherData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/classifyWeather2', weatherData);
            setResult(response.data);
        } catch (error) {
            console.error('Błąd podczas klasyfikacji:', error);
        }
    };

    return (
        <div className="weather-form-container">
            <h2>Weather - Standarize</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="outlook">Outlook:</label>
                    <input type="text" id="outlook" name="outlook" value={weatherData.outlook} onChange={handleChange} placeholder="Słoneczna, pochmurna, deszczowa..." />
                </div>
                <div className="form-group">
                    <label htmlFor="temperature">Temperature:</label>
                    <input type="number" id="temperature" name="temperature" value={weatherData.temperature} onChange={handleChange} placeholder="W stopniach Celsiusza" />
                </div>
                <div className="form-group">
                    <label htmlFor="humidity">Humidity:</label>
                    <input type="number" id="humidity" name="humidity" value={weatherData.humidity} onChange={handleChange} placeholder="W procentach" />
                </div>
                <div className="form-group">
                    <label htmlFor="windy">Windy:</label>
                    <input type="text" id="windy" name="windy" value={weatherData.windy} onChange={handleChange} placeholder="Wietrznie, bezwietrznie..." />
                </div>
                <button type="submit">Klasyfikuj</button>
            </form>
            {result && <p>Wynik klasyfikacji: {result}</p>}
        </div>
    );
};

export default WeatherForm2;

import React, { useState } from 'react';
import axios from 'axios';

const IrisForm2 = () => {
    const [formData, setFormData] = useState({
        sepalLength: 0,
        sepalWidth: 0,
        petalLength: 0,
        petalWidth: 0
    });
    const [result, setResult] = useState('');

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/classifyIris2', formData);
            setResult(response.data);
        } catch (error) {
            console.error('Błąd podczas klasyfikacji:', error);
        }
    };

    return (
        <div className="iris-form-container">
            <h2>Iris Discretize</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="sepalLength">Sepal Length:</label>
                    <input type="number" id="sepalLength" name="sepalLength" value={formData.sepalLength} onChange={handleChange} placeholder="Sepal Length" />
                </div>
                <div className="form-group">
                    <label htmlFor="sepalWidth">Sepal Width:</label>
                    <input type="number" id="sepalWidth" name="sepalWidth" value={formData.sepalWidth} onChange={handleChange} placeholder="Sepal Width" />
                </div>
                <div className="form-group">
                    <label htmlFor="petalLength">Petal Length:</label>
                    <input type="number" id="petalLength" name="petalLength" value={formData.petalLength} onChange={handleChange} placeholder="Petal Length" />
                </div>
                <div className="form-group">
                    <label htmlFor="petalWidth">Petal Width:</label>
                    <input type="number" id="petalWidth" name="petalWidth" value={formData.petalWidth} onChange={handleChange} placeholder="Petal Width" />
                </div>
                <button type="submit">Klasyfikuj</button>
            </form>
            {result && <p>Wynik klasyfikacji: {result}</p>}
        </div>
    );
};

export default IrisForm2;

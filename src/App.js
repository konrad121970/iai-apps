import React from 'react';
import WeatherForm from './Forms/WeatherForm';
import WeatherForm1 from './Forms/WeatherForm1';
import WeatherForm2 from './Forms/WeatherForm2';
import IrisForm from './Forms/IrisForm';
import IrisForm2 from './Forms/IrisForm2';

function App() {
const [showWeatherForm, setShowWeatherForm] = React.useState(false);
  const [showWeatherForm1, setShowWeatherForm1] = React.useState(false);
  const [showWeatherForm2, setShowWeatherForm2] = React.useState(false);
  const [showIrisForm, setShowIrisForm] = React.useState(false);
  const [showAnotherIrisForm, setShowAnotherIrisForm] = React.useState(false);

  const handleWeatherFormClick = () => {
  setShowWeatherForm(true);
                                   setShowWeatherForm1(false);
                                   setShowWeatherForm2(false);
                                   setShowIrisForm(false);
                                   setShowAnotherIrisForm(false);
                                 };

  const handleWeatherForm1Click = () => {
  setShowWeatherForm(false);
    setShowWeatherForm1(true);
    setShowWeatherForm2(false);
    setShowIrisForm(false);
    setShowAnotherIrisForm(false);
  };

  const handleWeatherForm2Click = () => {
    setShowWeatherForm(false);
    setShowWeatherForm1(false);
    setShowWeatherForm2(true);
    setShowIrisForm(false);
    setShowAnotherIrisForm(false);
  };

  const handleIrisFormClick = () => {
    setShowWeatherForm(false);
    setShowWeatherForm1(false);
    setShowWeatherForm2(false);
    setShowIrisForm(true);
    setShowAnotherIrisForm(false);
  };

  const handleAnotherIrisFormClick = () => {
    setShowWeatherForm(false);
    setShowWeatherForm1(false);
    setShowWeatherForm2(false);
    setShowIrisForm(false);
    setShowAnotherIrisForm(true);
  };

  return (
    <div className="App">
      <h1>Aplikacja klasyfikacji</h1>
      <div>
        <button onClick={handleWeatherFormClick}>LAB4</button>
        <button onClick={handleWeatherForm1Click}>Weather-NominalToBinary</button>
        <button onClick={handleWeatherForm2Click}>Weather-Standarize</button>
        <button onClick={handleIrisFormClick}>Iris-Remove</button>
        <button onClick={handleAnotherIrisFormClick}>Iris-Discretize</button>
      </div>
      {showWeatherForm && <WeatherForm />}
      {showWeatherForm1 && <WeatherForm1 />}
      {showWeatherForm2 && <WeatherForm2 />}
      {showIrisForm && <IrisForm />}
      {showAnotherIrisForm && <IrisForm2 />}
    </div>
  );
}

export default App;

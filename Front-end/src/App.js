// App.js
import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Header from './components/Header/Header';
import Home from './pages/Home';
import About from './pages/About';
import GenerateADateForm from './pages/GenerateADateForm';

const App = () => {
    return (
        <Router>
            <div>
                <Header/>
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/about" element={<About/>}/>
                    <Route path="/generate-a-date" element={<GenerateADateForm/>}/>
                </Routes>
            </div>
        </Router>
    );
};

export default App;

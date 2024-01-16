// Home.js
import React from 'react';
import {Link} from 'react-router-dom';

const Home = () => {
    return (
        <div>
            <h1>Page d'accueil</h1>
            <p>Contenu de la page d'accueil</p>
            <Link to="/about">Aller à la page À Propos</Link>
            <Link to="/generate-a-date">Aller au formulaire de generation de date</Link>
        </div>
    );
};

export default Home;

import React from 'react';
import {Link} from 'react-router-dom';

export const Home = () => {
    return (
        <div>
            <h1 className='text-3xl font-bold'>Page d'accueil</h1>
            <p>Contenu de la page d'accueil</p>
            <Link to="/about-us">Aller à la page À Propos</Link>
            <Link to="/generate-a-date">Aller au formulaire de generation de date</Link>
        </div>
    );
};


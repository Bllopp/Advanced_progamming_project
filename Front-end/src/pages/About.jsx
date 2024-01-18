import React from 'react';
import {Link} from 'react-router-dom';

const About = () => {
    return (
        <div>
            <h1>Page À Propos</h1>
            <p>Contenu de la page À Propos</p>
            <Link to="/">Retour à la page d'accueil</Link>
        </div>
    );
};

export default About;

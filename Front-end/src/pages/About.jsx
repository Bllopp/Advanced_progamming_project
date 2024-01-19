import React from "react";
import { NavLink } from "react-router-dom";

export const About = () => {
  return (
    <div>
      <h1>Page À Propos</h1>
      <p>Contenu de la page À Propos</p>
      <NavLink to="/">Retour à la page d'accueil</NavLink>
    </div>
  );
};

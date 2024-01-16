import React from 'react';
import './Header.css';
import logo from '../../assets/logo-efrei.svg'

const Header = () => {
    return (
        <header className="header">
            <img src={logo} className="logo" alt="logo"/>
        </header>
    );
};

export default Header;

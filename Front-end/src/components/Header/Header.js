import React from 'react';
import { Link } from 'react-router-dom';

import './Header.css';

import logo from '../../assets/logo-efrei.svg';

const Header = () => {
    return (
        <header className="header">
            <div className="logo-container">
                <Link to="/">
                    <img src={logo} className="logo" alt="Logo" />
                </Link>
            </div>

            <div className="menu">
                <Link to="/generate-a-date">
                    <div>Plan Defense</div>
                </Link>

                <Link to="/reports">
                    <div>Reports</div>
                </Link>
            </div>
        </header>
    );
};

export default Header;

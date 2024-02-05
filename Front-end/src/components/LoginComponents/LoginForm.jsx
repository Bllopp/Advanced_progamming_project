import React, { useState } from 'react';
import { TextField, Button, Typography } from '@mui/material';
import {urlAuthenticationService} from "../../constant/constant";

export const LoginForm = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault(); // Empêcher le rechargement de la page lors de la soumission du formulaire

        const formData = new URLSearchParams();
        formData.append('username', username);
        formData.append('password', password);
        formData.append('email', email);

        try {
            const response = await fetch(`${urlAuthenticationService}/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: formData,
            });
            if (response.ok) {
                // Si la connexion réussit, réinitialisez les champs du formulaire
                setUsername('');
                setPassword('');
                setEmail('');
                console.log('Login successful!');
            } else {
                // Si la connexion échoue, vous pouvez afficher un message d'erreur
                console.error('Login failed!');
            }
        } catch (error) {
            // Gérer les erreurs de connexion
            console.error('Error:', error);
        }
    };

    return (
        <form onSubmit={handleLogin}>
            <Typography variant="h5">Login</Typography>
            <TextField
                label="Username"
                variant="outlined"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />
            <TextField
                label="Password"
                type="password"
                variant="outlined"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <TextField
                label="Email"
                type="email"
                variant="outlined"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />
            <Button type="submit" variant="contained" color="primary">
                Login
            </Button>
        </form>
    );
};

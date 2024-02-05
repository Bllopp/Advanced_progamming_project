import React, { useState } from 'react';
import { TextField, Button, Grid, Typography } from '@mui/material';
import {urlAuthenticationService} from "../../constant/constant";
import { useDispatch } from 'react-redux';
import { setToken } from '../../redux/slices/authSlice';

export const LoginForm = ({setLogIn}) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const dispatch  = useDispatch();


    const handleLogin = async () => {
        const formData = new URLSearchParams();
        formData.append('username', username);
        formData.append('password', password);

        try {
            const response = await fetch(`${urlAuthenticationService}/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: formData.toString(),
            });
            if (response.ok) {
                setUsername('');
                setPassword('');
                const result = await response.text();
                dispatch(setToken(result))
                console.log(result);
                console.log('Login successful!');
                setLogIn();
            } else {
                console.error('Login failed!');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };


    return (
        <Grid container spacing={2} direction="column" alignItems="center">
            <Grid item>
                <Typography variant="h5">Login</Typography>
            </Grid>
            <Grid item>
                <TextField
                    label="Username"
                    variant="outlined"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
            </Grid>
            <Grid item>
                <TextField
                    label="Password"
                    type="password"
                    variant="outlined"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
            </Grid>
            <Grid item>
                <Button variant="contained" color="primary" onClick={handleLogin}>
                    Login
                </Button>
            </Grid>
        </Grid>
    );
};
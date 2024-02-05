import React, { useState } from 'react';
import { TextField, Button, Grid, Typography } from '@mui/material';

export const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');

    const handleLogin = () => {
        console.log('Logging in with:', { username, password, email });
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
                <TextField
                    label="Email"
                    type="email"
                    variant="outlined"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
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

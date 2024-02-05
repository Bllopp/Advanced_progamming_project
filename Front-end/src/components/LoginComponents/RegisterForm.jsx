import React, { useState } from 'react';
import { TextField, Button, Grid, Typography, FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import {urlAuthenticationService} from "../../constant/constant";
import axios from "axios";

export const RegisterForm = ({setLogIn}) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [role, setRole] = useState('');

    const handleRegister = async () => {

        const user = {
            username: username,
            password: password,
            email: email,
            role: role
        }

        await fetch(`${urlAuthenticationService}/register`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user)
        })
            .then(response => {
                if (response.status === 200) {
                    setUsername('');
                    setPassword('');
                    setEmail('');
                    setRole('');
                    response.json().then(data => {
                        console.log(data);
                    });
                    setLogIn();
                    console.log('Registration successful!');
                } else {
                    console.error('Registration failed!');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    };

    return (
        <Grid container spacing={2} direction="column" alignItems="center">
            <Grid item xs={12}>
                <Typography variant="h5">Register</Typography>
            </Grid>
            <Grid item xs={12}>
                <TextField
                    label="Username"
                    variant="outlined"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    fullWidth
                />
            </Grid>
            <Grid item xs={12}>
                <TextField
                    label="Password"
                    type="password"
                    variant="outlined"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    fullWidth
                />
            </Grid>
            <Grid item xs={12}>
                <TextField
                    label="Email"
                    type="email"
                    variant="outlined"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    fullWidth
                />
            </Grid>
            <Grid item xs={12}>
                <FormControl variant="outlined" fullWidth style={{ width: '200px' }}>
                    <InputLabel id="role-label">Role</InputLabel>
                    <Select
                        labelId="role-label"
                        value={role}
                        onChange={(e) => setRole(e.target.value)}
                        label="Role"
                        fullWidth
                    >
                        <MenuItem value="student">Student</MenuItem>
                        <MenuItem value="teacher">Teacher</MenuItem>
                        <MenuItem value="tutor">Tutor</MenuItem>
                    </Select>
                </FormControl>

            </Grid>
            <Grid item xs={12}>
                <Button variant="contained" color="primary" onClick={handleRegister} fullWidth>
                    Register
                </Button>
            </Grid>
        </Grid>
    );
};

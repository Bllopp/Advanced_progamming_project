import React, { useState } from 'react';
import { TextField, Button, Grid, Typography, FormControl, InputLabel, Select, MenuItem } from '@mui/material';

export const RegisterForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [role, setRole] = useState('');

    const handleRegister = () => {
        // Logic to handle registration with username, password, email, and role
        console.log('Registering with:', { username, password, email, role });
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

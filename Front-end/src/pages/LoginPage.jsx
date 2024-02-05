import React, { useState } from 'react';
import { LoginForm } from "../components/LoginComponents/LoginForm";
import { RegisterForm } from "../components/LoginComponents/RegisterForm";
import { Tabs, Tab, Box } from '@mui/material';

export const LoginPage = ({setLogIn}) => {
    const [tabIndex, setTabIndex] = useState(0); // 0 for login, 1 for register

    const handleTabChange = (event, newIndex) => {
        setTabIndex(newIndex);
    };

    return (
        <div className="flex flex-col h-content">
            <Tabs value={tabIndex} onChange={handleTabChange} centered>
                <Tab label="Login" />
                <Tab label="Register" />
            </Tabs>
            <Box sx={{ p: 3 }}>
                {tabIndex === 0 && <LoginForm setLogIn={setLogIn} />}
                {tabIndex === 1 && <RegisterForm setLogIn={setLogIn} />}
            </Box>
        </div>
    );
};

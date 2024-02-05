import React, { useState } from 'react';
import { router } from "./router";
import { RouterProvider } from "react-router-dom";
import { Root } from "@pages";
import {LoginPage} from "./pages/LoginPage";



const App = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const setLogIn = () => {
        setIsLoggedIn(true);
    }

    return (
        <div>
            {isLoggedIn ?
                    <RouterProvider router={router}>
                        <Root />
                    </RouterProvider> :
            <LoginPage setLogIn={setLogIn} />}
        </div>
    );
};

export default App;

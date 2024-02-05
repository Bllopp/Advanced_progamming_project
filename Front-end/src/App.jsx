import React, { useState } from 'react';
import { router } from "./router";
import { RouterProvider } from "react-router-dom";
import { Root } from "@pages";
import {LoginPage} from "./pages/LoginPage";

const App = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    return (
        <div>
            {isLoggedIn ?
                    <RouterProvider router={router}>
                        <Root />
                    </RouterProvider> :
            <LoginPage/>}
        </div>
    );
};

export default App;

import { createBrowserRouter as Router } from "react-router-dom";
import { Home, About, GenerateADateForm, ErrorPage } from "@pages";
import { ABOUT_ROUTE, GENERATE_A_DATE_ROUTE } from "./routes";
import { Root } from "@pages";

export const router = Router([
  {
    path: "",
    element: <Root />,
    errorElement: <ErrorPage error={"404"} />,
    children: [
      {
        path: '/',
        element: <Home />,
      },
      {
        path: ABOUT_ROUTE,
        element: <About />,
      },
      {
        path: GENERATE_A_DATE_ROUTE,
        element: <GenerateADateForm />,
      },
    ],
  },
]);

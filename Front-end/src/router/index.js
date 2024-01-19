import { createBrowserRouter as Router } from "react-router-dom";
import { Home, About, GenerateADateForm, ErrorPage } from "@pages"
import { ABOUT_US, GENERATE_A_DATE } from "./routes";

export const router = Router([
    {
      path: "/",
      element: <Home />,
      errorElement: <ErrorPage error={'404'} />,
      children: [
        {
          path: ABOUT_US,
          element: <About />,
        },
        {
          path: GENERATE_A_DATE,
          element: <GenerateADateForm />,
        }
      ]
    },
  ]);
import App from "@/App";
import { createBrowserRouter as Router } from "react-router-dom";
import { Home, About, GenerateADateForm } from "@pages"

export const router = Router([
    {
      path: "/",
      element: <Home />,
    },
    {
      path: "/about-us",
      element: <About />,
    },
    {
      path: "/generate-a-date",
      element: <GenerateADateForm />,
    }
  ]);
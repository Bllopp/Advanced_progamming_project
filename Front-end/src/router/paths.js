import { Home, About, GenerateADateForm, ErrorPage } from "@pages";
import { HOME, ABOUT_US, GENERATE_A_DATE, LOGIN } from "./routes";
import { Root } from "@pages";
import { Login } from "@pages/Login";

export const PATHS = [
  {
    path: "",
    element: <Root />,
    errorElement: <ErrorPage error={"404"} />,
    children: [
      {
        ...HOME,
        element: <Home />,
      },
      {
        ...ABOUT_US,
        element: <About />,
      },
      {
        ...GENERATE_A_DATE,
        element: <GenerateADateForm />,
      },
      {
        ...LOGIN,
        element: <Login />,
      },
    ],
  },
];

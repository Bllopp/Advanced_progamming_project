import { Home, About, GenerateADateForm, ErrorPage } from "@pages";
import { HOME, ABOUT_US, GENERATE_A_DATE } from "./routes";
import { Root } from "@pages";

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
    ],
  },
];

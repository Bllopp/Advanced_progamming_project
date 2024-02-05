import { Home, About, GenerateADateForm, ErrorPage } from "@pages";
import {HOME, ABOUT_US, GENERATE_A_DATE, TUTOR_VALIDATION, DATE_SELECTION} from "./routes";
import { Root } from "@pages";

export const PATHS = [
  {
    path: "",
    element: <Root />,
    errorElement: <ErrorPage error={"404"} />,
    children: [
      {
        ...TUTOR_VALIDATION,
        element: <Home />,
      },
      {
        ...DATE_SELECTION,
        element: <About />,
      },
      {
        ...GENERATE_A_DATE,
        element: <GenerateADateForm />,
      },
    ],
  },
];

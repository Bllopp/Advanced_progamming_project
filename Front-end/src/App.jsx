import { router } from "./router";
import { RouterProvider } from "react-router-dom";
import { Root } from "@pages";

const App = () => {
  return (
    <RouterProvider router={router}>
      <Root />
    </RouterProvider>
  );
};

export default App;

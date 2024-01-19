import { TopBar } from "@components";
import { router } from "./router";
import { RouterProvider } from "react-router-dom";

const App = () => {
  return (
    <RouterProvider router={router}>
      <TopBar />
    </RouterProvider>
  );
};

export default App;

import { NavLink, useRouteError } from "react-router-dom";

export const ErrorPage = ({ error }) => {
  const exception = useRouteError();
  let errorMessage;
  switch (error) {
    case "404":
      errorMessage = "404 - Cette page n'existe pas";
      break;
    case "403":
      errorMessage = "403 - Vous n'êtes pas autorisé à consulter cette page";
      break;
    default:
      errorMessage = exception.error.message;
  }

  return (
    <div className="w-full h-full flex justify-center items-center flex-col gap-6">
      <div className="text-2xl text-red-500">{errorMessage}</div>
      <NavLink to="/" className="rounded-lg gray-button px-3 py-2">
        Go back home
      </NavLink>
    </div>
  );
};

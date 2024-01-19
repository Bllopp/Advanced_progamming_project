import { Link } from "react-router-dom";

export const ErrorPage = ({ error }) => {
  return (
    <div className="w-full h-full flex justify-center items-center flex-col gap-6">
      <div className="text-2xl text-red-500">
        {error === "404"
          ? "404 - Cette page n'existe pas"
          : "403 - Vous n'êtes pas autorisé à consulter cette page"}
      </div>
      <Link to="/" className="rounded-lg gray-button px-3 py-2">
        Go back home
      </Link>
    </div>
  );
};

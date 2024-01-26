import { TopBar } from "@components";
import clsx from "clsx";
import { useEffect, useState } from "react";
import { Outlet } from "react-router-dom";

export const Root = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  useEffect(() => {}, [isMenuOpen]);

  return (
    <div className="max-md:pt-3 md:overflow-hidden h-screen">
      <TopBar
        isMenuOpen={isMenuOpen}
        setIsMenuOpen={(state) => setIsMenuOpen(state)}
      />
      <div
        onClick={() => {
          if (isMenuOpen) setIsMenuOpen(false);
        }}
        className={clsx(
          isMenuOpen && "max-sm:translate-page",
          "translate-page"
        )}
      >
        <Outlet />
      </div>
    </div>
  );
};

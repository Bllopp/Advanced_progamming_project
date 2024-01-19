import { TopBar } from "@components";
import clsx from "clsx";
import { useState } from "react";
import { Outlet } from "react-router-dom";

export const Root = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  return (
    <div className="h-full">
      <TopBar
        isMenuOpen={isMenuOpen}
        setIsMenuOpen={(state) => setIsMenuOpen(state)}
      />
      <div
        className={clsx(
          isMenuOpen && "max-sm:translate-page",
          "translate-page pt-2"
        )}
      >
        <Outlet />
      </div>
    </div>
  );
};

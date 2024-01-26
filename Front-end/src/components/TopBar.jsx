/* eslint-disable jsx-a11y/anchor-is-valid */
import { ReactComponent as Logo } from "@/assets/logo-efrei.svg";
import { ReactComponent as BurgerMenuIcon } from "@/assets/burger-menu.svg";
import { ReactComponent as CloseIcon } from "@/assets/close-circle.svg";
import { useLocation, useNavigate } from "react-router-dom";
import clsx from "clsx";
import { HOME, ABOUT_US, GENERATE_A_DATE } from "@/router/routes";
import { useState } from "react";
import { CustomButton } from "./CustomButton";

export const TopBar = ({ isMenuOpen, setIsMenuOpen }) => {

  const topBarRoutes = [HOME, ABOUT_US, GENERATE_A_DATE];
  
  const navigateTo = (element) => {
    setElements(
      elements.map((el) => ({
        ...el,
        isActive: element.path === el.path,
      }))
    );
    navigate(element.path);
  };

  const navigate = useNavigate();
  const location = useLocation();

  const [elements, setElements] = useState(
    topBarRoutes.map((element) => ({
      ...element,
      isActive: element.path === location.pathname,
    }))
  );

  return (
    <header className="flex w-full h-[var(--topbar-height)] z-50 bg-[var(--primary)] mx-auto py-1 fixed top-0 w-content justify-center">
      <div className="absolute max-sm:border-b-2 max-sm:border-black inset-0 px-4 md:px-16 flex max-sm:flex-col items-center max-w-full">
        <div className="flex justify-between max-sm:py-2 lg:w-full sm:w-2/5 w-full">
          <a href="#">
            <Logo className="h-[var(--logo-height)] w-[var(--logo-width)]" />
          </a>
          <button
            id="burger-menu"
            className="sm:hidden"
            onClick={() => setIsMenuOpen(!isMenuOpen)}
          >
            {isMenuOpen ? (
              <CloseIcon className="h-8 w-8 hover:text-white" />
            ) : (
              <BurgerMenuIcon className="h-8 w-8 hover:text-white" />
            )}
          </button>
        </div>
        <ul
          className={clsx(
            { "max-sm:hidden": !isMenuOpen },
            "flex w-full max-sm:flex-col max-sm:absolute max-sm:translate-y-[var(--topbar-height)] sm:justify-around sm:w-3/5 h-full items-center"
          )}
        >
          {elements.map((element, i) => (
            <li key={i} className="h-full max-sm:w-full">
              <CustomButton
                onClick={() => navigateTo(element)}
                key={i}
                className={clsx(
                  element.isActive
                    ? "max-sm:border-white font-bold border-b-2 text-white"
                    : "max-sm:border-black font-semibold",
                  "max-sm:border-b-2 md:text-lg max-sm:hover:bg-white max-sm:hover:text-black cursor-pointer max-sm:w-full flex items-center justify-center max-sm:bg-[var(--primary)] sm:h-full py-button sm:px-3"
                )}
              >
                {element.label}
              </CustomButton>
            </li>
          ))}
        </ul>
      </div>
    </header>
  );
};

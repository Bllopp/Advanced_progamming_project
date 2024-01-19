/* eslint-disable jsx-a11y/anchor-is-valid */
import logo from "@/assets/logo-efrei.svg";
import burgerMenu from "@/assets/burger-menu.svg";
import close from "@/assets/close-circle.svg";
import { NavLink, useNavigate } from "react-router-dom";
import clsx from "clsx";
import { PATHS } from "../router/paths";
import { useState } from "react";
import { CustomButton } from "./CustomButton";

export const TopBar = ({ isMenuOpen, setIsMenuOpen }) => {
  const [rootElement] = PATHS.filter((path) => path.path === "");
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
  const [elements, setElements] = useState(
    rootElement.children.map((element) => ({
      ...element,
      isActive: element.path === "/",
    }))
  );


  return (
    <header className="flex w-full h-[var(--topbar-height)] z-50 bg-[var(--primary)] mx-auto py-1 fixed top-0 w-content justify-center">
      <div className="absolute max-sm:border-b-2 max-sm:border-black inset-0 px-4 md:px-16 flex max-sm:flex-col items-center max-w-full">
        <div className="flex justify-between max-sm:py-2 lg:w-full sm:w-2/5 w-full">
          <a href="#">
            <img src={logo} className="h-12" alt="logo" />
          </a>
          <button
            id="burger-menu"
            className="sm:hidden"
            onClick={() => setIsMenuOpen(!isMenuOpen)}
          >
            <img
              src={isMenuOpen ? close : burgerMenu}
              className="h-8 hover:text-white"
              alt=""
            />
          </button>
        </div>
        <ul
          className={clsx(
            { "max-sm:hidden": !isMenuOpen },
            "flex w-full max-sm:flex-col max-sm:absolute max-sm:translate-y-[var(--topbar-height)] sm:justify-around sm:w-3/5 h-full items-center"
          )}
        >
          {elements.map((element, i) => (
            <li className="h-full max-sm:w-full">
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

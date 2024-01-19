/* eslint-disable jsx-a11y/anchor-is-valid */
import logo from "@/assets/logo-efrei.svg";
import burgerMenu from "@/assets/burger-menu.svg";
import close from "@/assets/close-circle.svg";
import { NavLink } from "react-router-dom";
import { ABOUT_ROUTE, GENERATE_A_DATE_ROUTE } from "@/router/routes";
import clsx from "clsx";

export const TopBar = ({isMenuOpen, setIsMenuOpen}) => {

  return (
    <header className="flex w-full h-[var(--topbar-height)] bg-[var(--primary)] mx-auto py-1 fixed top-0 w-content justify-center">
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
          <li className="max-sm:hover:bg-white max-sm:hover:text-black cursor-pointer max-sm:w-full flex justify-center items-center max-sm:bg-[var(--primary)] sm:h-full py-button sm:px-3 border-b-2 border-b-white text-white">
            <NavLink className={"font-bold"} to={"/"}>
              HOME
            </NavLink>
          </li>
          <li className="max-sm:hover:bg-white max-sm:hover:text-black cursor-pointer max-sm:w-full flex items-center justify-center max-sm:bg-[var(--primary)] sm:h-full py-button sm:px-3">
            <NavLink className={"font-semibold"} to={ABOUT_ROUTE}>
              ABOUT US
            </NavLink>
          </li>
          <li className="max-sm:hover:bg-white max-sm:hover:text-black cursor-pointer max-sm:w-full flex items-center justify-center max-sm:bg-[var(--primary)] sm:h-full py-button sm:px-3">
            <NavLink className={"font-semibold"} to={GENERATE_A_DATE_ROUTE}>
              SUGGEST DATES
            </NavLink>
          </li>
        </ul>
      </div>
    </header>
  );
};

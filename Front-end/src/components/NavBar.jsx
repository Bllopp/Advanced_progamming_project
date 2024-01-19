/* eslint-disable jsx-a11y/anchor-is-valid */
import React from "react";
import logo from "@/assets/logo-efrei.svg";

export const NavBar = () => {
  return (
    <div className="flex w-full mx-auto justify-center">
      <div className="px-2 border-2 flex max-w-full">
        <a href="#">
          <img src={logo} className="h-16" alt="logo" />
        </a>
        <div className="justify-around">

        </div>
      </div>
    </div>
  );
};

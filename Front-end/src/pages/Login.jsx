import { ReactComponent as Logo } from "@/assets/logo-efrei.svg";
import { ReactComponent as PadLock } from "@/assets/padlock.svg";
import { CustomButton } from "../components";
import { useNavigate } from "react-router-dom";

export const Login = () => {
  const navigate = useNavigate();

  return (
    <div className="flex h-screen w-screen">
      <div className="flex-1 max-md:hidden h-full w-1/2 flex items-center justify-center">
        <PadLock className="h-full w-1/2 flex" />
      </div>in
      <div className="w-full md:w-1/2 py-2 flex flex-col bg-[var(--primary)]">
        <div className="w-full flex-1 h-full px-8 flex flex-col md:justify-between">
          <div className="flex flex-col w-full h-1/3 items-center md:justify-center">
            <Logo className="h-[20rem] w-[20rem] md:h-[var(--logo-height)] md:w-[var(--logo-width)]" />
          </div>
          <div className="flex flex-col h-2/3 gap-y-8">
            <div className="flex flex-col">
              <span className="text-5xl w-1/2 font-bold">LOGIN</span>
              <span className="text-xl w-1/2 sm:font-semibold">
                WELCOME BACK
              </span>
            </div>
            <div className="flex flex-col gap-y-4">
              <input
                placeholder="Enter your email"
                className="h-12 px-2 outline-[var(--primary)] border-2 rounded-lg"
                type="email"
                id="email"
                required
              />
              <input
                placeholder="Enter your password"
                className="h-12 px-2 outline-[var(--primary)] border-2 rounded-lg"
                type="password"
                id="password"
                required
              />
            </div>
            <div className="flex flex-col gap-y-2 justify-center items-center">
              <CustomButton className="font-bold text-white text-lg bg-[var(--secondary)] w-fit rounded-lg px-4 py-2">
                Login
              </CustomButton>
              <div className="">
                Don't have an account ?{" "}
                <span
                  onClick={() => navigate("/register")}
                  className="hover:underline text-blue-900 cursor-pointer"
                >
                  Register now
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

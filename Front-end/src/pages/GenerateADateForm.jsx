import { NavLink } from "react-router-dom";
import { PresentationDateLocation } from "@components";
import { Stepper } from "@components/MultiStepForm/Stepper"

export const GenerateADateForm = () => {
  return (
    <div className="flex max-md:flex-col gap-1 h-[var(--content-height)] w-full">
      <Stepper />
      <div className="w-full h-full bg-[var(--tertiary)] border-2"></div>
    </div>

    // <div
    //   style={{
    //     margin: "10px",
    //   }}
    // >
    //   <p
    //     style={{
    //       padding: "10px",
    //       display: "inline-block",
    //       "max-width": "100%",
    //       "background-color": "#EBEBEB",
    //       color: "#468CCC",
    //       "font-size": "30px",
    //       "border-radius": "10px",
    //     }}
    //   >
    //     Propose 3 dates and locations for your Presentation
    //   </p>
    //   <div>
    //     {[...Array(3)].map((_, index) => (
    //       <PresentationDateLocation
    //         key={index}
    //         dateId={index + 1}
    //         locationId={index + 1}
    //       />
    //     ))}
    //   </div>
    //   <div
    //     style={{
    //       display: "flex",
    //       "align-items": "center",
    //       "justify-content": "center",
    //     }}
    //   >
    //     <button
    //       style={{
    //         "background-color": "#46CC6B",
    //         display: "inline-block",
    //         "max-width": "100%",
    //         "font-size": "26px",
    //         padding: "10px",
    //         color: "white",
    //         "border-radius": "5px",
    //         "margin-top": "10px",
    //       }}
    //     >
    //       Submit
    //     </button>
    //   </div>
    //   <NavLink to="/">Retour à la page d'accueil</NavLink>
    // </div>
  );
};

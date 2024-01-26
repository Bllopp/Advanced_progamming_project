import { ReactComponent as Completed } from "@/assets/completed.svg";
import { ReactComponent as Uncompleted } from "@/assets/uncompleted.svg";
import { ReactComponent as OnGoing } from "@/assets/on-going.svg";

import clsx from "clsx";
import { statuses } from "@utils/multiStepForm/stepConsts";
import React from "react";

export const StepperItem = ({
  onClick,
  stepperId,
  stepperLabel,
  stepStatus,
}) => {
  return (
    <div
      className={clsx(
        "flex max-md:flex-col-reverse max-md:pt-2 h-20 md:h-full w-24 md:w-full md:flex-1 overflow-hidden gap-1 md:gap-2 ",
        stepStatus === statuses.COMPLETED ? "cursor-pointer" : "cursor-default",
        { "text-[var(--primary)]": stepStatus === statuses.COMPLETED },
        { "text-[var(--secondary)]": stepStatus === statuses.ONGOING },
        { "text-[var(--tertiary)]": stepStatus === statuses.UNCOMPLETED }
      )}
      onClick={() => {
        onClick();
      }}
    >
      <div className="w-full h-20">
        {stepStatus === statuses.UNCOMPLETED ? (
          <Uncompleted stroke="var(--primary)" className="h-8 md:h-20" />
        ) : stepStatus === statuses.ONGOING ? (
          <OnGoing stroke="var(--secondary)" className="h-8 md:h-20" />
        ) : stepStatus === statuses.COMPLETED ? (
          <Completed stroke="var(--primary)" className="h-8 md:h-20" />
        ) : (
          React.Fragment
        )}
      </div>
      <div className="flex w-full h-20 md:flex-col max-md:gap-1 max-md:items-center">
        <span className="md:text-5xl text-3xl font-bold">{stepperId}</span>
        <span
          className={clsx("text-sm max-sm:text-xs font-thin", {
            "text-black": stepStatus.COMPLETED || stepStatus.ONGOING,
          })}
        >
          {stepperLabel}
        </span>
      </div>
    </div>
  );
};

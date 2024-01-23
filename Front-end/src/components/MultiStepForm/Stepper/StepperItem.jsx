import completed from "@/assets/completed.svg";
import uncompleted from "@/assets/uncompleted.svg";
import onGoing from "@/assets/on-going.svg";

import clsx from "clsx";
import { statuses } from "./consts";

export const StepperItem = ({
  onClick,
  isCurrent = false,
  stepperId,
  stepperLabel,
  stepStatus,
}) => {
  const statusImg =
    stepStatus === statuses.UNCOMPLETED
      ? uncompleted
      : stepStatus === statuses.ONGOING
      ? onGoing
      : stepStatus === statuses.COMPLETED
      ? completed
      : undefined;

  return (
    <div
      className={clsx(
        "flex max-md:flex-col-reverse max-md:pt-2 h-20 md:h-full w-24 md:w-full md:flex-1 overflow-hidden gap-1 md:gap-2 ",
        stepStatus === statuses.COMPLETED && !isCurrent
          ? "cursor-pointer"
          : "cursor-default",
        { "text-[var(--primary)]": stepStatus === statuses.COMPLETED },
        { "text-[var(--secondary)]": stepStatus === statuses.ONGOING },
        { "text-[var(--tertiary)]": stepStatus === statuses.UNCOMPLETED }
      )}
      onClick={() => {
        onClick();
        isCurrent = true;
      }}
    >
      <div className="w-full h-20">
        <img src={statusImg} alt="" className="h-8 md:h-20" />
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

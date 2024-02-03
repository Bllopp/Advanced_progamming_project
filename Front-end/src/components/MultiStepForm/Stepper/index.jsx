import clsx from "clsx";
import { FORM_STEPS, statuses } from "@/utils/multiStepForm/stepConsts";
import { StepperItem } from "./StepperItem";

export const Stepper = () => {
  return (
    <div className="px-2 py-1 flex md:flex-col">
      {FORM_STEPS.map((step, id) => (
        <div
          key={id}
          className="flex md:flex-col h-full max-md:w-full md:gap-y-2 gap-x-2 max-md:items-end"
        >
          <div className="md:w-20 md:h-full max-md:h-10 w-full flex max-md:items-center md:justify-center flex-1 ">
            <div
              className={clsx(
                "md:h-full md:w-0 h-0 w-full rounded-sm border-4",
                {
                  "border-[var(--primary)]": step.status === statuses.COMPLETED,
                },
                {
                  "border-dashed border-[var(--secondary)]":
                    step.status === statuses.ONGOING,
                },
                {
                  "border-[var(--tertiary)]":
                    step.status === statuses.UNCOMPLETED,
                }
              )}
            />
          </div>
          <StepperItem
            isCurrent={id === 0}
            stepStatus={step.status}
            stepperLabel={step.label}
            stepperId={`${id % 10 === id ? "0" : ""}${id + 1}`}
          />
        </div>
      ))}
    </div>
  );
};

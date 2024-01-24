export const statuses = {
  UNCOMPLETED: "uncompleted",
  ONGOING: "ongoing",
  COMPLETED: "completed",
};

export const FORM_STEPS = [
  {
    label: "Upload Report",
    status: statuses.COMPLETED,
  },
  {
    label: "Dates Selection",
    status: statuses.ONGOING,
  },
  {
    label: "Review",
    status: statuses.UNCOMPLETED,
  },
];

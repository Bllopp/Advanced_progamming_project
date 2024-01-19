export const CustomButton = ({
  children,
  className = "line-clamp-1",
  disabled,
  onClick,
}) => {
  return (
    <button className={className} disabled={disabled} onClick={() => onClick()}>
      {children}
    </button>
  );
};

export function getExtension(fileName) {
  if (!fileName) return "";
  if (!fileName.includes(".")) return "";
  return `.${fileName.split(".").pop()}`;
}

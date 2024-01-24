import { ReactComponent as CloseIcon } from "@/assets/close-circle.svg";
import { ReactComponent as UploadFileIcon } from "@/assets/upload-file.svg";
import { useRef } from "react";
import { bytes } from "@/utils/files";

export const CustomFileInput = ({ value, onChange }) => {
  const uploaderRef = useRef(null);
  const fileInputRef = useRef(null);

  const handleFileDrop = (e) => {
    const droppedFile = e.dataTransfer.files[0];
    if (droppedFile) {
      onChange(
        new File([droppedFile], droppedFile.name, {
          type: droppedFile.type,
        })
      );
    }
  };

  const onDragEnter = (e) => {
    if (uploaderRef.current && !uploaderRef.current.contains(e.relatedTarget))
      uploaderRef.current.classList.add("dragover");
  };
  const onDragLeave = (e) => {
    if (uploaderRef.current && !uploaderRef.current.contains(e.relatedTarget))
      uploaderRef.current.classList.remove("dragover");
  };
  const onDrop = (e) => {
    e.preventDefault()
    if (uploaderRef.current && !uploaderRef.current.contains(e.relatedTarget)) {
      uploaderRef.current.classList.remove("dragover");
      handleFileDrop(e);
    }
  };

  return (
    <>
      <input
        ref={fileInputRef}
        type="file"
        className="hidden"
        onChange={e => onChange(e.target.files[0])}
      />
      {value ? (
        <div className="flex justify-between w-full">
          <div className="relative flex gap-6 z-10">
            {/* <FileTypeIcon size={24} file={value} /> */}
            <div>
              <div className="text-2 text-lg font-medium">{value.name}</div>
              <div className="text-1 text-sm">{bytes(value.size)}</div>
            </div>
          </div>
          <div className="relative z-10 p-0.5 rounded-lg h-fit cursor-pointer">
            <CloseIcon
              height="36"
              className="text-red-600"
              onClick={() => onChange(null)}
            />
          </div>
        </div>
      ) : (
        <div
          className="flex flex-col justify-center items-center gap-4 w-full flex-1"
          ref={uploaderRef}
          onDragEnter={onDragEnter}
          onDragLeave={onDragLeave}
          onDragOver={(e) => e.preventDefault()}
          onDrop={onDrop}
        >
          <UploadFileIcon
            fill="var(--secondary)"
            draggable="false"
            className="text-0 h-[150px] md:h-[300px]"
          />
          <div draggable="false" className="text-2 text-lg font-medium">
            Drag and drop{" "}
            <span
              className="text-[var(--secondary)] hover:underline cursor-pointer"
              onClick={() => fileInputRef.current?.click()}
            >
              or select a document
            </span>
          </div>
        </div>
      )}
    </>
  );
};

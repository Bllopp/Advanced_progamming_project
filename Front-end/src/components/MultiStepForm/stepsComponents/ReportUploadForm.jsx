import clsx from "clsx";
import { CustomFileInput } from "@components/CustomFileInput";
import { useState } from "react";

export const ReportUploadForm = ({report}) => {
  const [file, setFile] = useState(report);
  const uploadFile = (file) => {
    setFile(file);
    console.log(file)
  }

  return (
    <div
      className={clsx(
        "h-full w-full rounded-2xl flex flex-col gap-3 px-8 py-3",
        file && "file-uploader"
      )}
    >
      <CustomFileInput value={file} onChange={uploadFile}/>
      <div className="flex flex-col flex-1 h-full w-full">
        <span className="text-xl font-bold max-md:hidden">Note : </span>
        <textarea
          className="h-full w-full text-lg px-1 py-2"
          placeholder="Leave comment to the tutor(optional) ..."
        />
      </div>
      <button className="px-3 py-1 rounded h-10 w-full md:text-lg text-lg font-bold text-white hover:text-[var(--primary)] hover:bg-transparent bg-[var(--secondary)]">
        SUBMIT
      </button>
    </div>
  );
};

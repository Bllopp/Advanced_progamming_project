import clsx from "clsx";
import { CustomFileInput } from "@components/CustomFileInput";
import { useState } from "react";
import { submitReport } from "@utils/api";

export const ReportUploadForm = ({ report }) => {
  const [isSubmited, setIsSubmited] = useState(false);
  const [file, setFile] = useState(report);
  const [comment, setComment] = useState("");

  const uploadFile = (file) => {
    setFile(file);
    if (!file) setIsSubmited(false);
  };

  const submission = async (e) => {
    e.preventDefault();
    setIsSubmited(true);
    if (file) {
      const details = {};

      details.studentId = 1;
      details.file = file;
      details.tutorId = 2;
      details.teacherId = 3;
      details.comment = comment;

      await submitReport(details);
    }
  };

  return (
    <div
      className={clsx(
        "h-full w-full rounded-2xl flex flex-col gap-3 px-8 md:py-3",
        file && "file-uploader"
      )}
    >
      <CustomFileInput
        className={clsx(
          !file && isSubmited && "border-2 border-dotted border-[var(--danger)]"
        )}
        value={file}
        onChange={uploadFile}
      />
      <div className="flex flex-col flex-1 h-full w-full">
        <span className="text-xl font-bold max-md:hidden">Note : </span>
        <textarea
          value={comment}
          onChange={(e) => setComment(e.target.value)}
          className="h-full w-full text-lg px-1 py-2"
          placeholder="Leave comment to the tutor(optional) ..."
        />
      </div>
      <div className="flex flex-col gap-2">
        <button
          onClick={submission}
          className="px-3 py-1 rounded h-10 w-full md:text-lg text-lg font-bold text-white hover:text-[var(--primary)] hover:bg-transparent bg-[var(--secondary)]"
        >
          SUBMIT
        </button>
        {!file && isSubmited && (
          <span className="text-center text-lg text-[var(--danger)] font-semibold">
            No file uploaded
          </span>
        )}
      </div>
    </div>
  );
};

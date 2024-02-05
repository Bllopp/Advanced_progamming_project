import clsx from "clsx";
import { CustomFileInput } from "@components/CustomFileInput";
import { useState } from "react";
import axios from "axios";
import {urlReportService} from "../../../constant/constant";
import {useSelector} from "react-redux";

export const ReportUploadForm = ({report}) => {
  const token = useSelector((state) => state.auth.token)

  const [file, setFile] = useState(report);
  //const uploadFile = (file) => {

   // console.log(file)
  //}setFile(file);

  const uploadFile = async (file) => {
    try {
      const  formData = new FormData();
      formData.append('file', file);

      const response = await axios.post(`${urlReportService}/reports/submit`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          'Authorization': `${token}`,
        },
      });
      setFile(file);
      console.log(response.data);
    } catch (error) {
      console.error('Error uploading file: ', error);
    }
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

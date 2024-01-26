import http from "@utils/api/http";

export async function submitReport(details) {
  if (details.file) {
    const formData = new FormData();

    formData.append("studentId", details.studentId);
    formData.append("file", details.file);
    formData.append("tutorId", details.tutorId);
    formData.append("teacherId", details.teacherId);
    formData.append("comment", details.comment);

    try {
      await http.post("reports/submit", formData);
    } catch (error) {
      throw error;
    }
  }
};

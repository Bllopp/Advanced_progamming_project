import React, { useState, useEffect } from "react";
import {urlAuthenticationService, urlReportService} from "../../../constant/constant";
import {useSelector} from "react-redux";

export const ReviewForm = () => {
    const token = useSelector((state) => state.auth.token)
    const [reports, setReports] = useState([]);

    useEffect(() => {
        const fetchReports = async () => {
            try {
                const response = await fetch(`${urlReportService}/reports/all`, {
                    method: 'GET',
                    headers: {
                        Authorization: `${token}`
                    }
                });
                if (!response.ok) {
                    throw new Error(`Error fetching reports: ${response.statusText}`);
                }
                const reportsData = await response.json();

                // Extract all student IDs from reports
                const studentIds = reportsData.map((report) => report.studentId);

                // Fetch user information for all student IDs
                const userResponse = await fetch(`${urlAuthenticationService}/all`, {
                    method: 'GET',
                    headers: {
                        Authorization: `${token}`
                    }
                });

                if (!userResponse.ok) {
                    throw new Error(`Error fetching user data: ${userResponse.statusText}`);
                }

                const userData = await userResponse.json();

                // Create a mapping of studentId to userName
                const userNameMap = {};
                userData.forEach((user) => {
                    userNameMap[user.userId] = user.name;
                });

                // Update reports with user names
                const reportsWithUserNames = reportsData.map((report) => ({
                    ...report,
                    userName: userNameMap[report.studentId],
                }));

                setReports(reportsWithUserNames);
            } catch (error) {
                console.error(error.message);
            }
        };

        fetchReports();
    }, []); // The empty dependency array ensures this effect runs once when the component mounts

    return (
        <div className="main-container">
            <div className="inner-container">
                <p className="box title">Report validation</p>

                <table className="table">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>File</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    {reports.map((report) => (
                        <tr key={report.studentId}>
                            <td>{report.userName}</td>
                            <td>{report.file}</td>
                            <td>
                                <button className="validate-button">Validate</button>
                                <button className="invalidate-button">Invalidate</button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default ReviewForm;

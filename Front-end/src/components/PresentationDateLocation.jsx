// PresentationDateLocation.js
import React, { useState, useEffect } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import '../styles/PresentationDateLocation.css'; // Import the CSS file
import axios from "axios";
import {urlAuthenticationService, urlPressentationService} from "../constant/constant";

const PresentationDateLocation = () => {
    const [startDate1, setStartDate1] = useState(new Date());
    const [startDate2, setStartDate2] = useState(new Date());
    const [startDate3, setStartDate3] = useState(new Date());
    const [location1, setLocation1] = useState('');
    const [location2, setLocation2] = useState('');
    const [location3, setLocation3] = useState('');
    const [users, setUsers] = useState([])

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        try {
            const response = await axios.get(urlAuthenticationService + '/all');
            setUsers(response.data);
        } catch (error) {
            console.error('Error fetching users:', error);
        }
    };

    return (
        <div className="main-container">
            <div className="inner-container">
                <p className="box title">
                    Date
                </p>

                <div className="date-container">
                    <div className="box date-time-location-container">
                        <div className="date-time-location">
                            <label>1st proposition : </label>
                            <DatePicker
                                selected={startDate1}
                                onChange={(date) => setStartDate1(date)}
                                dateFormat="yyyy-MM-dd"
                            />
                            <label>Location : </label>
                            <select
                                value={location1}
                                onChange={(e) => setLocation1(e.target.value)}
                            >
                                <option value="">Select Location</option>
                                <option value="At school">At school</option>
                                <option value="At company">At company</option>
                                <option value="Visio">Visio</option>
                            </select>
                        </div>

                        <div className="date-time-location">
                            <label>2nd proposition : </label>
                            <DatePicker
                                selected={startDate2}
                                onChange={(date) => setStartDate2(date)}
                                dateFormat="yyyy-MM-dd"
                            />
                            <label>Location : </label>
                            <select
                                value={location2}
                                onChange={(e) => setLocation2(e.target.value)}
                            >
                                <option value="">Select Location</option>
                                <option value="At school">At school</option>
                                <option value="At company">At company</option>
                                <option value="Visio">Visio</option>
                            </select>

                        </div>

                        <div className="date-time-location">
                            <label>3rd proposition : </label>
                            <DatePicker
                                selected={startDate3}
                                onChange={(date) => setStartDate3(date)}
                                dateFormat="yyy-MM-dd"
                            />
                            <label>Location : </label>
                            <select
                                value={location3}
                                onChange={(e) => setLocation3(e.target.value)}
                            >
                                <option value="">Select Location</option>
                                <option value="At school">At school</option>
                                <option value="At company">At company</option>
                                <option value="Visio">Visio</option>
                            </select>

                        </div>
                    </div>
                </div>
                {/* Dropdown for professors */}
                <div className="date-time-location">
                    <label>Professor : </label>
                    <select
                        // value={professor}
                        // onChange={(e) => setProfessor(e.target.value)}
                    >
                        <option value="">Select Professor</option>
                        <option value="Professor 1">Professor 1</option>
                        <option value="Professor 2">Professor 2</option>
                        <option value="Professor 3">Professor 3</option>
                    </select>
                </div>

                {/* Dropdown for tutors */}
                <div className="date-time-location">
                    <label>Tutor : </label>
                    <select
                        // value={tutor}
                        // onChange={(e) => setTutor(e.target.value)}
                    >
                        <option value="">Select Tutor</option>
                        <option value="Tutor 1">Tutor 1</option>
                        <option value="Tutor 2">Tutor 2</option>
                        <option value="Tutor 3">Tutor 3</option>
                    </select>
                </div>
            </div>

            <button className="submit-button" onClick={() => axios.post(urlPressentationService, {
                "studentId" : 24,
                "teacherId" : 73,
                "tutorId" : 912,
                "date1" : startDate1,
                "date2" : startDate2,
                "date3" : startDate3
            }) }>
                SUBMIT
            </button>
        </div>
    );
};

export default PresentationDateLocation;

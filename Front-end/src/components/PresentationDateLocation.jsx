// PresentationDateLocation.js
import React, { useState, useEffect } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import '../styles/PresentationDateLocation.css';
import {urlAuthenticationService, urlPressentationService} from "../constant/constant";
import {useSelector} from "react-redux";

const PresentationDateLocation = () => {
    const [startDate1, setStartDate1] = useState(new Date());
    const [startDate2, setStartDate2] = useState(new Date());
    const [startDate3, setStartDate3] = useState(new Date());
    const [location1, setLocation1] = useState('');
    const [location2, setLocation2] = useState('');
    const [location3, setLocation3] = useState('');

    const [teacher, setTeacher] = useState('');
    const [tutor, setTutor] = useState('');

    const [users, setUsers] = useState([])
    const [teachers, setTeachers] = useState([]);
    const [tutors, setTutors] = useState([]);

    const token = useSelector((state) => state.auth.token)

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = async () => {
        try {
            console.log(token)
            await fetch(`${urlAuthenticationService}/all`, {
                method: 'GET',
                headers: {
                    'Authorization': `${token}`
                }
            }).then(response => {
                setUsers(response.data);
                const { teachers, tutors } = filterUsersByRole(response.data);
                setTeachers(teachers);
                setTutors(tutors);
            }).catch(error => {
                console.error('Error fetching users:', error);

            });

        } catch (error) {
            console.error('Error fetching users:', error);
        }
    };

    const filterUsersByRole = (users) => {
        const teachers = users.filter(user => user.role === 'teacher');
        const tutors = users.filter(user => user.role === 'tutor');
        return { teachers, tutors };
    };

    const submitDates = async () => {
        const dates = {
            studentId : 242,
            date1 : startDate1,
            mode1 : location1,
            date2 : startDate2,
            mode2 : location2,
            date3 : startDate3,
            mode3 : location3,
            teacherId : teacher.id,
            tutorId : tutor.id,
        }

        await fetch(`${urlPressentationService}/presentation/add`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `${token}`
            },
            body: JSON.stringify(dates)
        })
            .then(response => {
                if (response.status === 200) {
                    setStartDate1(new Date());
                    setStartDate2(new Date());
                    setStartDate3(new Date());
                    setLocation1('');
                    setLocation2('');
                    setLocation3('');
                    response.json().then(data => {
                        console.log(data);
                    });
                } else {
                    console.error('Add presentation failed !');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }


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
                                <option value="school">At school</option>
                                <option value="company">At company</option>
                                <option value="visio">Visio</option>
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
                                <option value="school">At school</option>
                                <option value="company">At company</option>
                                <option value="visio">Visio</option>
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
                                <option value="school">At school</option>
                                <option value="company">At company</option>
                                <option value="visio">Visio</option>
                            </select>

                        </div>
                    </div>
                </div>

                {/* Dropdown for teachers */}
                <div className="date-time-location">
                    <label>Teacher : </label>
                    <select>
                        <option value="">Select Teacher</option>
                        {teachers.map((teacher, index) => (
                            <option key={index} value={teacher}>{teacher.username}</option>
                        ))}
                    </select>
                </div>

                {/* Dropdown for tutors */}
                <div className="date-time-location">
                    <label>Tutor : </label>
                    <select>
                        <option value="">Select Tutor</option>
                        {tutors.map((tutor, index) => (
                            <option key={index} value={tutor}>{tutor.username}</option>
                        ))}
                    </select>
                </div>
            </div>

            <button className="submit-button" onClick={submitDates}>
                SUBMIT
            </button>
        </div>
    );
};

export default PresentationDateLocation;

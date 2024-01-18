import React, {useState} from 'react';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

const DefenseDateLocation = ({dateId, locationId}) => {
    const [startDate, setStartDate] = useState(new Date());
    return (
        <div className="main-container">
            <div className="inner-container">
                <p className="box"
                   style={{"background-color": "#EBEBEB", "color": "#468CCC", "font-size": "30px"}}>Date {dateId}</p>
                <div className="date-container">
                    <p className="box" style={{
                        "background-color": "#468CCC",
                        "color": "white",
                        "margin-right": "10px",
                        "font-size": "20px"
                    }}>Select a Date</p>
                    <div className="box" style={{"background-color": "#A3A3A3"}}>
                        <DatePicker
                            selected={startDate}
                            onChange={(date) => setStartDate(date)}
                            dateFormat="dd/MM/yyyy"
                        />

                    </div>
                </div>
            </div>
            <div className="inner-container">
                <p className="box" style={{
                    "background-color": "#EBEBEB",
                    "color": "#468CCC",
                    "font-size": "30px"
                }}>Location {locationId}</p>
                <div className="location-choice box" style={{"background-color": "#EBEBEB", "margin-top": "55px"}}>
                    <div className="location-choice-button">
                        <input type="radio" id={`school-${locationId}`} name={`location-${locationId}`} value="school"/>
                        <label htmlFor={`school-${locationId}`}>At school</label>
                    </div>
                    <div className="location-choice-button">
                        <input type="radio" id={`company-${locationId}`} name={`location-${locationId}`}
                               value="company"/>
                        <label htmlFor={`company-${locationId}`}>At company</label>
                    </div>
                    <div className="location-choice-button">
                        <input type="radio" id={`visio-${locationId}`} name={`location-${locationId}`} value="visio"/>
                        <label htmlFor={`visio-${locationId}`}>Visio</label>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default DefenseDateLocation;

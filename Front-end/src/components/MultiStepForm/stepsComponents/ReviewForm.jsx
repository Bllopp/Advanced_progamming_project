import './ReviewForm.css';
import React from "react";

export const ReviewForm = () => {
  return (
      <div className="main-container">
        <div className="inner-container">
          <p className="box title">
            Report validation
          </p>

          <table className="table">
            <thead>
            <tr>
              <th>Name</th>
              <th>File</th>
              <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td>John Doe</td>
              <td>SomeFile.pdf</td>
              <td>
                <button className="validate-button">
                  Validate
                </button>
                <button className="invalidate-button">
                  Invalidate
                </button>
              </td>
            </tr>
            {/* Add more rows as needed */}
            </tbody>
          </table>
        </div>
      </div>
  );
};


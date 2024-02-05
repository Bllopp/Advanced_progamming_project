# Report Service

This is a Spring application for student to upload their internship report and for teacher/tutor to validate them.
## Application Architecture
The service is build around SOLID principles and we tried to organize
its architecture wisely.


## Routes



><details>
><summary> POST /reports/submit </summary>
>
>### Purpose
>
>Called when a student wants to submit is intership report.
>
>### Precision
>
>All the Id's field represent userID in the user database.
>
>The `file` represents the binary of the pdf file, and it's stored as blob in MySQL but retrieve as a MultipartFile in Java;
>
>### Expect
>    ```json
>    {
>      "studentId" : 22, 
>      "file" : "report_v3_FR.pdf",
>      "teacherId" : 73,
>      "tutorId" : 912
>    }
>    ```
>
>### Results
>
>Add an entry to the `reports` table.
>
>
></details>

> <details>
> <summary> GET /reports/download/{studentId} </summary>
>
> ### Purpose
>
> Called to download the report in the browser
>
> ### Precision
>
>  The download is triggered by the header tag `attachement` in the response sent.
> 
> ### Results
>
> Return an HTTP response with the pdf file in the body and the right header
>
>
> *Example :*
>
> `GET /reports/download/52`
>
>
> </details>


> <details>
> <summary> POST /reports/validation-status/{studentId} </summary>
>
> ### Purpose
>
> Called to retrieve the status of a given report.
>
> ### Precision
>
> The studentId parameter should represent a existing user inside the user related database.\
> It must also be a student that has already upload his report.
>
>
> ### Results
>
> Returns an HTTP response about whether the report has been validated or not.
> Handle the error about report not being form
>
>
> *Example :*
>
> `GET report/validation-status/643`
>
>
> </details>


> <details>
> <summary> POST /presentation/vote/{role}/{studentId} </summary>
>
> ### Purpose
>
> Called to submit the vote of a teacher/tutor user for a given report.
>
> ### Precision
>
> The fields representing the vote of teacher and tutor can only have 3 values:
> - `0` (default) the date has not been answered yet
> - `1` the teacher/tutor is available
> - `-1` the teacher/tutor **isn't** available
>
> ### Expect
> 
> ```json
> {
>   "vote" : 1
> } 
>```
>
> ### Results
>
> Update the database table with the corresponding vote
>
>
>
> </details>


> <details>
> <summary> GET /reports/all </summary>
>
> ### Purpose
>
> Called to retrieve all the report that have been submitted
> 
> ### Precision
>
>
> ### Results
>
> Return a list of reports in a JSON array
>
>
> *Example :*
>
> `GET /reports/all`
>
> ```json
> [
>   {
>     "studentId" : 52,
>     "file" : "report_final.pdf",
>     "teacherId" : 0,
>     "teacherVote" : 0,
>     "tutorId" : -1,
>     "tutorVote" : -1
>   },
>   {
>     "studentId" : 624,
>     "file" : "rapport_v2.pdf",
>     "teacherId" : 0,
>     "teacherVote" : 0,
>     "tutorId" : -1,
>     "tutorVote" : -1
>   }
> ]
> ```
>
> </details>




### Authorization

To make sure our service is secured, all the routes described above require a JWT Token in the request header to be executed.

At the beginning of every route, the function `checkToken()` is executed,
it verify the signature of the token using the secret key, and also the expiration date.

The token contains two information about the user that made the request: its **id** and its **role**

This token is obtained when logging to the authentication service with the right credentials\
More information in [this document](../auth-service/readme.md)






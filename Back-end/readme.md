# Presentation Service

This is a Spring application used to add, manage and setup 
student presentation for they internship defense.
## Application Architecture 
The service is build around SOLID principles and we tried to organize
its architecture wisely.


## Routes



><details>
><summary> POST /presentation/add </summary>
>
>### Purpose 
>
>Called when a student wants to set up is presentation for the first time.
>
>### Precision
>
>All the Id's field respresent userID in the user database.
>
>Variable `mode` represent the type of presentation, either `remote` or `in_person`.
>
>Dates sent should be in the **yyyy-MM-dd** format to be then translate as MySQL date fields.
>
>### Expect
>    ```json
>    {
>      "studentId" : 242, 
>      "mode" : "remote",
>      "teacherId" : 73,
>      "tutorId" : 912,
>      "date1" : "2024-03-14",
>      "date2" : "2024-03-19",
>      "date3" : "2024-03-04"
>?    }
>    ```
>
>### Results
>
>Add an entry to the `presentation` table and 3 entries in the `presentation-dates` table.
>
>
></details>





> <details>
> <summary> GET /presentation/{userId} </summary>
>
> ### Purpose
>
> Called to retrieve all the presentation related to the user (either student, teacher or tutor).
>
> ### Precision
>
> The userId parameter should represent a existing user inside the user related database
>
>
> ### Results
>
> Return a JSON array containing all the presentations which contains the userId.
>
> Field `finalDate` can be null to indicate the presentation isn't yet fully set up.
>
> *Exemple :*
>
> `GET presentation/242`
>
> ```json
> [
>   {
>     "presId" : 52,
>     "studentId" : 242,
>     "mode" : "in_person",
>     "teacherId" : 1235,
>     "tutorId" : 23,
>     "finaleDate" : null
>   },
>   {
>     "presId" : 236,
>     "studentId" : 242,
>     "mode" : "remote",
>     "teacherId" : 124,
>     "tutorId" : 233,
>     "finaleDate" : "2025-06-23"
>   }
> 
> ]
> ```
>
> </details>


> <details>
> <summary> GET /presentation/vote/{presId} </summary>
>
> ### Purpose
>
> Called to retrieve all the proposed dates of a presentation
>
> ### Precision
>
> The fields representing the vote of teacher and tutor can only have 3 values:
> - `0` (default) the date has not been answered yet
> - `1` the teacher/tutor is available
> - `-1` the teacher/tutor **isn't** available
>
>
> ### Results
>
> Return at least the first 3 dates proposed by the student (their can be more than 3) and the vote associated with each date.
>
>
> *Exemple :*
>
> `GET presentation/vote/52`
>
> ```json
> [
>   {
>     "presId" : 52,
>     "date" : "2024-03-14",
>     "teacherVote" : 0,
>     "tutorVote" : -1
>   },
>   {
>     "presId" : 52,
>     "date" : "2024-03-19",
>     "teacherVote" : 1,
>     "tutorVote" : 0
>   },
>   {
>     "presId" : 52,
>     "date" : "2024-03-04",
>     "teacherVote" : -1,
>     "tutorVote" : -1
>   }
> 
> ]
> ```
>
> </details>


> <details>
> <summary> GET /presentation/vote/{presId}/{date} </summary>
>
> ### Purpose
>
> Called to retrieve a specific presentation date proposal and check its state
>
> ### Precision
>
> The fields representing the vote of teacher and tutor can only have 3 values:
> - `0` (default) the date has not been answered yet
> - `1` the teacher/tutor is available
> - `-1` the teacher/tutor **isn't** available
>
>
> ### Results
>
> Return the corresponding presentation dates or nothing
>
>
> *Exemple :*
>
> `GET presentation/vote/52/2024-03-14`
>
> ```json
> {
>   "presId" : 52,
>   "date" : "2024-03-14",
>   "teacherVote" : 0,
>   "tutorVote" : -1
> }
> 
> ```
>
> </details>


> <details>
> <summary> POST /presentation/{presId}/add </summary>
>
> ### Purpose
>
> Called to add 3 new dates to already existing presentation, when all of previous ones could were not accepted.
>
> ### Precision
>
> Variable `mode` represent the type of presentation, either `remote` or `in_person`.
>
> Dates sent should be in the **yyyy-MM-dd** format to be then translate as MySQL date fields.
>
> ### Expect
>
> ```json
> {
>   "user" : 142,
>   "mode": "remote",
>   "vote": [
>     {
>       "date" : "2024-03-24",
>       "vote" : 0 
>     },
>     {
>       "date" : "2024-04-08",
>       "vote" : 0
>     },
>     {
>       "date" : "2024-04-30",
>       "vote" : 0
>     }
>   ]
> }
> ```
>
> ### Results
>
> Add 3 new entries in the `presentation_dates` table.
>
>
> </details>

> <details>
>     <summary> POST /presentation/vote/{presId}</summary>
>
> ### Purpose
>
> Called when teacher/tutor wants to send their availabilities for a set of 3 dates.
>
> ### Precision
>
> Variable `mode` represent the type of presentation, either `remote` or `in_person`.
>
> Dates sent should be in the **yyyy-MM-dd** format to be then translate as MySQL date fields.
>
> Front-end Client needs to ensure the dates he wants to updates are existing in the `presentation_dates` table.
>
> To get this info, he can use the `GET /presentation/{presId}` route.
>
> ### Expect
>
> ```json
> {
>   "user" : 142,
>   "mode": "remote",
>   "vote": [
>     {
>       "date" : "2024-03-24",
>       "vote" : 0 
>     },
>     {
>       "date" : "2024-04-08",
>       "vote" : 0
>     },
>     {
>       "date" : "2024-04-30",
>       "vote" : 0
>     }
>   ]
> }
> ```
>
> ### Results
>
> Add 3 new entries in the `presentation_dates` table.
>
>
> </details>


### Authorization

To make sure our service is secure, all the routes desscribed above require a JWT Token in the request header to be executed.

This token is obtained when logging to the authentication service with the right credentials

More information in [this document](../auth-service/readme.md)






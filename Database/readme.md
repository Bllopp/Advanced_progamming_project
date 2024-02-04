# Database Architecture

The databases and services are designed to be only use by the associated service.\
In production, these databases should be place in a dedicated Docker container,
available to the matching service.

## Database for Users
One table that contains all the info related to a user : mail, password, role...

### User

| id | created_at          | email             | password                   | role      | username        |
|----|---------------------|-------------------|----------------------------|-----------|-----------------|
| 1  | 2024-08-19 14:21:52 | jhondoe@efrei.net | $1$$yqV86f/QMuuFHLiLRnJ5R1 | STUDENT   | Jhon Doe        |
| 2  | 2023-11-25 06:26:31 | JA@efrei.fr       | $1$$wPM0EW3FTDlt4aI4fY65Z0 | TEACHER   | Jacque Augustin |  

The field `created_at` is automatically generated when the user is created by MySQL.

The field `password` is encrypted using Bcrypt cyphering. 


## Student presentation
Presentation table for info related to the defense and final date
Presentation_dates table for keeping track of availabilities

### Presentation
| presId | studentID | mode | teacherId | tutorId |  finalDate |
| -- | --- | --- | ---- | ------ | --- |
| 1 | 1 | remote | 2 | 3 |  null |

### Presentation_dates
| PresID | date  | teacherVote | tutorVote |
| ---- | ---  | --- | --- |
|  1  | 2024-09-13 | 0 |  1 |
|  1  | 2024-09-19 | 1 | 0 |
|  1  | 2024-09-22 | 0 | -1 |

## Report validation
Report table contains the `.pdf` file and the validation of both teacher and tutor

### Report
| studentID | file | teacherId | teacherVote | tutorId |  tutorVote | upload_date | 
| --- | --- | ---- | ------ | --- | --- | --- |
| 1 | blob() | 2 | 0 | 3 | 0 |  2024 | 




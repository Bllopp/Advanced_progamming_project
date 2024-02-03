# Database Architecture

One database per micro-service + 1 for users

## Database for User
2 tables for users infos and roles
#### User

| id | name | mail | password |
|---|------|----|---|
| 1 | Jhon Doe | jhondoe@efrei.net| password |
| 2 | Jacque Augustin | JA@efrei.fr | password2|


#### Role

| id | role |
| --| -- |
| 1 | student |
| 2 | teacher |


## Student presentation
Presentation table for info related to the defense and final date
Presentation_dates table for keeping track of availabilities

### Presentation
| presId | studentID | mode | teacherId | tutorId |  finalDate |
| -- | --- | --- | ---- | ------ | --- |
| 1 | 1 | remote | 2 | 3 |  null |

### Presentation_dates (temporary)
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




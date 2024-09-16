import {Routes} from '@angular/router';
import {DepartementListComponent} from "./components/departements/departement-list/departement-list.component";
import {NewDepartementComponent} from "./components/departements/new-departement/new-departement.component";
import {MainComponent} from "./components/main/main.component";
import {StudentListComponent} from "./components/students/student-list/student-list.component";
import {NewStudentComponent} from "./components/students/new-student/new-student.component";
import {UpdateDepartementComponent} from "./components/departements/update-departement/update-departement.component";
import {UpdateStudentComponent} from "./components/students/update-student/update-student.component";

export const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: 'departements',
        component: DepartementListComponent
      },
      {
        path: 'new-departement',
        component: NewDepartementComponent
      },
      {
        path: 'students',
        component: StudentListComponent
      },
      {
        path: 'new-student',
        component: NewStudentComponent
      },
      {
        path: 'departements/update/:departement-id',
        component: UpdateDepartementComponent
      },
      {
        path: 'students/update/:student-id',
        component: UpdateStudentComponent
      }
    ]
  }

];

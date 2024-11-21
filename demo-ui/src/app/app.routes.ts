import {Routes} from '@angular/router';
import {DepartementListComponent} from "./components/departements/departement-list/departement-list.component";
import {NewDepartementComponent} from "./components/departements/new-departement/new-departement.component";
import {MainComponent} from "./components/main/main.component";
import {StudentListComponent} from "./components/students/student-list/student-list.component";
import {NewStudentComponent} from "./components/students/new-student/new-student.component";
import {UpdateDepartementComponent} from "./components/departements/update-departement/update-departement.component";
import {UpdateStudentComponent} from "./components/students/update-student/update-student.component";
import {authGuard} from "./services/guard/auth.guard";
import {LoginComponent} from "./pages/login/login.component";

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: '',
    component: MainComponent,
    canActivate: [authGuard],
    children: [
      {
        path: 'departements',
        component: DepartementListComponent,
        canActivate: [authGuard],
      },
      {
        path: 'new-departement',
        component: NewDepartementComponent,
        canActivate: [authGuard],
      },
      {
        path: 'students',
        component: StudentListComponent,
        canActivate: [authGuard],
      },
      {
        path: 'new-student',
        component: NewStudentComponent,
        canActivate: [authGuard],
      },
      {
        path: 'departements/update/:departement-id',
        component: UpdateDepartementComponent,
         canActivate: [authGuard],
      },
      {
        path: 'students/update/:student-id',
        component: UpdateStudentComponent,
        canActivate: [authGuard],
      }
    ]
  }

];

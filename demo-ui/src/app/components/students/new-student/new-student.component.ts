import {Component, OnInit} from '@angular/core';
import {StudentRequest} from "../../../services/models/student-request";
import {StudentsService} from "../../../services/services/students.service";
import {Router} from "@angular/router";
import {DepartementsService} from "../../../services/services/departements.service";
import {DepartementResponse} from "../../../services/models/departement-response";
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-new-student',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './new-student.component.html',
  styleUrl: './new-student.component.css'
})
export class NewStudentComponent implements OnInit{

  errorMsg: Array<string> = []
  studentRequest: StudentRequest = {
    name: '',
    email: '',
    departementId: 0
  }
  departementResponse: DepartementResponse[] = [];

  constructor(
    private studentService: StudentsService,
    private departementService: DepartementsService,
    private router: Router
  ) {
  }
  ngOnInit() {
    this.departementService.listAllDepartement().subscribe({
      next: (departements: DepartementResponse[]) => {
        this.departementResponse = departements
      },
      error: (err) => {
        console.error('Erreur lors du chargement des départements', err);
      }
    })
  }

  addStudent(){
    this.studentService.addStudent({
      body: this.studentRequest
    }).subscribe({
      next: data => {
        alert("Étudiant ajouté")
        this.router.navigateByUrl("/students")
      },
      error: (err) => {
        console.log(err)
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.errorMsg.push(err.error.error);
        }
      }
    })
  }

}

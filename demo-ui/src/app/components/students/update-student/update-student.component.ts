import {Component, OnInit} from '@angular/core';
import {UpdateStudentRequest} from "../../../services/models/update-student-request";
import {StudentsService} from "../../../services/services/students.service";
import {DepartementsService} from "../../../services/services/departements.service"; // Import correct du service des départements
import {ActivatedRoute, Router} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {DepartementResponse} from "../../../services/models/departement-response";

@Component({
  selector: 'app-update-student',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './update-student.component.html',
  styleUrl: './update-student.component.css'
})
export class UpdateStudentComponent implements OnInit{

  errorMsg: Array<string> = []
  studentId!: number

  studentRequest: UpdateStudentRequest = {
    id: 0,
    name: '',
    email: '',
    departementId: 0
  }

  departementResponse: DepartementResponse[] = [];

  constructor(
    private studentService: StudentsService,
    private departementService: DepartementsService,  // Utilisation du service des départements
    private router: Router,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit() {
    this.studentId = this.route.snapshot.params['student-id'];
    this.loadDepartements();  // Charger d'abord les départements
  }

  private loadDepartements() {
    this.departementService.listAllDepartement().subscribe({
      next: (data) => {
        this.departementResponse = data;
        this.loadStudent();  // Charger l'étudiant une fois les départements disponibles
      },
      error: (err) => {
        console.log("Erreur lors du chargement des départements", err);
      }
    });
  }

  private loadStudent(){
    this.studentService.findStudentById({
      "student-id": this.studentId
    }).subscribe({
      next: (data) => {
        this.studentRequest.id = data.id;
        this.studentRequest.name = data.name;
        this.studentRequest.email = data.email;
        // Sélectionner le bon département par défaut
        const selectedDepartement = this.departementResponse.find(dept => dept.name === data.departementName);
        this.studentRequest.departementId = selectedDepartement ? selectedDepartement.id : 0;
      },
      error: (err) => {
        console.log("Erreur lors du chargement de l'étudiant", err);
      }
    });
  }

  updateStudent() {
    this.studentService.updateStudent({
      "student-id": this.studentId,
      body: this.studentRequest
    }).subscribe({
      next: () => {
        alert("Étudiant modifié avec succès");
        this.router.navigateByUrl("/students");
      },
      error: (err) => {
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.errorMsg = ["Désolé, une erreur est survenue lors de la modification. Veuillez vérifier si l'e-mail n'est pas doublé."];
        }
      }
    });
  }
}

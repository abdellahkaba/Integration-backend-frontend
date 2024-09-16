import {Component, OnInit} from '@angular/core';
import {StudentsService} from "../../../services/services/students.service";
import {StudentResponse} from "../../../services/models/student-response";
import {NgForOf, NgIf} from "@angular/common";
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    RouterLink
  ],
  templateUrl: './student-list.component.html',
  styleUrl: './student-list.component.css'
})
export class StudentListComponent implements OnInit{

  studentResponse: StudentResponse[] = []
  errorMsg: Array<string> = []
  constructor(
    private studentService: StudentsService,
    private router: Router
  ) {
  }
  ngOnInit(): void {
    this.listAllStudent()
  }

  private listAllStudent() {
    this.studentService.listAllStudent()
      .subscribe({
        next: (students: StudentResponse[]) => {
          this.studentResponse = students;
        },
        error: (err) => {
          console.error("Erreur lors de chargement")
        }
      })
  }

  deleteStudent(studentId: number) {
    this.studentService.deleteStudent({"student-id": studentId}).subscribe({
      next: () => {
        alert("Etudiant supprime avec success")
        this.listAllStudent()
      },error: (err) => {
        console.log(err)
        this.errorMsg = ["Une  erreur lors de la suppression"]
      }
    })

  }
}

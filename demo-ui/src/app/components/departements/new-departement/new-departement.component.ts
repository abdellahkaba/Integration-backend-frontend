import {Component, OnInit} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {DepartementRequest} from "../../../services/models/departement-request";
import {DepartementsService} from "../../../services/services/departements.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-departement',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    FormsModule
  ],
  templateUrl: './new-departement.component.html',
  styleUrl: './new-departement.component.css'
})
export class NewDepartementComponent implements OnInit{

  errorMsg: Array<string> = []
  message = '';
  level: 'success' |'error' = 'success';
  departementRequest: DepartementRequest = {
    name: ''
  }
  constructor(
    private departementService: DepartementsService,
    private router: Router
  ) {
  }
  ngOnInit() {
  }
  saveDepartement(){
    this.departementService.saveDepartement({
      body: this.departementRequest
    }).subscribe({
      next: data => {
        alert("Departement ajouté")
        this.router.navigateByUrl("/departements")
      },
      error: (err) => {
        console.log(err);
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.errorMsg.push(err.error.error);
        }
      }
    })
  }

  // saveDepartement(){
  //   this.message = ''
  //   this.level = 'success'
  //   this.departementService.saveDepartement({
  //     body: this.departementRequest
  //   }).subscribe({
  //     next: data => {
  //       this.level = 'success'
  //       this.message = 'Le departement ajouté avec succès'
  //       // this.router.navigateByUrl("/departements")
  //     },
  //     error: (err) => {
  //       console.log(err);
  //       this.level = 'error'
  //       this.message = err.error.error
  //     }
  //   })
  // }

}

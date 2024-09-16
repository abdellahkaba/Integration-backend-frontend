import {Component, OnInit} from '@angular/core';
import {DepartementsService} from "../../../services/services/departements.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UpdateDepartementRequest} from "../../../services/models/update-departement-request";
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-update-departement',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './update-departement.component.html',
  styleUrl: './update-departement.component.css'
})
export class UpdateDepartementComponent implements OnInit{

  departementRequest: UpdateDepartementRequest = {
    id: 0,
    name: ''
  }
  errorMsg: Array<string> = []
  departementId!: number;

  constructor(
    private departementService: DepartementsService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.departementId = this.route.snapshot.params['departement-id'];
    this.loadDepartement();
  }

private loadDepartement(){
    this.departementService.findDepartementById({
        "departement-id": this.departementId
    }).subscribe({
      next: (data) => {
        this.departementRequest.name = data.name
      },
      error: (err) => {
        console.log("Erreur lors de chargement")
      }
    })
}

updatedDepartement(){
    this.departementService.updateDepartement({
      "departement-id": this.departementId,
      body: this.departementRequest
    }).subscribe({
      next: () => {
        alert("Departement modifié avec success")
        this.router.navigateByUrl("/departements")
      },
      error: (err) => {
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.errorMsg = ["Impossible Il se peut que ce departement existe deja !"];
        }
      }
    })
}

}

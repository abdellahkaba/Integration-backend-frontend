import {Component, OnInit} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {DepartementsService} from "../../../services/services/departements.service";
import {Router, RouterLink} from "@angular/router";
import {DepartementResponse} from "../../../services/models/departement-response";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-departement-list',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    RouterLink
  ],
  templateUrl: './departement-list.component.html',
  styleUrl: './departement-list.component.css'
})
export class DepartementListComponent implements OnInit {

  departementResponse: DepartementResponse[] = [];
  errorMsg: Array<string> = []

  constructor(
    private departementService: DepartementsService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.listAllDepartements();
  }

  private listAllDepartements(): void {
    this.departementService.listAllDepartement().subscribe({
      next: (departements: DepartementResponse[]) => {
        // Stocker la réponse dans la propriété
        this.departementResponse = departements;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des départements', err);
      }
    });
  }


  deleteDepartement(departementId: number) {
    this.departementService.deleteDepartement({ "departement-id": departementId })
      .subscribe({
        next: () => {
          this.errorMsg = [];  // Réinitialise les messages d'erreur
          alert("Département supprimé");
          this.listAllDepartements()
        },
        error: (err) => {
          console.log(err);
          if (err.error.validationErrors) {
            this.errorMsg = err.error.validationErrors;
          } else {
            this.errorMsg = ["Impossible de supprimer il es deja associé à un étudiant"];
          }
        }
      });
  }



}


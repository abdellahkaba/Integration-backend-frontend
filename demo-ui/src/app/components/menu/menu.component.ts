import {Component, OnInit} from '@angular/core';
import {RouterLink} from "@angular/router";
import {KeycloakService} from "../../services/keycloak/keycloak.service";


@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent implements OnInit{

  constructor(
     private keycloakService: KeycloakService
  ) {
  }

  ngOnInit() {

  }

  async logout() {
     await this.keycloakService.logout();
  }
}

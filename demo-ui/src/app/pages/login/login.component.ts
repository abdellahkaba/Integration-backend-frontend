import {Component, OnInit} from '@angular/core';
import {KeycloakService} from "../../services/keycloak/keycloak.service";
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  constructor(
    private key: KeycloakService
  ) {
  }
  async ngOnInit(): Promise<void> {
    await this.key.init()
    await this.key.login()
  }
}
